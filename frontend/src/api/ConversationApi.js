import axiosClient from "./AxiosClient";

const conversationApi =  {
    getAllConversations: (userId) => {
        const url = `/user-conversations/${userId}`;
        return axiosClient.get(url);
    }
}

export default conversationApi;