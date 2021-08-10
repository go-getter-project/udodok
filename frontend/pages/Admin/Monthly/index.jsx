import React, { PureComponent, useState, useEffect } from 'react';
import Header from '@layouts/Header';
import AdminTab from '@components/AdminTab';
import Footer from '@layouts/Footer';
import { Container, AllDiv, Content } from '@pages/Admin/Monthly/styles';
import axios from 'axios';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

const Monthly = () => {
  const [post, setPost] = useState();
  let now = new Date(); // 현재 날짜 및 시간
  let year = now.getFullYear(); // 연도
  useEffect(() => {
    axios
      .get('/api/admin/join-visuallization', {
        withCredentials: true,
        params: {
          year,
        },
      })
      .then((res) => {
        const data = res.data.data;
        data.sort((a, b) => {
          return a.month - b.month;
        });
        setPost(data);
      });
  }, []);
  return (
    <div style={{ height: '100%' }}>
      <Container>
        <Header />
        <AllDiv>
          <AdminTab />
          <Content>
            <LineChart
              width={900}
              height={370}
              data={post}
              margin={{
                top: 5,
                right: 30,
                left: 20,
                bottom: 5,
              }}
            >
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="month" />
              <YAxis dataKey="count" />
              <Tooltip />
              <Legend />
              <Line type="monotone" dataKey="count" stroke="#8884d8" activeDot={{ r: 8 }} />
            </LineChart>
          </Content>
        </AllDiv>
      </Container>
      <Footer />
    </div>
  );
};

export default Monthly;
