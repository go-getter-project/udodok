import React, { useState, useCallback } from 'react';
import { Container, Form, WriteHeader, Input, TextArea, Button } from '@components/DiscussionEditForm/styles';
import useInput from '@hooks/useInput';
import apiController from '@apis/apiController';
import { Link, Redirect } from 'react-router-dom';
import { useSelector } from 'react-redux';

const EditForm = ({ title, content, Id }) => {
  const users = useSelector((state) => state.auth.user);
  // const [changeTitle, onChangeTitle] = useInput('');
  // const [changeContent, onChangeContent] = useInput('');
  const [titleValue, setTitleValue] = useState(title);
  const [contentValue, setContentValue] = useState(content);

  const onSubmit = (e) => {
    // console.log(users.user_id);
    let params = {
      content: contentValue,
      title: titleValue,
    };

    apiController({
      url: `/users/discussions/edit/${Id}?userId=${users.user_id}`,
      method: 'patch',
      data: params,
    });
  };
  const titleChange = (e) => {
    const val = e.target.value;
    setTitleValue(val);
  };
  const contentChange = (e) => {
    const val = e.target.value;
    setContentValue(val);
  };
  return (
    <div style={{ height: '100%' }}>
      <Container>
        <WriteHeader>게시글 작성</WriteHeader>
        <Form>
          <div id="title">
            제목
            <Input
              id="title"
              name="title"
              placeholder="제목을 입력해주세요."
              value={titleValue}
              onChange={titleChange}
            />
          </div>
          <div id="content">
            본문
            <TextArea
              id="content"
              name="content"
              placeholder="내용을 입력해주세요."
              onChange={contentChange}
              value={contentValue}
            />
          </div>{' '}
          <Link to={{ pathname: `/discussion/content/${Id}` }}>
            <Button type="submit" onClick={onSubmit}>
              수정하기
            </Button>
          </Link>
        </Form>
      </Container>
    </div>
  );
};

export default EditForm;
