import React, { useEffect, useCallback, useState } from 'react';
import { Container, Content, Chats } from '@components/NoteBox/styles';
import Member from '@components/NoteBox/Member';
import Detail from '@components/NoteBox/Detail';
import apiController from '@apis/apiController';
import { useSelector, useDispatch } from 'react-redux';
import { insertPartner } from '@reducers/conversation';
import { Route, Switch } from 'react-router';

const NoteBox = ({ userId, sendToMessage, scrollbarRef }) => {
  const conversationList = useSelector((state) => state.conversation);
  const [memberList, setMemberList] = useState([]);
  const [receiver, setReceiver] = useState([]);

  const dispatch = useDispatch();

  useEffect(() => {
    getConversations();
  }, []);

  const getConversations = useCallback(() => {
    apiController({
      url: `/users/chats/${userId}`,
      method: 'get',
    }).then((res) => {
      if (res.data.message === '조회성공') {
        // console.log(res.data.data);
        // console.log(res.data.data.length);
        setReceiver(res.data.data);
        setMemberList(res.data.data);
        for (const key in res.data.data) {
          dispatch(
            insertPartner({
              partner: res.data.data[key].the_other_user_id,
              list: [res.data.data[key]],
            }),
          );
        }
      }
    });
  }, [userId]);

  if (!memberList) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Container>
        <Content>
          <h4>채팅목록</h4>
          {Object.keys(conversationList).map((key, index) => (
            <Member
              key={key}
              partner={key}
              host={userId}
              roomId={conversationList[key][0].message_room_id}
              nickName={conversationList[key][0].nick_name}
              receiver={receiver[index].nick_name}
              // onClickMember={onClickMember}
            />
          ))}
        </Content>
        <Chats>
          <Switch>
            <Route
              path="/note/:partner/:roomId"
              render={() => <Detail userId={userId} sendToMessage={sendToMessage} scrollbarRef={scrollbarRef} />}
            />
          </Switch>
        </Chats>
      </Container>
    </div>
  );
};

export default NoteBox;
