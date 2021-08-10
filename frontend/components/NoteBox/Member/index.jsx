import React from 'react';
import { Container, Top, Bottom } from '@components/NoteBox/Member/styles';
import { useSelector } from 'react-redux';
import formatDate from '@utils/formatDate';
import { NavLink } from 'react-router-dom';

const Member = ({ partner, host, roomId, nickName, receiver, onClickMember }) => {
  const data = useSelector((state) => state.conversation[partner][state.conversation[partner].length - 1]);

  const sendMemberData = () => {
    onClickMember(partner, roomId, nickName);
  };

  return (
    <div>
      <NavLink key={partner} to={`/note/${partner}/${roomId}`}>
        <Container>
          <Top>
            <h5>{receiver}</h5>
            <span>{formatDate(data.send_at)}</span>
          </Top>
          <Bottom>{data.content}</Bottom>
        </Container>
      </NavLink>
    </div>
  );
};

export default Member;
