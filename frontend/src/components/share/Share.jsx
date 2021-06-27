import { PermMedia, Label, Room, EmojiEmotions } from "@material-ui/icons";
import React, {useContext, useRef, useState} from "react";
import "./share.css";
import {AuthContext} from "../../context/AuthContext";
import axios from "axios";

function Share() {

  const PF = process.env["REACT_APP_PUBLIC_FOLDER "];
  const {user} = useContext(AuthContext);
  const description = useRef();
  const [file, setFile] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newPost = {
      userId: user.id,
      description: description.current.value,
    }

    if(file) {
      const data = new FormData();
      data.append("file", file);

      newPost.img = (await axios.post("/posts/upload", data)).data;
    }


    try {
      await axios.post("/posts", newPost)
      window.location.reload()
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <div className="share">
      <div className="shareWrapper">
        <div className="shareTop">
          <img className="shareProfileImg" src={user.profilePicture || PF + "person/noAvatar.png"} alt="" />
          <input placeholder={"What's in your mind " + user.username + "?"} className="shareInput" ref={description} />
        </div>

        <hr className="shareHr" />

        <form className="shareBottom" onSubmit={handleSubmit}>
          <div className="shareOptions">
            <label htmlFor={"file"} className="shareOption">
              <PermMedia htmlColor="tomato" className="shareIcon" />
              <span className="shareOptionText">Photo or Video</span>
              <input hidden={true} type="file" id={"file"} accept={".png,.jpeg,.jpg"} onChange={(e) => setFile(e.target.files[0])}/>
            </label>

            <div className="shareOption">
              <Label htmlColor="blue" className="shareIcon" />
              <span className="shareOptionText">Tag</span>
            </div>

            <div className="shareOption">
              <Room htmlColor="green" className="shareIcon" />
              <span className="shareOptionText">Location</span>
            </div>

            <div className="shareOption">
              <EmojiEmotions htmlColor="goldenrod" className="shareIcon" />
              <span className="shareOptionText">Feelings</span>
            </div>
          </div>
          <button className="shareButton" type={"submit"}>Share</button>
        </form>
      </div>
    </div>
  );
}

export default Share;
