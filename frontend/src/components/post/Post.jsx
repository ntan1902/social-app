import {MoreVert} from "@material-ui/icons";
import {useEffect, useState} from "react";
import "./post.css";
import axios from "axios";
import {format} from "timeago.js"
import {Link} from "react-router-dom";

function Post({post}) {
    const [like, setLike] = useState(post.likes.length);
    const [isLiked, setIsLiked] = useState(false);
    const [user, setUser] = useState({});

    const PF = process.env.REACT_APP_PUBLIC_FOLDER;

    useEffect(() => {
        const fetchUser = async () => {
            const res = await axios.get(`/users/${post.data.userId}`)
            return res.data;
        }
        fetchUser().then(res => setUser(res));
    }, [post.data.userId])

    const likeHandler = () => {
        setLike(isLiked ? like - 1 : like + 1);
        setIsLiked(!isLiked);
    };

    return (
        <div className="post">
            <div className="postWrapper">
                <div className="postTop">
                    <div className="postTopLeft">
                        <Link to={`profile/${user.username}`}>

                            <img
                                className="postProfileImg"
                                src={
                                    user.profilePicture || PF + "person/noAvatar.png"
                                }
                                alt=""
                            />
                        </Link>
                        <span className="postUsername">
                            {user.username}
                        </span>
                        <span className="postDate">
                            {format(post.data.createdAt)}
                        </span>
                    </div>

                    <div className="postTopRight">
                        <MoreVert/>
                    </div>
                </div>

                <div className="postCenter">
                    <span className="postText">{post?.data.description}</span>
                    <img className="postImg" src={post.data.img} alt=""/>
                </div>

                <div className="postBottom">
                    <div className="postBottomLeft">
                        <img
                            className="likeIcon"
                            src={`${PF}like.png`}
                            onClick={likeHandler}
                            alt=""
                        />
                        <img
                            className="likeIcon"
                            src={`${PF}heart.png`}
                            onClick={likeHandler}
                            alt=""
                        />
                        <span className="postLikeCounter">{like} people liked it</span>
                    </div>
                    <div className="postBottomRight">
                        <span className="postCommentText">{post.data.comment} comments</span>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Post;
