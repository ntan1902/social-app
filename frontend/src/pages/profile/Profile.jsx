import "./profile.css";
import Topbar from "../../components/topbar/Topbar";
import Sidebar from "../../components/sidebar/Sidebar";
import Feed from "../../components/feed/Feed";
import Rightbar from "../../components/rightbar/Rightbar";
import {useEffect, useState} from "react";
import {useParams} from "react-router";
import axios from "axios";

function Profile() {
  const PF = process.env.REACT_APP_PUBLIC_FOLDER;
  const [user, setUser] = useState({});
  const username = useParams().username;

  useEffect(() => {
    const fetchUser = async () => {
      const res = await axios.get(`/users?username=${username}`)
      return res.data;
    }
    fetchUser().then(res => setUser(res));
  }, [username])

  return (
    <>
      <Topbar />
      <div className="profile">
        <Sidebar />
        <div className="profileRight">
          <div className="profileRightTop">
            <div className="profileCover">
              <img
                className="profileCoverImg"
                src={user.coverPicture || PF+"person/noCover.png"}
                alt=""
              />

              <img
                className="profileUserImg"
                src={user.profilePicture || PF+"person/noAvatar.png"}
                alt=""
              />
            </div>

            <div className="profileInfo">
              <h4 className="profileInfoName">{user.username}</h4>

              <div className="profileInfoDesc">
                {user.description}
              </div>
            </div>
          </div>
          <div className="profileRightBottom">
            <Feed username={username}/>
            <Rightbar user={user} />
          </div>
        </div>
      </div>
    </>
  );
}

export default Profile;
