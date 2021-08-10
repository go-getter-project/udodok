import React, { useCallback, useState, useEffect } from 'react';
import { AllDiv, LoginDiv, Form, Login, Input, Button, Search, Social, FaceBook, Google } from '@pages/LogIn/styles';
import { Link, withRouter } from 'react-router-dom';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import useInput from '@hooks/useInput';
import { Redirect } from 'react-router';
import pwEncrypt from '@utils/pwEncrypt';
import { signin } from '@actions/auth';
import { useDispatch, useSelector } from 'react-redux';
import apiController from '@apis/apiController';

const LogIn = (props) => {
  const [email, onChangeEmail] = useInput('');
  const [password, onChangePassword] = useInput('');

  const dispatch = useDispatch();

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();
      const pwHash = pwEncrypt(password);

      dispatch(signin(email, pwHash))
        .then(() => {})
        .catch((err) => {
          console.dir(err);
        });
    },
    [email, password],
  );

  const onClickFacebook = () => {
    apiController({
      url: '/auth/FACEBOOK',
      method: 'get',
    }).then((res) => {
      console.log(res.data.data);
      // window.location.href = `${res.data.data}`;
    });
  };

  return (
    <div id="container" style={{ height: '100%' }}>
      <AllDiv>
        <Header />
        <LoginDiv>
          <Login>
            <h2>로그인</h2>
            <Form onSubmit={onSubmit}>
              <span>
                <Link to="/signup">우도독 가입하기</Link>
              </span>
              <div id="email">
                <Input
                  type="email"
                  id="email"
                  name="email"
                  placeholder="이메일"
                  value={email}
                  onChange={onChangeEmail}
                />
              </div>
              <div id="password">
                <Input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="비밀번호"
                  value={password}
                  onChange={onChangePassword}
                />
              </div>
              <Button type="submit">로그인</Button>
            </Form>
          </Login>
          <Search>
            <span>
              <Link to="/">아이디 찾기</Link> | <Link to="/">비밀번호 찾기</Link>
            </span>
          </Search>
          <Social>
            <FaceBook onClick={onClickFacebook}>
              <i className="fab fa-facebook-square"></i> 페이스북으로 시작하기
            </FaceBook>
            <Google>
              <i className="fab fa-google"></i> 구글로 시작하기
            </Google>
          </Social>
        </LoginDiv>
      </AllDiv>
      <Footer />
    </div>
  );
};

export default withRouter(LogIn);
