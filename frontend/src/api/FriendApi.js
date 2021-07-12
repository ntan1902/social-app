import axiosClient from "./AxiosClient";

const friendApi =  {
    friend : (friend)  => {
        const url = `/friends`;
        return axiosClient.post(url, friend);
    },

    unfriend : (userId, friendId)  => {
        const url = `/friends/${userId}/${friendId}`;
        return axiosClient.delete(url);
    },

    getFriends: (userId) => {
        const url = `/friends/${userId}`;
        return axiosClient.get(url);
    }
}

export default friendApi;