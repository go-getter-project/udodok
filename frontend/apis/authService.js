import axios from 'axios';
import pwEncrypt from '@utils/pwEncrypt';

const signup = (email, name, nick_name, password, phone_number) => {
  const pwHash = pwEncrypt(password);
  return axios.post(
    '/api/signup',
    {
      email,
      name,
      nick_name,
      password: pwHash,
      phone_number,
    },
    {
      withCredentials: true,
    },
  );
};

const signin = (email, password) => {
  return axios
    .post(
      '/api/signin',
      {
        email,
        password,
      },
      {
        withCredentials: true,
      },
    )
    .then((res) => {
      if (res.data.data) {
        sessionStorage.setItem('nick_name', JSON.stringify(res.data.data.nick_name));
        sessionStorage.setItem('access_token', JSON.stringify(res.data.data.access_token));
        sessionStorage.setItem('refresh_token', JSON.stringify(res.data.data.refresh_token));
        sessionStorage.setItem('user_id', JSON.stringify(res.data.data.user_id));
        sessionStorage.setItem('user_grade', JSON.stringify(res.data.data.user_grade));
      }
      return res.data;
    });
};

const logout = () => {
  sessionStorage.removeItem('nick_name');
  sessionStorage.removeItem('access_token');
  sessionStorage.removeItem('refresh_token');
  sessionStorage.removeItem('user_id');
  sessionStorage.removeItem('user_grade');
};

export default { signup, signin, logout };
