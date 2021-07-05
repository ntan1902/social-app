import axiosClient from "./AxiosClient";

const messageApi =  {

    createMessage: (message) => {
        const url = `/user-messages`;
        return axiosClient.post(url, message);
    },
    getAllMessages: (senderId, receiverId) => {
        const url = `/user-messages/${senderId}/${receiverId}`;
        return axiosClient.get(url);
    }
}

export default messageApi;