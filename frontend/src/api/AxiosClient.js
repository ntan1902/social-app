import axios from "axios";
import queryString from "query-string";

const axiosClient = axios.create({
    baseURL: process.env["REACT_APP_API_URL"],
    headers: {
        'content-type': 'application/json',
    },
    paramsSerializer: params => queryString.stringify(params),
});

const refresh = () => {
    const _refreshToken = localStorage.getItem("refreshToken");
    try {
        return  axiosClient.post("/auth/refresh-token", {refreshToken: _refreshToken})

    } catch (err) {
        console.log(err)
    }
}

axiosClient.interceptors.request.use(async (config) => {

    const tokenType = localStorage.getItem("tokenType");
    let accessToken = localStorage.getItem("accessToken");


    config.headers = {
        'Authorization': `${tokenType} ${accessToken}`,
        'Accept': 'application/json'
    }
    return config;
});

axiosClient.setToken = (token) => {
    const tokenType = localStorage.getItem("tokenType");
    axiosClient.defaults.headers = {
        'Authorization': `${tokenType} ${token}`,
        'Accept': 'application/json'
    };

    localStorage.setItem('accessToken', token);
}

axiosClient.interceptors.response.use(response => {
    if (response && response.data) {
        return response.data;
    }
}, async error => {
    if(error.response) {
        const {status} = error.response.data;
        if (status === 401) {
            return refresh().then(rs => {
                axiosClient.setToken(rs.accessToken);

                const config = error.response.config;
                config.headers = {
                    'Authorization': `Bearer ${rs.accessToken}`,
                    'Accept': 'application/json'
                }

                return axiosClient(config);
            });
        }
    } else {
        throw error;
    }
});

export default axiosClient;