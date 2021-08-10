import React, { useState, useCallback, useEffect } from 'react';
import {
  Modal,
  ModalBtn,
  OKBtn,
  NoBtn,
  ModalForm,
  Title,
  Content,
  Container,
  File,
} from '@components/Modal/EventEdit/styles';
import useInput from '@hooks/useInput';
import { useSelector } from 'react-redux';
import axios from 'axios';
import apiController from '@apis/apiController';

const EventEdit = (props) => {
  // const state = useSelector((state) => state.auth.user);
  const eventId = props.eventId;
  const [title, onChangeTitle, setTitle] = useInput(props.post.title);
  const [content, onChangeContent, setContent] = useInput(props.post.content);
  const [img_url, onChangeImg_Url, setImg_Url] = useInput(props.post.img_url);
  const [coupon_id, onChangeCoupon_Id, setCoupon_Id] = useInput(props.post.coupon_id);

  useEffect(() => {
    setTitle(props.post.title);
    setContent(props.post.content);
    setImg_Url(props.post.img_url);
    setCoupon_Id(props.post.coupon_id);
  }, [props]);

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();
      let params = { title, content, img_url: 'a', coupon_id };
      apiController({
        url: `/admin/events/${eventId}`,
        method: 'patch',
        data: params,
      }).then((res) => {
        alert('수정되었습니다.');
        props.setSuccessEdit(true);
      });
    },
    [eventId, title, content, img_url, coupon_id, props],
  );

  const onClickNoBtn = useCallback(
    (e) => {
      e.preventDefault();
      props.setEditModalOpen(false);
    },
    [props],
  );

  return (
    <>
      <Modal editModal={props.editModalOpen} />
      <Container editModal={props.editModalOpen}>
        <ModalForm onSubmit={onSubmit}>
          <h5>제목</h5>
          <Title id="title" type="text" placeholder={title} value={title} onChange={onChangeTitle} />
          <h5>내용</h5>
          <Content id="content" type="text" placeholder={content} value={content} onChange={onChangeContent} />
          <h5>이미지</h5>
          <File>
            <label for="file">첨부파일</label>
            <input type="file" id="file" />
          </File>
          <ModalBtn>
            <OKBtn type="submit">수정</OKBtn>
            <NoBtn onClick={onClickNoBtn}>닫기</NoBtn>
          </ModalBtn>
        </ModalForm>
      </Container>
    </>
  );
};

export default EventEdit;
