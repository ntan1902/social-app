import "./register.css";

function Register() {
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
          <div className="registerBox">
            <input placeholder="Username" className="registerInput" />
            <input placeholder="Email" className="registerInput" />
            <input placeholder="Password" className="registerInput" />
            <input placeholder="Password again" className="registerInput" />
            <button className="signupButton">Sign up</button>
            <span className="registerForgot">Forgot Password ?</span>
            <hr />
            <button className="gobackButton">Go back</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Register;
