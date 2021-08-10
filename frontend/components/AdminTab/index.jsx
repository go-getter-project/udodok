import React, { useState } from 'react';
import { LinkClicked } from '@components/AdminTab/styles';
import { Link, useHistory } from 'react-router-dom';
import { TabDiv } from '@components/AdminTab/styles';

const Tab = () => {
  const pathname = window.location.pathname;
  const history = useHistory();
  const onClickAdmin = (e) => {
    history.push('/admin');
    window.location.reload();
  };

  const onClickMonthly = (e) => {
    history.push('/admin/monthly');
    window.location.reload();
  };
  return (
    <TabDiv>
      <h2>관리자 페이지</h2>
      <ul>
        <LinkClicked isActive={!pathname.includes('/admin/month')}>
          <li onClick={onClickAdmin}>회원 관리</li>
        </LinkClicked>
        <LinkClicked isActive={pathname.includes('/admin/month')}>
          <li onClick={onClickMonthly}>월별 회원가입 수</li>
        </LinkClicked>
      </ul>
    </TabDiv>
  );
};

export default Tab;
