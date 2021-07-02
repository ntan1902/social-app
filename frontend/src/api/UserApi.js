import axiosClient from "./AxiosClient";

const userApi = {
    getByUsername: (username) => {
        const url = `/users?username=${username}`;
        return axiosClient.get(url);
    },

    getById: (userId) => {
        const url = `/users/${userId}`;
        return axiosClient.get(url);
    },

    getPosts: (userId) => {
        const url = `/users/${userId}/posts`;
        return axiosClient.get(url);
    },

    getTimeLine: (userId) => {
        const url = `/users/${userId}/timeline`;
        return axiosClient.get(url);
    },

    getFollowings: (userId) => {
        const url = `/users/${userId}/followings`;
        return axiosClient.get(url);
    },
}

export default userApi;