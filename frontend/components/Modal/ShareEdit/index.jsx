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
} from '@components/Modal/ShareEdit/styles';
import useInput from '@hooks/useInput';
import { useSelector } from 'react-redux';
import apiController from '@apis/apiController';

const ShareEdit = (props) => {
  // console.log(props);
  const state = useSelector((state) => state.auth.user);

  const boardId = props.boardId;
  const [title, onChangeTitle, setTitle] = useInput(props.post.title);
  const [bookTitle, onChangeBookTitle, setBookTitle] = useInput(props.post.book_title);
  const [content, onChangeContent, setContent] = useInput(props.post.content);
  const [tag, onChangeTag, setTag] = useInput(props.post.tag_content);

  useEffect(() => {
    setTitle(props.post.title);
    setBookTitle(props.post.book_title);
    setContent(props.post.content);
    setTag(props.post.tag_content);
  }, [props]);

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();

      let params = {
        book_title: bookTitle,
        content,
        sharing_board_tag: tag,
        title,
        user_id: state.user_id,
      };

      apiController({
        url: `/users/sharings?id=${boardId}`,
        method: 'patch',
        data: params,
      }).then((res) => {
        alert('수정되었습니다.');
        props.setSuccessEdit(true);
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
          <Title id="title" type="text" placeholder={title} value={title} onChange={onChangeTitle} />
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

export default ShareEdit;
