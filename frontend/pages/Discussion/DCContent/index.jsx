import React, { useState, useEffect, useCallback } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import {
  Container,
  DCContainer,
  SubHeader,
  MainContainer,
  SubTitle,
  SubMeta,
  SubMetaLeft,
  SubMetaRight,
  SubArticle,
  DCEdit,
} from '@pages/Discussion/DCContent/styles';
import Reply from '@components/Reply';
import apiController from '@apis/apiController';
import { useSelector, useStore } from 'react-redux';
import { Link, Redirect, useHistory } from 'react-router-dom';
import CheckModal from '@components/Modal/Check';
import formatDate from '@utils/formatDate';

const DCContent = (props) => {
  const users = useSelector((state) => state.auth.user);
  const currentId = users.user_id;
  const history = useHistory();
  const { id } = props.match.params;
  const [Id, setId] = useState();
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [nickName, setNickName] = useState('');
  const [date, setDate] = useState('');
  const [userId, setUserId] = useState('');
  const [checkModalOpen, setCheckModalOpen] = useState(false);
  const [readHit, setReadHit] = useState(0);

  const onClickCheckBtn = (e) => {
    setCheckModalOpen(true);
  };

  // const onClickEditBtn = (e) => {
  //   history.push(`/discussion/edit/${id}`);
  // };

  useEffect(() => {
    apiController({
      url: `/bkusers/discussions/${id}?userId=${currentId}`,
      method: 'get',
    }).then((res) => {
      const data = res.data.data;
      setTitle(data.title);
      setContent(data.content);
      setNickName(data.user_nick_name);
      setDate(data.create_at);
      setId(data.id);
      setUserId(data.user_id);
      setReadHit(data.readhit);
    });
  }, []);

  return (
    <div style={{ height: '100%' }}>
      <CheckModal checkModalOpen={checkModalOpen} setCheckModalOpen={setCheckModalOpen} Id={Id} userId={currentId} />
      <DCContainer>
        <Header></Header>
        <Container>
          <SubHeader>토론 게시판</SubHeader>
          <MainContainer>
            <SubTitle>{title}</SubTitle>
            <SubMeta>
              <SubMetaLeft>
                <span className="date">{formatDate(date)}</span>|
                <span className="count">
                  조회수 <span className="countNo">{readHit}</span>
                </span>
              </SubMetaLeft>
              <SubMetaRight>
                <span>{nickName}</span>
                <i className="fas fa-user-circle"></i>
              </SubMetaRight>
            </SubMeta>
            <SubArticle>{content}</SubArticle>
          </MainContainer>
          {userId === currentId ? (
            <DCEdit>
              <Link to={{ pathname: `/discussion/edit/${id}`, state: { title, content } }}>
                <button className="edit">수정하기</button>
              </Link>
              <button className="delete" onClick={onClickCheckBtn}>
                삭제하기
              </button>
            </DCEdit>
          ) : null}

          <Reply discussionId={id}></Reply>
        </Container>
      </DCContainer>
      <Footer></Footer>
    </div>
  );
};

export default DCContent;
