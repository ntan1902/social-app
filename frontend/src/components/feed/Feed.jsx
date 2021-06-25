import Share from "../share/Share";
import Post from "../post/Post";
import "./feed.css";
import {useEffect, useState} from "react";
import axios from "axios";

function Feed({username}) {
    const [posts, setPosts] = useState([])

    // Run this once
    useEffect(() => {
        const fetchPosts = async () => {

            if (username) {
                const user = await axios.get(`/users?username=${username}`);
                const res = await axios.get(`/users/${user.data.id}/posts`);
                return res.data;

            } else {
                const res = await axios.get("/users/1/posts");
                return res.data;
            }
        }

        fetchPosts().then(res => setPosts(res));


    }, [])
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
