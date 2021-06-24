import Share from "../share/Share";
import Post from "../post/Post";
import "./feed.css";
import {useEffect, useState} from "react";
import axios from "axios";

function Feed() {
    const [posts, setPosts] = useState([])

    // Run this once
    useEffect(() => {
        const fetchPosts = async () => {
            const res = await axios.get("/users/1/all-posts")
            console.log(res.data)
            setPosts(res.data)
        }

        fetchPosts();

    }, [])
    return (
        <div className="feed">
            <div className="feedWrapper">
                <Share/>
                {posts.map(post => (
                  <Post key={post.data.id} post={post} />
                ))}
            </div>
        </div>
    );
}

export default Feed;
