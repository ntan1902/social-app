import axiosClient from "./AxiosClient";

const likeApi = {
    like : (like)  => {
        const url = `/likes`;
        return axiosClient.post(url, like);
    },

    unlike : (postId, userId)  => {
        const url = `/likes/${postId}/${userId}`;
        return axiosClient.delete(url);
    }
}

export default likeApi;