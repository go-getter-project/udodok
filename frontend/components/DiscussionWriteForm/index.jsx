import React, { useState, useCallback } from 'react';
import { Container, Form, WriteHeader, Input, TextArea, Button } from '@components/DiscussionWriteForm/styles';
import useInput from '@hooks/useInput';
import apiController from '@apis/apiController';
import { Link, useHistory } from 'react-router-dom';
import { useSelector } from 'react-redux';

const WriteForm = () => {
  const users = useSelector((state) => state.auth.user);
  const history = useHistory();
  const [title, onChangeTitle] = useInput('');
  const [content, onChangeContent] = useInput('');

  const onSubmit = (e) => {
    // e.preventDefault();
    let params = {
      content,
      title,
    };

    apiController({
      url: `/users/discussions/${users.user_id}`,
      method: 'post',
      data: params,
    }).then((res) => {
      history.push('disccussion');
    });
  };
  return (
    <div>
      <Container>
        <WriteHeader>게시글 작성</WriteHeader>
        <Form>
          <div id="title">
            제목
            <Input id="title" name="title" placeholder="제목을 입력해주세요." onChange={onChangeTitle} value={title} />
          </div>
          <div id="content">
            본문
            <TextArea
              id="content"
              name="content"
              placeholder="내용을 입력해주세요."
              onChange={onChangeContent}
              value={content}
            />
          </div>
          <Button type="submit" onClick={onSubmit}>
            작성하기
          </Button>
        </Form>
      </Container>
    </div>
  );
};

export default WriteForm;
