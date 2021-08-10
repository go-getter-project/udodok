import React, { useState, useCallback, useEffect } from 'react';
import {
  Modal,
  ModalBtn,
  OKBtn,
  NoBtn,
  ModalForm,
  Title,
  Content,
  Tag,
  Container,
} from '@components/Modal/RecordEdit/styles';
import useInput from '@hooks/useInput';
import { useSelector } from 'react-redux';
import apiController from '@apis/apiController';

const RecordEdit = (props) => {
  // console.log(props);
  const state = useSelector((state) => state.auth.user);

  const boardId = props.boardId;
  const [title, onChangeTitle, setTitle] = useInput(props.post.title);
  const [bookTitle, onChangeBookTitle, setBookTitle] = useInput(props.post.book_name);
  const [content, onChangeContent, setContent] = useInput(props.post.content);
  const [tag, onChangeTag, setTag] = useInput(props.post.book_report_tag);

  useEffect(() => {
    setTitle(props.post.title);
    setBookTitle(props.post.book_name);
    setContent(props.post.content);
    setTag(props.post.book_report_tag);
  }, [props]);

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();

      let params = {
        book_name: bookTitle,
        content,
        tag_name: tag,
      };

      apiController({
        url: `/bkusers/book-reports/${boardId}?userId=${state.user_id}`,
        method: 'patch',
        data: params,
      }).then((res) => {
        props.setSuccessEdit(true);
        alert('수정되었습니다.');
      });
    },
    [boardId, title, bookTitle, content, tag, state, props],
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
          <h5>글 제목</h5>
          <Title id="title" type="text" placeholder={title} value={title} onChange={onChangeTitle} disabled />
          <h5>책 제목</h5>
          <Title id="book_title" type="text" placeholder={bookTitle} value={bookTitle} onChange={onChangeBookTitle} />
          <h5>내용</h5>
          <Content id="content" type="text" placeholder={content} value={content} onChange={onChangeContent} />
          <h5>태그</h5>
          <Tag id="tag" type="text" placeholder={tag} value={tag} onChange={onChangeTag} />
          <ModalBtn>
            <OKBtn type="submit">수정</OKBtn>
            <NoBtn onClick={onClickNoBtn}>닫기</NoBtn>
          </ModalBtn>
        </ModalForm>
      </Container>
    </>
  );
};

export default RecordEdit;
