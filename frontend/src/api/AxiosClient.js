import axios from "axios";
import queryString from "query-string";

const axiosClient = axios.create({
    baseURL: process.env["REACT_APP_API_URL"],
    headers: {
        'content-type': 'application/json',
    },
    paramsSerializer: params => queryString.stringify(params),
});

axiosClient.interceptors.request.use(async (config) => {

    const tokenType = localStorage.getItem("tokenType");
    const accessToken = localStorage.getItem("accessToken");
    config.headers = {
        'Authorization': `${tokenType} ${accessToken}`,
        'Accept': 'application/json'
    }
    return config;
});

axiosClient.interceptors.response.use(response => {
    if (response && response.data) {
        return response.data;
    }
}, error => {
    throw error;
});

export default axiosClient;