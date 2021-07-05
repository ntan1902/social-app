import React, {useContext, useEffect, useState} from 'react'
import Topbar from "../../components/topbar/Topbar";
import "./messenger.css";
import Conversation from "../../components/conversation/Conversation";
import Message from "../../components/message/Message";
import {Send} from "@material-ui/icons";
import ChatOnline from "../../components/chatOnline/ChatOnline";
import {AuthContext} from "../../context/AuthContext";
import conversationApi from "../../api/ConversationApi";
import messageApi from "../../api/MessageApi";

export default function Messenger() {
    const [conversations, setConversations] = useState([]);
    const [currentChat, setCurrentChat] = useState(null);
    const [messages, setMessages] = useState([]);
    const [newMessage, setNewMessage] = useState("");

    const {user} = useContext(AuthContext);

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

    const handleSubmit = async (e) => {
        e.preventDefault();
        const message = {
            senderId: user.id,
            receiverId: currentChat?.secondUserId,
            content: newMessage
        };

        const res = await messageApi.createMessage(message);
        setMessages([...messages, res]);
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
                                    <Conversation  userId={conversation.secondUserId}/>
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
                                        {messages.map(message => (
                                            <Message key={message.id} message={message} own={message.senderId === user.id}/>
                                        ))}
                                    </div>

                                    <div className={"chatBoxBottom"}>
                                        <textarea className={"chatMessageInput"} placeholder={"Aa"}
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
