import React, { memo, useMemo } from 'react';
import { ChatWrapper } from '@components/NoteBox/Detail/Chat/styles';
import formatDate from '@utils/formatDate';

const Chat = ({ data }) => {
  // console.log(data);
  return (
    <ChatWrapper>
      <div className="chat-img">
        <img className="img"></img>
      </div>
      <div className="chat-text">
        <div className="chat-user">
          <b>{data.nick_name}</b>
          <span>{formatDate(data.send_at)}</span>
        </div>
        <p>{data.content}</p>
      </div>
    </ChatWrapper>
  );
};

export default memo(Chat);
