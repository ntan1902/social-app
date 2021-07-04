import React from 'react'
import "./message.css"

export default function Message({own}) {
    return (
        <div className={own ? "message own" : "message"}>
            <div className={"messageTop"}>
                <img className={"messageImg"} src={"https://images.pexels.com/photos/3310693/pexels-photo-3310693.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"}/>
                <p className={"messageText"}>Hello this is message</p>
            </div>
            <div className={"messageBottom"}>1 hour ago</div>
        </div>
    )
}
