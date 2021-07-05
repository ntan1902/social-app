import React, {useEffect, useState} from 'react'
import "./conversation.css"
import userApi from "../../api/UserApi";
export default function Conversation({userId}) {
    const [user, setUser] = useState({});
    const PF = process.env["REACT_APP_PUBLIC_FOLDER"];

    useEffect(() => {
        const fetchUser = async () => {
            const res = await userApi.getById(userId);
            setUser(res);
        }
        fetchUser();
    },[userId])

    return (
        <div className="conversation">
            <img className={"conversationImg"} src={user.profilePicture || PF+"person/noAvatar.png"} alt={""}/>
            <span className={"conversationName"}>{user.username}</span>
        </div>
    )
}
