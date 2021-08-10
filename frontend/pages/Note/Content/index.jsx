import React, { useRef, useState, useCallback, useEffect } from 'react';
import NoteBox from '@components/NoteBox';
import { Container } from '@pages/Note/Content/styles';
import SockJsClient from 'react-stomp';
import { useDispatch, useSelector } from 'react-redux';
import moment from 'moment';
import Scrollbar from 'react-custom-scrollbars';
import { insertMessage, receive } from '@reducers/conversation';

const Content = ({ userId }) => {
  const socketRef = useRef(null);
  let topics = ['/topic/' + userId];

  const scrollbarRef = useRef(null);

  const dispatch = useDispatch();

  const sendToMessage = (senderId, receiverId, roomId, chat, nickName) => {
    const nowTime = moment().format('YYYY-MM-DD HH:mm:ss');

    const reduxParams = {
      the_other_user_id: receiverId,
      nick_name: nickName,
      content: chat,
      send_at: nowTime,
      message_room_id: roomId,
    };
    const socketParams = {
      sender_id: senderId,
      receiver_id: receiverId,
      message_room_id: roomId,
      content: chat,
    };
    socketRef.current.sendMessage('/app/chat/send', JSON.stringify(socketParams));
    dispatch(insertMessage(reduxParams));
    scrollbarToBottom();
  };

  const receiveMessage = (msg) => {
    dispatch(receive(msg));
    scrollbarToBottom();
  };

  const scrollbarToBottom = () => {
    if (scrollbarRef.current) {
      if (
        scrollbarRef.current.getScrollHeight() <
        scrollbarRef.current.getClientHeight() + scrollbarRef.current.getScrollTop() + 150
      ) {
        setTimeout(() => {
          scrollbarRef.current?.scrollToBottom();
        }, 50);
      }
    }
  };

  return (
    <Container>
      <SockJsClient
        url="/chat"
        topics={topics}
        onConnect={() => console.log('onConnect')}
        onDisconnect={() => console.log('onDisconnect')}
        onMessage={(msg) => receiveMessage(msg)}
        ref={socketRef}
      />
      <NoteBox userId={userId} sendToMessage={sendToMessage} scrollbarRef={scrollbarRef} />
    </Container>
  );
};

export default Content;
