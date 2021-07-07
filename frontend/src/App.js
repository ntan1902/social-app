import Home from "./pages/home/Home";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Profile from "./pages/profile/Profile";

import {BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";
import {useContext} from "react";
import {AuthContext} from "./context/AuthContext";
import Messenger from "./pages/messenger/Messenger";

function App() {
    const {user} = useContext(AuthContext)


    return (
        <Router>
            <Switch>
                <Route exact path="/">
                    {user ? <Home/> : <Login/>}
                </Route>

                <Route path="/login">
                    {user ? <Redirect to="/"/> : <Login/>}
                </Route>

                <Route path="/register">
                    {user ? <Redirect to="/"/> : <Register/>}
                </Route>

                <Route exact path="/messenger">
                    {user ? <Messenger/> : <Login/>}
                </Route>

                <Route exact path="/profile/:username">
                    {user ? <Profile/> : <Login/>}
                </Route>
            </Switch>
        </Router>
    );
}

export default App;
