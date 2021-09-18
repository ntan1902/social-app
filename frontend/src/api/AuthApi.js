import axiosClient from "./AxiosClient";

class AuthApi {
  login = (userCredential) => {
    const url = "/users/login";
    return axiosClient.post(url, userCredential);
  };

  register = (user) => {
    const url = "/users/register";
    return axiosClient.post(url, user);
  };
}

const authApi = new AuthApi();
export default authApi;
