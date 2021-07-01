import axiosClient from "./axiosClient";

class FollowApi {
    follow = (follow)  => {
        const url = `/follows`;
        return axiosClient.post(url, follow);
    }

    unfollow = (userId, followingId)  => {
        const url = `/follows/${userId}/${followingId}`;
        return axiosClient.delete(url);
    }
}

const followApi = new FollowApi();
export default followApi;