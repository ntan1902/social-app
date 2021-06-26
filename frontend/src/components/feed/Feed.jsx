import Share from "../share/Share";
import Post from "../post/Post";
import "./feed.css";
import {useContext, useEffect, useState} from "react";
import axios from "axios";
import {AuthContext} from "../../context/AuthContext";

function Feed({username}) {
    const [posts, setPosts] = useState([]);
    const {user} = useContext(AuthContext);

    // Run this once
    useEffect(() => {
        const fetchPosts = async () => {

            if (username) {
                const user1 = await axios.get(`/users?username=${username}`);
                const res = await axios.get(`/users/${user1.data.id}/posts`);
                return res.data;

            } else {
                const res = await axios.get(`/users/${user.id}/timeline`);
                return res.data;
            }
        }

        fetchPosts().then(res => setPosts(res));


    }, [username, user.id])
    return (
        <div className="feed">
            <div className="feedWrapper">
                <Share/>
                {posts.map(post => (
                    <Post key={post.data.id} post={post}/>
                ))}
            </div>
        </div>
    );
}

export default Feed;
