import React from 'react';
import { Link } from 'react-router-dom';
import { TabDiv } from '@components/Tab/styles';

const Tab = () => {
  return (
    <TabDiv>
      <h2>마이 페이지</h2>
      <ul>
        <Link to="/mypage/profile">
          <li className="select">프로필</li>
        </Link>
        {/* <Link to="/mypage/note">
          <li>쪽지함</li>
        </Link> */}
      </ul>
    </TabDiv>
  );
};

export default Tab;
