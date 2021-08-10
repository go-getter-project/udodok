import React, { useState, useCallback } from 'react';
import Header from '@layouts/Header';
import {
  SignUpWrapper,
  SignForm,
  FormGroup,
  Label,
  EmailInput,
  TextInput,
  SmallText,
  FormCheck,
  CheckText,
  SignUpButton,
  AuthButton,
  Email,
  SignUpTitle,
  SignUpDiv,
  Error,
  AuthSuccessBtn,
} from '@pages/SignUp/SignUp2/styles';
import Footer from '@layouts/Footer';
import AuthModal from '@components/Modal/Auth';
import ServiceModal from '@components/Modal/Service';
import useInput from '@hooks/useInput';
import axios from 'axios';
import { Redirect } from 'react-router';
import { useSelector, useDispatch } from 'react-redux';
import { signup } from '@actions/auth';

const SignUp2 = () => {
  const pwCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
  const nickNameCheck = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{4,16}$/;
  const [signUpSuccess, setSignUpSuccess] = useState(false);

  const [email, onChangeEmail] = useInput('');
  const [name, onChangeName] = useInput('');
  const [nickName, onChangeNickName] = useInput('');
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [check, setCheck] = useState(0);
  const [emailAuthCheck, setEmailAuthCheck] = useState(false);
  const [terms, onChangeTerms] = useInput(false);

  const [authModalOpen, setAuthModalOpen] = useState(false);
  const [serviceModalOpen, setServiceModalOpen] = useState(false);

  const [mismatchError, setMismatchError] = useState(false);

  const { message } = useSelector((state) => state.message);
  const dispatch = useDispatch();

  const onChangePassword = useCallback(
    (e) => {
      setPassword(e.target.value);
      setMismatchError(e.target.value !== passwordCheck);
    },
    [passwordCheck],
  );

  const onChangePasswordCheck = useCallback(
    (e) => {
      setPasswordCheck(e.target.value);
      setMismatchError(e.target.value !== password);
    },
    [password],
  );

  const onChangePhoneNumber = useCallback((e) => {
    const { value } = e.target;
    const onlyNum = value.replace(/[^0-9]/g, '');

    setPhoneNumber(onlyNum);
  }, []);

  const onClickAuthButton = useCallback(
    (e) => {
      e.preventDefault();
      setAuthModalOpen(true);
    },
    [email],
  );

  const onClickServiceButton = useCallback((e) => {
    e.preventDefault();
    setServiceModalOpen(true);

    const checkId = e.target.id;
    if (checkId === 'check1') setCheck(0);
    else setCheck(1);
  }, []);

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();

      if (!emailAuthCheck) {
        alert('이메일 인증을 먼저 해주세요');
      } else if (!pwCheck.test(password)) {
        alert('비밀번호 오류');
      } else if (!nickNameCheck.test(nickName)) {
        alert('닉네임 오류');
      } else if (!name.length || !name.trim().length) {
        alert('이름 오류');
      } else if (!phoneNumber.length || !phoneNumber.trim().length) {
        alert('휴대폰 번호 오류');
      } else if (!terms) {
        alert('이용 약관 동의하세요');
      } else {
        dispatch(signup(email, name, nickName, password, phoneNumber))
          .then(() => {
            setSignUpSuccess(true);
            alert('회원 가입 성공');
          })
          .catch((err) => {
            console.dir(err);
          });

        // axios
        //   .post('/api/signup', {
        //     email,
        //     name,
        //     nick_name: nickName,
        //     password: pwHash,
        //     phone_number: phoneNumber,
        //   })
        //   .then((res) => {
        //     console.log(res);
        //     setSignUpSuccess(true);
        //     alert('회원 가입 성공');
        //   })
        //   .catch((err) => {
        //     console.dir(err);
        //   });
      }
    },
    [email, password, name, phoneNumber, nickName, pwCheck],
  );

  if (signUpSuccess) {
    return <Redirect to="/login" />;
  }

  return (
    <div id="container" style={{ height: '100%' }}>
      <AuthModal
        authModalOpen={authModalOpen}
        setAuthModalOpen={setAuthModalOpen}
        setEmailAuthCheck={setEmailAuthCheck}
        email={email}
      />
      <ServiceModal serviceModalOpen={serviceModalOpen} setServiceModalOpen={setServiceModalOpen} check={check} />
      <SignUpDiv>
        <Header />
        <SignUpWrapper>
          <SignUpTitle>회원가입</SignUpTitle>
          <SignForm onSubmit={onSubmit}>
            <FormGroup>
              <Email>
                <Label>
                  이메일<span> *</span>
                </Label>
                <br></br>
                <EmailInput
                  type="email"
                  id="email"
                  name="email"
                  placeholder="이메일을 입력해주세요."
                  onChange={onChangeEmail}
                  value={email}
                ></EmailInput>
                {!emailAuthCheck && <AuthButton onClick={onClickAuthButton}>인증받기</AuthButton>}
                {emailAuthCheck && <AuthSuccessBtn>인증 완료</AuthSuccessBtn>}
              </Email>
            </FormGroup>
            <FormGroup>
              <Label>
                비밀번호<span> *</span>
              </Label>
              <br></br>
              <TextInput
                type="password"
                id="password"
                name="password"
                placeholder="비밀번호를 입력해주세요."
                onChange={onChangePassword}
                value={password}
              ></TextInput>
              <SmallText>비밀번호는 8~16자, 영문, 숫자, 특수문자를 포함해야 합니다.</SmallText>
            </FormGroup>
            <FormGroup>
              <Label>
                비밀번호 확인<span> *</span>
              </Label>
              <br></br>
              <TextInput
                type="password"
                id="password-check"
                name="password-check"
                placeholder="비밀번호를 다시 입력해주세요."
                onChange={onChangePasswordCheck}
                value={passwordCheck}
              ></TextInput>
              {mismatchError && <Error>비밀번호가 일치하지 않습니다.</Error>}
            </FormGroup>
            <FormGroup>
              <Label>
                이름<span> *</span>
              </Label>
              <br></br>
              <TextInput
                type="text"
                id="name"
                name="name"
                placeholder="이름을 입력해주세요."
                onChange={onChangeName}
                value={name}
              ></TextInput>
              {!name && <Error>이름을 입력해주세요.</Error>}
            </FormGroup>
            <FormGroup>
              <Label>
                휴대폰 번호<span> *</span>
              </Label>
              <br></br>
              <TextInput
                type="text"
                id="phone-number"
                name="phone-number"
                placeholder="-없이 번호를 입력해주세요."
                onChange={onChangePhoneNumber}
                value={phoneNumber}
              ></TextInput>
              {!phoneNumber && <Error>휴대폰 번호를 입력해주세요.</Error>}
            </FormGroup>
            <FormGroup>
              <Label>
                닉네임<span> *</span>
              </Label>
              <br></br>
              <TextInput
                type="text"
                id="nick-name"
                name="nick-name"
                placeholder="닉네임을 입력해주세요."
                onChange={onChangeNickName}
                value={nickName}
              ></TextInput>
              <SmallText>닉네임은 4~16자로 입력해주세요.</SmallText>
              {!nickName && <Error>닉네임을 입력해주세요.</Error>}
            </FormGroup>
            <FormCheck>
              <div>
                <input type="checkbox" id="terms" name="terms" value={terms} onClick={onChangeTerms} />
                <CheckText>
                  우도독의{' '}
                  <a id="check1" onClick={onClickServiceButton}>
                    이용약관 및 개인정보 처리 방침
                  </a>
                  (필수)에 동의합니다.<br></br>
                </CheckText>
                <input type="checkbox" />
                <CheckText>
                  <a id="check2" onClick={onClickServiceButton}>
                    마케팅 정보 수신
                  </a>
                  (선택)에 동의합니다.
                </CheckText>
              </div>
            </FormCheck>
            <SignUpButton type="submit">회원가입</SignUpButton>
          </SignForm>
        </SignUpWrapper>
      </SignUpDiv>
      <Footer />
    </div>
  );
};

export default SignUp2;
