import axiosClient from "./axiosClient";

class PostApi {

    upload = (formData) => {
        const url = '/posts/upload';
        return axiosClient.post(url, formData)
    }

    createPost = (post) => {
        const url = '/posts';
        return axiosClient.post(url, post);
    }
}

const postApi = new PostApi();
export default postApi;