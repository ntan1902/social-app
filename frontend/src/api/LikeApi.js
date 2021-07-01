import axiosClient from "./axiosClient";

class LikeApi {
    like = (like)  => {
        const url = `/likes`;
        return axiosClient.post(url, like);
    }

    unlike = (postId, userId)  => {
        const url = `/likes/${postId}/${userId}`;
        return axiosClient.delete(url);
    }
}

const likeApi = new LikeApi();
export default likeApi;