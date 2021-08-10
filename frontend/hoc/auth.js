import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { auth } from '@actions/auth';

export default (SpecificComponent, permission) => {
  /* 

    우도독) permission:
              GUEST -> 비회원
              USER -> 로그인한 유저
              ADMIN -> 관리자
              BLACK -> 블랙리스트
  */

  const AuthenticateCheck = (props) => {
    const userId = useSelector((state) => state.auth.user);
    const dispatch = useDispatch();

    if (userId === null) {
      if (permission !== 'GUEST') {
        alert('접근 권한이 없습니다.');
        props.history.push('/');
        window.location.reload();
      } else if (userId) {
      }
    } else {
      // useEffect(() => {
      //   dispatch(auth(userId.user_id)).then((res) => {
      //     const userPermission = res.permission;
      //     if (permission === 'GUEST') {
      //       props.history.push('/');
      //       window.location.reload();
      //     }
      //   });
      // }, []);
      const userPermission = userId.user_grade;
      if (permission === 'GUEST') {
        props.history.push('/');
        window.location.reload();
      } else if (permission === 'ADMIN') {
        if (userPermission !== 'ADMIN') {
          alert('접근 권한이 없습니다.');
          props.history.push('/');
          window.location.reload();
        }
      }
    }

    return <SpecificComponent props={props} />;
  };

  return AuthenticateCheck;
};
