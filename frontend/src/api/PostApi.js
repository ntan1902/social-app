import axiosClient from "./AxiosClient";

const postApi = {

    upload : (formData) => {
        const url = '/posts/upload';
        return axiosClient.post(url, formData)
    },

    createPost : (post) => {
        const url = '/posts';
        return axiosClient.post(url, post);
    }
}

export default postApi;