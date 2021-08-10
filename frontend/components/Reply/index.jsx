import axios from 'axios';
import React, { useEffect, useState, useCallback } from 'react';
import {
  Containers,
  Container,
  ReplyDiv,
  ReplyContents,
  ReplyUser,
  ReplyContent,
  ReplyWrite,
} from '@components/Reply/styles';
import { useSelector } from 'react-redux';
import useInput from '@hooks/useInput';
import apiController from '@apis/apiController';

const Reply = ({ discussionId }) => {
  const users = useSelector((state) => state.auth.user);
  const currentId = users.user_id;
  const [reply, setReply] = useState();
  const [count, setCount] = useState(0);
  const [content, setContent] = useInput('');

  useEffect(() => {
    apiController({
      url: `/bkusers/discussionreplies/${discussionId}`,
      method: 'get',
    }).then((res) => {
      if (res.data.data) {
        const data = res.data.data.content;
        const page = res.data.pagination.total_elements;
        setCount(page);
        setReply(data);
      }
    });
  }, []);

  const onSubmit = (e) => {
    let params = {
      content,
    };

    apiController({
      url: `/users/discussionreplies/${discussionId}?userId=${currentId}`,
      method: 'post',
      data: params,
    }).then((res) => {
      window.location.reload();
    });
  };

  return (
    <div>
      <Containers>
        <Container>
          <div>
            전체 댓글 <span>{count}</span>
          </div>
          <ReplyDiv>
            {reply?.map((item, idx) => {
              return <ReplyItem key={idx} item={item} discussionId={discussionId} currentId={currentId} />;
            })}
          </ReplyDiv>
          <span>답글 쓰기</span>
          <ReplyWrite>
            <textarea value={content} onChange={setContent}></textarea>
            <button onClick={onSubmit}>등록</button>
          </ReplyWrite>
        </Container>
      </Containers>
    </div>
  );
};
export default Reply;

const ReplyItem = ({ item, discussionId, currentId }) => {
  const { id, user_id, user_nick_name, content, create_at } = item;

  const onDelete = useCallback(
    (e) => {
      axios
        .delete(`/api/users/discussionreplies/${discussionId}?replyId=${id}&userId=${currentId}`, {
          withCredentials: true,
        })
        .then(() => {
          window.location.reload();
        })
        .catch((err) => {
          console.dir(err);
        });
    },
    [discussionId, id, currentId], // 넣어줘야함
  );
  return (
    <ReplyContents>
      <ReplyUser>
        <i className="fas fa-user-circle"></i>
        <div>
          <div className="nickName">{user_nick_name}</div>
          <div className="date">{create_at}</div>
        </div>
      </ReplyUser>
      <ReplyContent>
        {user_id === currentId ? (
          <button className="delete" onClick={onDelete}>
            &times;
          </button>
        ) : null}
        <div>{content}</div>
      </ReplyContent>
    </ReplyContents>
  );
};
