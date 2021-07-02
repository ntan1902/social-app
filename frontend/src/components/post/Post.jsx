import {MoreVert} from "@material-ui/icons";
import {useContext, useEffect, useState} from "react";
import "./post.css";
import {format} from "timeago.js"
import {Link} from "react-router-dom";
import {AuthContext} from "../../context/AuthContext";
import userApi from "../../api/UserApi";
import likeApi from "../../api/LikeApi";

function Post({post}) {
    const [like, setLike] = useState(post.likes.length);
    const [isLiked, setIsLiked] = useState(false);
    const [user, setUser] = useState({});
    const {user: currentUser} = useContext(AuthContext);

    const PF = process.env.REACT_APP_PUBLIC_FOLDER;

    useEffect(() => {
        setIsLiked(post.likes.includes(currentUser.id))
    }, [currentUser.id, post.likes])

    useEffect(() => {
        const fetchUser = async () => {
            const res = await userApi.getById(post.data.userId)
            setUser(res);
        }
        fetchUser();
    }, [post.data.userId])

    const likeHandler = () => {
        try {
            isLiked ?
                likeApi.unlike(post.data.id, currentUser.id) :
                likeApi.like({
                    postId: post.data.id,
                    userId: currentUser.id
                });
        } catch (err) {
            console.log(err);
        }
        setLike(isLiked ? like - 1 : like + 1);
        setIsLiked(!isLiked);
    };

    return (
        <div className="post">
            <div className="postWrapper">
                <div className="postTop">
                    <div className="postTopLeft">
                        <Link to={`/profile/${user.username}`}>

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
