import React from "react";
import "./login.css";

function Login() {
  return (
    <div className="login">
      <div className="loginWrapper">
        <div className="loginLeft">
          <h3 className="loginLogo">heyysocial</h3>
          <span className="loginDesc">
            Connect with friends and the world around you on heyysocial.
          </span>
        </div>
        <div className="loginRight">
          <div className="loginBox">
            <input placeHolder="Email" className="loginInput" />
            <input placeHolder="Password" className="loginInput" />
            <button className="loginButton">Log In</button>
            <span className="loginForgot">Forgot Password ?</span>
            <hr />
            <button className="registerButton">Create a new account</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
