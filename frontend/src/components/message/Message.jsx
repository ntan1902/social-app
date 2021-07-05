import React, {useEffect, useState} from 'react'
import "./message.css"
import {format} from "timeago.js";
import userApi from "../../api/UserApi";

export default function Message({message, own}) {
    const [user, setUser] = useState(null);
    const PF = process.env["REACT_APP_PUBLIC_FOLDER"];

    useEffect(() => {
        const fetchUser = async () => {
            const res = await userApi.getById(message.senderId);
            setUser(res);
        }
        !own && fetchUser();
    }, [message.senderId, own])

    return (
        <div className={own ? "message own" : "message"}>
            <div className={"messageTop"}>
                {
                    !own && <img className={"messageImg"}
                              src={user?.profilePicture || PF+"person/noAvatar.png"}
                              alt={""}/>
                }
                <p className={"messageText"}>{message.content}</p>
            </div>
            <div className={"messageBottom"}>{format(message.createdAt)}</div>
        </div>
    )
}
