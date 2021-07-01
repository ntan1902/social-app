import "./rightbar.css";
import {Users} from "../../dummyData";
import Online from "../online/Online";
import {useContext, useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {AuthContext} from "../../context/AuthContext";
import {Add, Remove} from "@material-ui/icons";
import userApi from "../../api/UserApi";
import followApi from "../../api/FollowApi";

function Rightbar({user}) {
    const PF = process.env.REACT_APP_PUBLIC_FOLDER;
    const [friends, setFriends] = useState([]);
    const [followed, setFollowed] = useState(false);
    const {user: currentUser} = useContext(AuthContext);

    useEffect(() => {
        const fetchFriends = async () => {
            try {
                const res = await userApi.getFollowings(user?.id)
                setFriends(res);
            } catch (err) {
                console.log(err);
            }
        }
        if(user) fetchFriends();
    }, [user])

    useEffect(() => {
        const fetchCurrentUserFriends = async () => {
            const res = await userApi.getFollowings(currentUser?.id);
            setFollowed(res?.some(currentUserFriend => currentUserFriend.id === user?.id))
        }

        fetchCurrentUserFriends();

    }, [currentUser.id, user])

    const handleClick = () => {
        try {
            followed ?
                followApi.unfollow(currentUser.id, user.id) :
                followApi.follow({
                    userId: currentUser.id,
                    followingId: user.id
                });
        } catch (err) {
            console.log(err);
        }
        setFollowed(!followed);
    }

    const HomeRightbar = () => {
        return (
            <>
                <div className="birthdayContainer">
                    <img className="birthdayImg" src={`${PF}gift.png`} alt=""/>
                    <span className="birthdayText">
            <b>Pola Foster</b> and <b>3 other friends</b>have a birthday today
          </span>
                </div>
                <img className="rightbarAd" src={`${PF}ad.png`} alt=""/>
                <h4 className="rightbarTitle">Online Friends</h4>
                <ul className="rightbarFriendList">
                    {Users.map((user) => (
                        <Online key={user.id} user={user}/>
                    ))}
                </ul>
            </>
        );
    };

    const ProfileRightbar = () => {
        return (
            <>
                {user.username !== currentUser.username && (
                    <button className={"rightbarFollowButton"} onClick={handleClick}>
                        {followed ? "Unfollow" : "Follow"}
                        {followed ? <Remove/> : <Add/>}
                    </button>
                )}
                <h4 className="rightbarTitle">User infomation</h4>
                <div className="rightbarInfo">
                    <div className="rightbarInfoItem">
                        <span className="rightbarInfoKey">City:</span>
                        <span className="rightbarInfoValue">{user.city}</span>
                    </div>

                    <div className="rightbarInfoItem">
                        <span className="rightbarInfoKey">From:</span>
                        <span className="rightbarInfoValue">{user.fromCity}</span>
                    </div>

                    <div className="rightbarInfoItem">
                        <span className="rightbarInfoKey">Relationship:</span>
                        <span className="rightbarInfoValue">{user.relationship}</span>
                    </div>
                </div>

                <h4 className="rightbarTitle">User friends</h4>
                <div className="rightbarFollowings">
                    {friends.map(friend => (
                        <Link style={{textDecoration: "none"}} to={"/profile/" + friend.username} key={friend.id}>
                            <div className="rightbarFollowing">
                                <img
                                    src={friend.profilePicture || PF + "person/noAvatar.png"}
                                    alt=""
                                    className="rightbarFollowingImg"
                                />
                                <span className="rightbarFollowingName">{friend.username}</span>
                            </div>
                        </Link>
                    ))}

                </div>
            </>
        );
    };
    return (
        <div className="rightbar">
            <div className="rightbarWrapper">
                {user ? <ProfileRightbar/> : <HomeRightbar/>}
            </div>
        </div>
    );
}

export default Rightbar;
