import axios from 'axios';

const refresh_token = JSON.parse(sessionStorage.getItem('refresh_token'));

const instance = axios.create({
  baseURL: '/api',
  timeout: 1000,
});

instance.interceptors.request.use(
  (config) => {
    config.headers['Content-Type'] = 'application/json; charset=utf-8';
    config.headers['Authorization'] = 'Bearer ' + refresh_token;
    return config;
  },
  (err) => {
    console.dir(err);
    return Promise.reject(err);
  },
);

instance.interceptors.response.use(
  (res) => {
    return res.data;
  },
  (err) => {
    console.dir(err);
    return Promise.reject(err);
  },
);

export default instance;
