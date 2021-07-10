import React, {useContext, useEffect, useRef, useState} from 'react'
import Topbar from "../../components/topbar/Topbar";
import "./messenger.css";
import Conversation from "../../components/conversation/Conversation";
import Message from "../../components/message/Message";
import {Send} from "@material-ui/icons";
import ChatOnline from "../../components/chatOnline/ChatOnline";
import {AuthContext} from "../../context/AuthContext";
import conversationApi from "../../api/ConversationApi";
import messageApi from "../../api/MessageApi";
import Stomp from "stompjs";

export default function Messenger() {
    const [conversations, setConversations] = useState([]);
    const [currentChat, setCurrentChat] = useState(null);
    const [messages, setMessages] = useState([]);
    const [newMessage, setNewMessage] = useState("");
    const [arrivalMessage, setArrivalMessage] = useState(null);

    const {user} = useContext(AuthContext);
    const scrollRef = useRef();


    const stompClient = useRef();

    // Connect to web socket
    useEffect(() => {
        const connect = () => {
            const url = "ws://localhost:8081/stomp";
            stompClient.current = Stomp.client(url);
            stompClient.current.debug = null;
            stompClient.current.connect({}, onConnected, onError);
        }

        function onConnected() {
            console.log("Connected!!!")

            publish({
                action: "login",
                data: {
                    userId: user.id
                }
            })

            // Subscribe to the Public Topic
            stompClient.current.subscribe(`message.data.${user.id}`, onMessageReceived);

        }

        function onError(err) {
            console.log(err);
        }

        function onMessageReceived(payload) {
            const message = JSON.parse(payload.body);
            setArrivalMessage(message)
        }

        connect();
    }, [user.id])

    // Publish to Server
    const publish = (payload) => {
        if (stompClient.current)
            stompClient.current.send("api.data", {}, JSON.stringify(payload))
    }


    useEffect(() => {
        const fetchConversations = async () => {
            const res = await conversationApi.getAllConversations(user.id);
            setConversations(res);
        }

        fetchConversations();
    }, [user.id])

    useEffect(() => {
        const fetchMessages = async () => {
            const res = await messageApi.getAllMessages(currentChat?.firstUserId, currentChat?.secondUserId)
            setMessages(res)
        }
        currentChat && fetchMessages()
    }, [currentChat])

    useEffect(() => {
        arrivalMessage &&
        (currentChat?.secondUserId === arrivalMessage.senderId) &&
        setMessages((prevState) => [...prevState, arrivalMessage])
    }, [arrivalMessage, currentChat])

    useEffect(() => {
        scrollRef.current?.scrollIntoView({behavior: "smooth"});
    }, [messages]);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const message = {
            senderId: user.id,
            receiverId: currentChat?.secondUserId,
            content: newMessage
        };

        const res = await messageApi.createMessage(message);

        // Publish to socket server
        publish({
            action: "send-message",
            data: res
        })

        setMessages([...messages, res]);
        setNewMessage("");
    }

    return (
        <>
            <Topbar/>
            <div className="messenger">
                <div className={"chatMenu"}>
                    <div className={"chatMenuWrapper"}>
                        <input placeholder={"Search for friends"} className={"chatMenuInput"}/>
                        {
                            conversations.map(conversation => (
                                <div key={conversation.secondUserId} onClick={() => setCurrentChat(conversation)}>
                                    <Conversation userId={conversation.secondUserId}/>
                                </div>
                            ))
                        }

                    </div>
                </div>
                <div className={"chatBox"}>
                    <div className={"chatBoxWrapper"}>
                        {
                            currentChat ?
                                <>
                                    <div className={"chatBoxTop"}>
                                        {messages.map(m => (
                                            <div ref={scrollRef} key={m.id} >
                                                <Message message={m}
                                                         own={m.senderId === user.id}/>
                                            </div>
                                        ))}
                                    </div>

                                    <div className={"chatBoxBottom"}>
                                        <textarea className={"chatMessageInput"} placeholder={"Aa"}
                                                  value={newMessage}
                                                  onChange={(e) => setNewMessage(e.target.value)}/>
                                        <Send className={"chatSubmitButton"} onClick={handleSubmit}/>
                                    </div>
                                </> :
                                <span className={"noConversationText"}>Open a conversation to start a chat.</span>
                        }

                    </div>
                </div>
                <div className={"chatOnline"}>
                    <div className={"chatOnlineWrapper"}>
                        <ChatOnline/>
                        <ChatOnline/>
                        <ChatOnline/>
                    </div>
                </div>
            </div>
        </>
    )
}
