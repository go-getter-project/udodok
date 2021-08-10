import React, { useState, useCallback } from 'react';
import {
  Modal,
  ModalBtn,
  ModalWrapper,
  MainText,
  SubText,
  Container,
  TextInput,
  AuthCheckBtn,
} from '@components/Modal/Auth/styles';
import axios from 'axios';

const Auth = ({ authModalOpen, setAuthModalOpen, setEmailAuthCheck, email }) => {
  const [sendMail, setSendMail] = useState(false);
  const [authNumber, setAuthNumber] = useState('');

  const onChangeModal = useCallback(() => {
    setAuthModalOpen(false);
    setSendMail(false);
  }, []);

  const onChangeSendMail = useCallback(
    (e) => {
      setSendMail(false);

      axios
        .get(`/api/email-confirm?email=${email}`, {
          withCredentials: true,
        })
        .then((res) => {
          if (res.data.message === '이메일중복') {
            alert('이미 가입된 이메일입니다.');
          } else {
            setSendMail(!sendMail);
          }
        })
        .catch((err) => {
          console.dir(err);
        });
    },
    [email],
  );

  const onChangeAuthNumber = useCallback((e) => {
    const { value } = e.target;
    const onlyNum = value.replace(/[^0-9]/g, '');

    setAuthNumber(onlyNum);
  }, []);

  const onAuthConfirm = useCallback(
    (e) => {
      e.preventDefault();
      setEmailAuthCheck(false);
      // console.log(authNumber);

      axios
        .get(`/api/issuance-confirm?number=${authNumber}`, {
          withCredentials: true,
        })
        .then((res) => {
          if (res.data.message === '일치') {
            setEmailAuthCheck(true);
            onChangeModal();
          } else {
            alert('인증 번호가 일치하지 않습니다.');
          }
        })
        .catch((err) => {
          console.dir(err);
        });
      // setAuthModalOpen(false);
      // setSendMail(false);
    },
    [authNumber],
  );

  return (
    <>
      <Modal signUpModal={authModalOpen} onClick={onChangeModal} />
      <Container signUpModal={authModalOpen}>
        {!sendMail && (
          <ModalWrapper>
            <MainText>입력하신 이메일로 인증을 진행해 주세요.</MainText>
            <SubText>*수신함에서 인증메일을 찾을 수 없을 경우 스팸함을 조회하세요.</SubText>
            <br />
            <ModalBtn onClick={onChangeSendMail}>인증 메일 발송</ModalBtn>
          </ModalWrapper>
        )}
        {sendMail && (
          <form>
            <ModalWrapper>
              <MainText>이메일을 확인해주세요.</MainText>
              <SubText>*수신함에서 인증메일을 찾을 수 없을 경우 스팸함을 조회하세요.</SubText>
              <TextInput
                name="text"
                id="text"
                type="text"
                placeholder="인증번호를 입력해주세요."
                onChange={onChangeAuthNumber}
                value={authNumber}
              ></TextInput>
              <ModalBtn onClick={onAuthConfirm}>인증 번호 확인</ModalBtn>
            </ModalWrapper>
          </form>
        )}
      </Container>
    </>
  );
};

export default Auth;
