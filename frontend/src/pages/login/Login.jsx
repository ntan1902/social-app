import React, { useContext, useRef } from "react";
import "./login.css";
import authApi from "../../api/AuthApi";
import { AuthContext } from "../../context/AuthContext";
import { CircularProgress } from "@material-ui/core";
import {
  LoginFailure,
  LoginStart,
  LoginSuccess,
} from "../../context/AuthActions";
import userApi from "../../api/UserApi";
import { Link } from "react-router-dom";

function Login() {
  const username = useRef();
  const password = useRef();
  const { isFetching, dispatch } = useContext(AuthContext);

  const handleSubmit = async (e) => {
    e.preventDefault();
    dispatch(LoginStart());
    try {
      const res = await authApi.login({
        username: username.current.value,
        password: password.current.value,
      });
      localStorage.setItem("tokenType", res.tokenType);
      localStorage.setItem("accessToken", res.accessToken);
      localStorage.setItem("refreshToken", res.refreshToken);

      const user = await userApi.getProfile();
      dispatch(LoginSuccess(user));
    } catch (err) {
      dispatch(LoginFailure(err));
    }
  };

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
            <input
              placeholder="Username"
              type="username"
              required
              className="loginInput"
              ref={username}
            />
            <input
              placeholder="Password"
              type="password"
              required
              minLength="6"
              className="loginInput"
              ref={password}
            />
            <button className="loginButton" type="submit" disabled={isFetching}>
              {isFetching ? (
                <CircularProgress color="primary" size="20px" />
              ) : (
                "Log In"
              )}
            </button>
            <span className="loginForgot">Forgot Password ?</span>
            <hr />
            <Link to={"register"} style={{ alignSelf: "center", width: "70%" }}>
              <button className="registerButton">
                {" "}
                {isFetching ? (
                  <CircularProgress color="white" size="20px" />
                ) : (
                  "Create a New Account"
                )}
              </button>
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
