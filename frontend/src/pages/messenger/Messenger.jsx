import React from 'react'
import Topbar from "../../components/topbar/Topbar";
import "./messenger.css";
import Conversation from "../../components/conversation/Conversation";
import Message from "../../components/message/Message";
import {Send} from "@material-ui/icons";
import ChatOnline from "../../components/chatOnline/ChatOnline";

export default function Messenger() {
    return (
        <>
            <Topbar/>
            <div className="messenger">
                <div className={"chatMenu"}>
                    <div className={"chatMenuWrapper"}>
                        <input placeholder={"Search for friends"} className={"chatMenuInput"}/>
                        <Conversation/>

                    </div>
                </div>
                <div className={"chatBox"}>
                    <div className={"chatBoxWrapper"}>
                        <div className={"chatBoxTop"}>
                            <Message/>
                            <Message/>

                            <Message own={true}/>
                            <Message/>
                        </div>

                        <div className={"chatBoxBottom"}>
                            <textarea className={"chatMessageInput"} placeholder={"Aa"}/>
                            <Send className={"chatSubmitButton"}/>
                        </div>
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
