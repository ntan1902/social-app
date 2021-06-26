import {createContext, useReducer} from "react";
import AuthReducer from "./AuthReducer";

const INITIAL_STATE = {
    user: {
        city: "Tp. Ho Chi Minh",
        coverPicture: "https://images.unsplash.com/photo-1500099817043-86d46000d58f?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80",
        description: "Hi, I'm An",
        email: "ntan1902@gmail.com",
        fromCity: "Tp. Ho Chi Minh",
        id: 1,
        profilePicture: "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80",
        relationship: "SINGLE",
        userRole: "USER",
        username: "ntan1902"
    },
    isFetching: false,
    error: false
}

export const AuthContext = createContext(INITIAL_STATE);
export const AuthContextProvider = ({children}) => {
    const [state, dispatch] = useReducer(AuthReducer, INITIAL_STATE);
    return (
        <AuthContext.Provider value={{user: state.user, isFetching: state.isFetching, error: state.error, dispatch}}>
            {children}
        </AuthContext.Provider>
    )
}