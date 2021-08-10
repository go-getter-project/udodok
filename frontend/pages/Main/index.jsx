import React from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import axios from 'axios';

// axios.interceptors.request.use(
//   (config) => {
//     const token = localStorage.getItem('token');
//     if (token) {
//       config.headers['Authorization'] = 'Bearer ' + token;
//     }
//     return config;
//   },
//   (error) => {
//     Promise.reject(error);
//   },
// );

const Main = () => {
  return (
    <div style={{ height: '100%' }}>
      <div>
        <Header />
        <div>메인 화면입니다.</div>
      </div>
      <Footer />
    </div>
  );
};

export default Main;
