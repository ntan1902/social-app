import axiosClient from "./axiosClient";

class AuthApi {
    login = (userCredential) => {
        const url = `/auth/login`;
        return axiosClient.post(url, userCredential);
    }

    register = (user) => {
        const url = '/auth/register';
        return axiosClient.post(url, user);
    }
}

const authApi = new AuthApi();
export default authApi;
