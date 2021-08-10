import React, { useState, useEffect } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import { EventContainer, Container, EventHeader, EventTab, TabArea, EventList } from '@pages/Event/End/styles';
import Paging from '@components/Paging';
import axios from 'axios';

const EndEvent = () => {
  const [post, setPost] = useState();
  const [totalElements, setTotalElements] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  useEffect(() => {
    axios
      .get(`/api/end-events`, {
        withCredentials: true,
      })
      .then((res) => {
        const data = res.data.data.content;
        console.log(data);
        setPost(data);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  const onNowEvent = (e) => {
    window.location.replace('/event/now');
  };
  return (
    <div style={{ height: '100%' }}>
      <EventContainer>
        <Header />
        <Container>
          <EventHeader>이벤트</EventHeader>
          <EventTab>
            <li onClick={onNowEvent}>진행중인 이벤트</li>
            <li className="on">종료된 이벤트</li>
          </EventTab>
          <TabArea>
            {post?.map((item, idx) => {
              return <EventLists key={idx} item={item} />;
            })}
          </TabArea>
        </Container>
      </EventContainer>
      <Paging></Paging>
      <Footer />
    </div>
  );
};

export default EndEvent;

const EventLists = ({ item }) => {
  const { title, start_date, end_date, id } = item;
  return (
    <EventList>
      <div>
        <img src="/images/placeholder.png"></img>
        <span className="evt_title">{title}</span>
        <em className="evt_date">
          {start_date}~{end_date}
        </em>
      </div>
    </EventList>
  );
};
