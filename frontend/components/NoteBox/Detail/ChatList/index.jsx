import React, { forwardRef, useEffect, useRef } from 'react';
import { ChatZone } from '@components/NoteBox/Detail/ChatList/styles';
import Scrollbars from 'react-custom-scrollbars';
import Chat from '@components/NoteBox/Detail/Chat';
import { useSelector } from 'react-redux';

const ChatList = forwardRef(({ partner }, scrollRef) => {
  const conversationList = useSelector((state) => state.conversation[partner]);

  return (
    <ChatZone>
      <Scrollbars autoHide ref={scrollRef}>
        {conversationList?.map((chat, index) => index > 0 && <Chat key={chat.send_at} data={chat} />)}
      </Scrollbars>
    </ChatZone>
  );
});

export default ChatList;
