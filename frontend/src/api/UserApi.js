import axiosClient from "./axiosClient";

class UserApi {
    getByUsername = (username) => {
        const url = `/users?username=${username}`;
        return axiosClient.get(url);
    }

    getById = (userId) => {
        const url = `/users/${userId}`;
        return axiosClient.get(url);
    }

    getPosts = (userId) => {
        const url = `/users/${userId}/posts`;
        return axiosClient.get(url);
    }

    getTimeLine = (userId) => {
        const url = `/users/${userId}/timeline`;
        return axiosClient.get(url);
    }

    getFollowings = (userId) => {
        const url = `/users/${userId}/followings`;
        return axiosClient.get(url);
    }
}

const userApi = new UserApi();
export default userApi;