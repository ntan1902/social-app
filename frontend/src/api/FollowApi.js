import axiosClient from "./AxiosClient";

const followApi =  {
    follow : (follow)  => {
        const url = `/follows`;
        return axiosClient.post(url, follow);
    },

    unfollow : (userId, followingId)  => {
        const url = `/follows/${userId}/${followingId}`;
        return axiosClient.delete(url);
    },

    getFollowings: (userId) => {
        const url = `/follows/${userId}`;
        return axiosClient.get(url);
    }
}

export default followApi;