import "./topbar.css";
import {Chat, Notifications, Person, Search} from "@material-ui/icons";
import {Link} from "react-router-dom";
import {useContext} from "react";
import {AuthContext} from "../../context/AuthContext";

function Topbar() {
    const {user} = useContext(AuthContext)
    console.log(user)
    const PF = process.env.REACT_APP_PUBLIC_FOLDER;

    return (
        <div className="topbarContainer">
            <div className="topbarLeft">
                <Link to="/" style={{textDecoration: "none"}}>
                    <span className="logo">Heyyyyy</span>
                </Link>
            </div>

            <div className="topbarCenter">
                <div className="searchBar">
                    <Search className="searchIcon"/>
                    <input
                        placeholder="Search for friend, post or video"
                        className="searchInput"
                    />
                </div>
            </div>

            <div className="topbarRight">
                <div className="topBarLinks">
                    <span className="topbarLink">Homepage</span>
                    <span className="topbarLink">Timeline</span>
                </div>

                <div className="topbarIcons">
                    <div className="topbarIconItem">
                        <Person/>
                        <span className="topbarIconBadge">1</span>
                    </div>

                    <div className="topbarIconItem">
                        <Chat/>
                        <span className="topbarIconBadge">1</span>
                    </div>

                    <div className="topbarIconItem">
                        <Notifications/>
                        <span className="topbarIconBadge">1</span>
                    </div>
                </div>
                <Link to={`/profile/${user.username}`}>
                    <img src={user ? user.profilePicture : PF + "person/noAvatar.png"} alt=""
                         className="topbarImg"/>
                </Link>
            </div>
        </div>
    );
}

export default Topbar;
