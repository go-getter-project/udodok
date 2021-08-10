import React, { useState, useEffect, useCallback } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import {
  Contents,
  EventContainer,
  Container,
  EventHeader,
  EventTab,
  TabArea,
  EventList,
  ListButton,
  EditBtn,
} from '@pages/Event/Detail/styles';
import Paging from '@components/Paging';
import EditModal from '@components/Modal/EventEdit';
import EventDelete from '@components/Modal/EventDelete';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';

const EventDetail = (props) => {
  const users = useSelector((state) => state.auth.user);
  const user_grade = users.user_grade;
  const eventId = props.match.params.id;

  const [post, setPost] = useState([]);
  const [totalElements, setTotalElements] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  const [title, setTitle] = useState();
  const [content, setContent] = useState();
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();

  const [editModalOpen, setEditModalOpen] = useState(false);
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);

  const [successEdit, setSuccessEdit] = useState(false);
  const [successDelete, setSuccessDelete] = useState(false);

  useEffect(() => {
    axios
      .get(`/api/events/${eventId}`, {
        withCredentials: true,
      })
      .then((res) => {
        const data = res.data.data;
        setPost(data);
        setContent(data.content);
        setStartDate(data.start_date);
        setEndDate(data.end_date);
        setTitle(data.title);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  const onClickEditBtn = useCallback((e) => {
    e.preventDefault();
    setEditModalOpen(true);
  }, []);

  const onClickDeleteBtn = useCallback((e) => {
    e.preventDefault();
    setDeleteModalOpen(true);
  }, []);

  if (successEdit) {
    setEditModalOpen(false);
    props.history.push(`/event/now/${eventId}`);
    window.location.reload();
    setSuccessEdit(false);
  }

  if (successDelete) {
    setDeleteModalOpen(false);
    props.history.push('/event/now');
    window.location.reload();
    setSuccessDelete(false);
  }

  return (
    <div style={{ height: '100%' }}>
      <EventDelete
        eventId={eventId}
        deleteModalOpen={deleteModalOpen}
        setDeleteModalOpen={setDeleteModalOpen}
        setSuccessDelete={setSuccessDelete}
      />
      <EditModal
        eventId={eventId}
        post={post}
        editModalOpen={editModalOpen}
        setEditModalOpen={setEditModalOpen}
        setSuccessEdit={setSuccessEdit}
      />
      <EventContainer>
        <Header />
        <Container>
          <EventHeader>이벤트</EventHeader>
          {user_grade === 'ADMIN' ? (
            <EditBtn>
              <i className="far fa-edit" onClick={onClickEditBtn} setEditModalOpen={setEditModalOpen}></i>
              <button className="delete" onClick={onClickDeleteBtn} setDeleteModalOpen={setDeleteModalOpen}>
                &times;
              </button>
            </EditBtn>
          ) : null}
          <Contents>
            <h3>{title}</h3>
            {content}
            <br></br>
            {startDate}~{endDate}
          </Contents>
          <ListButton>
            <Link to="/event/now">
              <button>목록</button>
            </Link>
          </ListButton>
        </Container>
      </EventContainer>
      <Paging></Paging>
      <Footer />
    </div>
  );
};

export default EventDetail;
