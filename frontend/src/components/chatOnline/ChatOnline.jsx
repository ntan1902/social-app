import React, {useEffect, useState} from 'react'
import "./chatOnline.css"
import userApi from "../../api/UserApi";

export default function ChatOnline({onlineUserId}) {
    const [friend, setFriend] = useState(null);
    const PF = process.env.REACT_APP_PUBLIC_FOLDER;
    useEffect(() => {
        const fetchUser = async () => {
            const res = await userApi.getById(onlineUserId);
            setFriend(res);
        }
        fetchUser()
    }, [onlineUserId])
    return (
        <div className={"chatOnline"}>
            <div className={"chatOnlineFriend"}>
                <div className={"chatOnlineImgContainer"}>
                    <img className={"chatOnlineImg"} src={friend?.profilePicture || PF + "person/noAvatar.png"} alt={""}/>
                    <div className={"chatOnlineBadge"}/>
                </div>
                <span className={"chatOnlineName"}>{friend?.username}</span>
            </div>
        </div>
    )
}
