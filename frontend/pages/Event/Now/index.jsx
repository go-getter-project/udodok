import React, { useState, useEffect } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import { EventContainer, Container, EventHeader, EventTab, TabArea, EventList } from '@pages/Event/Now/styles';
import Paging from '@components/Paging';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';

const NowEvent = ({ history, props }) => {
  const users = useSelector((state) => state.auth.user);
  const user_grade = users.user_grade;
  const [post, setPost] = useState();
  const [totalElements, setTotalElements] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  useEffect(() => {
    axios
      .get(`/api/events`, {
        withCredentials: true,
      })
      .then((res) => {
        const data = res.data.data.content;
        setPost(data);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);
  const onEndEvent = (e) => {
    history.push('/event/end');
  };
  return (
    <div style={{ height: '100%' }}>
      <EventContainer>
        <Header />
        <Container>
          <EventHeader>이벤트</EventHeader>
          {user_grade === 'ADMIN' ? (
            <Link to={'/event/write'}>
              <i className="fas fa-plus"></i>
            </Link>
          ) : null}
          <EventTab>
            <li className="on">진행중인 이벤트</li>
            <li onClick={onEndEvent}>종료된 이벤트</li>
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

export default NowEvent;

const EventLists = ({ item }) => {
  const { title, start_date, end_date, id } = item;
  return (
    <EventList>
      <div>
        <Link
          to={{
            pathname: `/event/now/${id}`,
          }}
        >
          <img src="/images/placeholder.png"></img>
        </Link>
        <span className="evt_title">{title}</span>
        <em className="evt_date">
          {start_date}~{end_date}
        </em>
      </div>
    </EventList>
  );
};
