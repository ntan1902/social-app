import React, {useContext, useRef} from "react";
import "./login.css";
import {loginCall} from "../../apiCalls";
import {AuthContext} from "../../context/AuthContext";
import {CircularProgress} from "@material-ui/core";

function Login() {
    const email = useRef();
    const password = useRef();
    const {user, isFetching, error, dispatch} = useContext(AuthContext);

    const handleSubmit = (e) => {
        e.preventDefault();
        loginCall({email: email.current.value, password: password.current.value}, dispatch)
    }

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
                    <form className="loginBox" onSubmit={handleSubmit}>
                        <input placeholder="Email" type="email" required className="loginInput" ref={email}/>
                        <input placeholder="Password" type="password" required minLength="6" className="loginInput"
                               ref={password}/>
                        <button className="loginButton" disabled={isFetching}>
                            {
                                isFetching ?
                                    <CircularProgress color="primary" size="20px"/>
                                    :
                                    "Log In"
                            }
                        </button>
                        <span className="loginForgot">Forgot Password ?</span>
                        <hr/>
                        <button className="registerButton">Create a new account</button>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default Login;
