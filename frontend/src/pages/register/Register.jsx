import "./register.css";
import { useRef } from "react";
import { useHistory } from "react-router-dom";
import authApi from "../../api/AuthApi";
import { Link } from "react-router-dom";

function Register() {
  const username = useRef();
  const email = useRef();
  const password = useRef();
  const passwordAgain = useRef();
  const history = useHistory();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (passwordAgain.current.value !== password.current.value) {
      passwordAgain.current.setCustomValidity("Password don't match!");
    } else {
      const user = {
        username: username.current.value,
        email: email.current.value,
        password: password.current.value,
      };
      try {
        const res = await authApi.register(user);
        console.log(res);
        history.push("/login");
      } catch (err) {
        console.log(err);
      }
    }
  };

  return (
    <div className="register">
      <div className="registerWrapper">
        <div className="registerLeft">
          <h3 className="registerLogo">heyysocial</h3>
          <span className="registerDesc">
            Connect with friends and the world around you on heyysocial.
          </span>
        </div>
        <div className="registerRight">
          <form className="registerBox" onSubmit={handleSubmit}>
            <input
              placeholder="Username"
              required
              ref={username}
              className="registerInput"
            />
            <input
              placeholder="Email"
              required
              type="email"
              ref={email}
              className="registerInput"
            />
            <input
              placeholder="Password"
              required
              minLength="6"
              type="password"
              ref={password}
              className="registerInput"
            />
            <input
              placeholder="Password again"
              required
              type="password"
              ref={passwordAgain}
              className="registerInput"
            />
            <button className="signupButton" type="submit">
              Sign up
            </button>
            <span className="registerForgot">Forgot Password ?</span>
            <hr />

            <Link to="/login" style={{ alignSelf: "center", width: "70%" }}>
              <button className="gobackButton">Go back</button>{" "}
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Register;
