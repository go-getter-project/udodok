import React, { useCallback, useRef, useState, useEffect } from 'react';
import { Container, Top } from '@components/NoteBox/Detail/styles';
import ChatBox from '@components/NoteBox/Detail/ChatBox';
import ChatList from '@components/NoteBox/Detail/ChatList';
import useInput from '@hooks/useInput';
import apiController from '@apis/apiController';
import { useSelector, useDispatch } from 'react-redux';
import { useParams } from 'react-router';
import { conversationList } from '@reducers/conversation';

const Detail = ({ userId, sendToMessage, scrollbarRef }) => {
  const data = useSelector((state) => state);
  const nickName = data.auth.user.nick_name;
  const { partner, roomId } = useParams(null);

  const [chat, onChangeChat, setChat] = useInput('');

  const dispatch = useDispatch();

  const onSubmitForm = useCallback(
    (e) => {
      e.preventDefault();
      // console.log(userId, partner, roomId, chat, nickName);
      // 통신
      sendToMessage(userId, partner, roomId, chat, nickName);
      setChat('');
    },
    [userId, partner, roomId, chat, nickName],
  );

  useEffect(() => {
    getChatList();
  }, [roomId]);

  const getChatList = useCallback(() => {
    apiController({
      url: `users/chats/detail/${roomId}`,
      method: 'get',
    }).then((res) => {
      // console.log(res.data.data);
      if (data.conversation[partner].length === 1) {
        for (const key in res.data.data) {
          let params = {
            the_other_user_id: partner,
            nick_name: res.data.data[key].send_nick_name,
            content: res.data.data[key].content,
            send_at: res.data.data[key].send_at,
            message_room_id: roomId,
          };

          dispatch(
            conversationList({
              partner: partner,
              list: params,
            }),
          );
        }
      }
    });
  }, [partner, roomId, data]);

  return (
    <div>
      <Container>
        <Top>
          <h4>닉네임</h4>
        </Top>
        <ChatList partner={partner} ref={scrollbarRef} />
        <ChatBox chat={chat} onChangeChat={onChangeChat} onSubmitForm={onSubmitForm} />
      </Container>
    </div>
  );
};

export default Detail;
