import React from 'react'
import "./chatOnline.css"

export default function ChatOnline() {
    return (
        <div className={"chatOnline"}>
            <div className={"chatOnlineFriend"}>
                <div className={"chatOnlineImgContainer"}>
                    <img className={"chatOnlineImg"} src={"https://images.pexels.com/photos/3310693/pexels-photo-3310693.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"} alt={""}/>
                    <div className={"chatOnlineBadge"}/>
                </div>
                <span className={"chatOnlineName"}>John Doe</span>
            </div>
        </div>
    )
}
