import axios from 'axios';
import accessTokenUpdate from '@apis/accessTokenUpdate';

const access_token = JSON.parse(sessionStorage.getItem('access_token'));

const instance = axios.create({
  baseURL: '/api',
  withCredentials: true,
});

instance.interceptors.request.use(
  (config) => {
    config.headers['Content-Type'] = 'application/json; charset=utf-8';
    config.headers['Authorization'] = 'Bearer ' + access_token;
    return config;
  },
  (err) => {
    console.dir(err);
    return Promise.reject(err);
  },
);

instance.interceptors.response.use(
  (res) => {
    return res;
  },
  async (err) => {
    // errorController(err);
    console.dir(err);
    const originalRequest = err.config;
    let errorState;
    if (err.response !== undefined) {
      errorState = err.response.data.message;
    }

    if (errorState === '엑세스토큰 불일치') {
      const { data } = await accessTokenUpdate({
        url: '/token',
        method: 'get',
      });

      sessionStorage.setItem('access_token', JSON.stringify(data.access_token));
      const accessToken = data.access_token;

      originalRequest.headers.Authorization = 'Bearer ' + accessToken;
      return axios(originalRequest);
    }
    return Promise.reject(err);
  },
);

export default instance;
