import Topbar from "../../components/topbar/Topbar";
import Sidebar from "../../components/sidebar/Sidebar";
import Feed from "../../components/feed/Feed";
import Rightbar from "../../components/rightbar/Rightbar";
import "./home.css";
import {useContext} from "react";
import {AuthContext} from "../../context/AuthContext";

function Home() {
    const {user} = useContext(AuthContext);

    return (
        <>
            <Topbar/>
            <div className="homeContainer">
                <Sidebar/>
                <Feed username={user.username} isHome={true}/>
                <Rightbar/>
            </div>
        </>
    );
}

export default Home;
