import Share from "../share/Share";
import Post from "../post/Post";
import "./feed.css";
import {useContext, useEffect, useState} from "react";
import {AuthContext} from "../../context/AuthContext";
import userApi from "../../api/UserApi";

function Feed({username}) {
    const [posts, setPosts] = useState([]);
    const {user} = useContext(AuthContext);

    // Run this once
    useEffect(() => {
        const fetchPosts = async () => {

            if (username !== user.username) {
                const _user = await userApi.getByUsername(username);
                const res = await userApi.getPosts(_user.id);
                setPosts(res.sort((p1, p2) => {
                    return new Date(p2.data.createdAt) - new Date(p1.data.createdAt);
                }));

            } else {
                const res = await userApi.getTimeLine(user.id)
                setPosts(res.sort((p1, p2) => {
                    return new Date(p2.data.createdAt) - new Date(p1.data.createdAt);
                }));
            }
        }
        fetchPosts();
    }, [username, user.id, user.username])
    return (
        <div className="feed">
            <div className="feedWrapper">
                {username === user.username && <Share/> }
                {posts.map(post => (
                    <Post key={post.data.id} post={post}/>
                ))}
            </div>
        </div>
    );
}

export default Feed;
