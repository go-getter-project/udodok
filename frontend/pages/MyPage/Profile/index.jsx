import React, { useCallback, useEffect, useState } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import Tab from '@components/Tab';
import {
  AllDiv,
  ContentDiv,
  ProfileDiv,
  Profile,
  CantModified,
  ProfileImage,
  Label,
  Input,
  SmallText,
  FormGroup,
  ProfileForm,
  MyPageDiv,
  ModifiedBtn,
} from '@pages/MyPage/Profile/styles';
import { Error } from '@pages/SignUp/SignUp2/styles';
import { useSelector } from 'react-redux';
import useInput from '@hooks/useInput';
import pwEncrypt from '@utils/pwEncrypt';
import { withRouter } from 'react-router';
import apiController from '@apis/apiController';

const MyPageProfile = (props) => {
  const pwCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/;
  const nickNameCheck = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{4,16}$/;

  const userId = useSelector((state) => state.auth.user.user_id);
  const [email, setEmail] = useState('');
  const [name, setName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [nickName, onChangeNickName, setNickName] = useInput('');
  const [password, setPassword] = useState('');
  const [passwordCheck, setPasswordCheck] = useState('');

  const [mismatchError, setMismatchError] = useState(false);

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

  useEffect(() => {
    apiController({
      url: `/bkusers/mypage/${userId}`,
      method: 'get',
    }).then((res) => {
      const data = res.data.data;
      setNickName(data.nick_name);
      setEmail(data.email);
      setName(data.name);
      setPhoneNumber(data.phone_number);
    });
  }, []);

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();

      if (mismatchError) {
        alert('비밀번호를 확인해주세요.');
      } else if (!pwCheck.test(password)) {
        alert('비밀번호를 확인해주세요.');
      } else if (!nickNameCheck.test(nickName)) {
        alert('닉네임을 확인해주세요.');
      } else {
        const pwHash = pwEncrypt(password);
        let params = {
          nick_name: nickName,
          password: pwHash,
        };

        apiController({
          url: `/bkusers/mypage/edit/${userId}`,
          method: 'patch',
          data: params,
        }).then((res) => {
          alert('성공적으로 수정되었습니다.');
          props.history.push(`/mypage/profile/${userId}`);
          window.location.reload();
        });
      }
    },
    [mismatchError, password, nickName, pwCheck, nickNameCheck, userId],
  );

  if (!email) {
    return <div>Loading...</div>;
  }

  return (
    <div id="mypage-profile" style={{ height: '100%' }}>
      <MyPageDiv>
        <Header />
        <AllDiv>
          <Tab />
          <ContentDiv>
            <ProfileDiv>
              <h2>프로필</h2>
              <Profile>
                <CantModified>
                  이메일
                  <span>{email}</span>
                  이름
                  <span>{name}</span>
                  휴대폰
                  <span>{phoneNumber}</span>
                </CantModified>
                <ProfileImage>
                  <i className="fas fa-user-circle fa-8x"></i>
                </ProfileImage>
              </Profile>
              <ProfileForm onSubmit={onSubmit}>
                <FormGroup>
                  <Label>닉네임</Label>
                  <Input type="text" placeholder={nickName} value={nickName} onChange={onChangeNickName}></Input>
                  {!nickName && <Error>닉네임을 입력해주세요.</Error>}
                </FormGroup>
                <FormGroup>
                  <Label>비밀번호</Label>
                  <Input
                    placeholder="비밀번호를 입력해주세요."
                    type="password"
                    value={password}
                    onChange={onChangePassword}
                  ></Input>
                  <SmallText>비밀번호는 8~16자, 영문, 숫자, 특수문자를 포함해야 합니다.</SmallText>
                </FormGroup>
                <FormGroup>
                  <Label>비밀번호 확인</Label>
                  <Input
                    placeholder="비밀번호를 다시 입력해주세요."
                    type="password"
                    value={passwordCheck}
                    onChange={onChangePasswordCheck}
                  ></Input>
                  {mismatchError && <Error>비밀번호가 일치하지 않습니다.</Error>}
                </FormGroup>
                <ModifiedBtn type="submit">수정하기</ModifiedBtn>
              </ProfileForm>
            </ProfileDiv>
          </ContentDiv>
        </AllDiv>
      </MyPageDiv>
      <Footer />
    </div>
  );
};

export default withRouter(MyPageProfile);
