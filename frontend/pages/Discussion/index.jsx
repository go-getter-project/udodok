import React, { useEffect, useState } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import {
  Container,
  DCHeader,
  DCSearch,
  DCTable,
  DCMain,
  DCTitle,
  DCButton,
  DCContainer,
} from '@pages/Discussion/styles';
import { Link } from 'react-router-dom';
import axios from 'axios';
import Paging from '@components/Paging';
import useInput from '@hooks/useInput';
import { useDispatch } from 'react-redux';
import { useSelector } from 'react-redux';
import formatDate from '@utils/formatDate';

const Discussion = () => {
  const { isLoggedIn } = useSelector((state) => state.auth);
  const [post, setPost] = useState();
  const [totalElements, setTotalElements] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  const [text, setText] = useInput('');
  const [select, setSelect] = useState();
  const [selectValue, setSelectValue] = useState();
  useEffect(() => {
    axios
      .get('/api/discussions', {
        withCredentials: true,
        params: {
          page: currentPage,
        },
      })
      .then((res) => {
        const data = res.data.data.content;
        const pages = res.data.pagination;
        const { total_pages, total_elements, current_page, current_elements } = pages;
        setPost(data);
        setTotalElements(total_elements);
        setCurrentPage(current_page);
      })
      .catch((e) => {
        console.log(e);
      });
  }, []);

  const handlePageChange = (page) => {
    axios
      .get('/api/discussions', {
        withCredentials: true,
        params: {
          page: page - 1,
        },
      })
      .then((res) => {
        const data = res.data.data.content;
        const pages = res.data.pagination;
        const { total_pages, total_elements, current_page, current_elements } = pages;
        console.log(data);
        setPost(data);
        setTotalElements(total_elements);
        setCurrentPage(current_page);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  const handleChange = (e) => {
    const { value } = e.target;
    setSelectValue(value);
  };

  // console.log({ post });
  const onSubmit = (e) => {
    // console.log('ddd');
    axios
      .get(`/api/discussions/search-${selectValue}/${text}`, {
        withCredentials: true,
      })
      .then((res) => {
        const data = res.data.data.content;
        setPost(data);
      })
      .catch((e) => {
        console.log(e);
      });
  };

  return (
    <div id="discussion" style={{ height: '100%' }}>
      <Header></Header>
      <Container>
        <DCContainer>
          <DCHeader>토론 게시판</DCHeader>
          <DCSearch>
            <select onChange={handleChange} value={select}>
              <option>선택</option>
              <option value="all">제목/내용</option>
              <option value="title">제목</option>
              <option value="content">내용</option>
            </select>
            <i className="fas fa-search" onClick={onSubmit}></i>
            <input placeholder="검색어를 입력해주세요." onChange={setText} value={text}></input>
          </DCSearch>
          <DCTable>
            <DCTitle>
              <tr>
                <th className="textNo">글번호</th>
                <th className="title">제목</th>
                <th>조회수</th>
                <th>작성자</th>
                <th>작성날짜</th>
              </tr>
            </DCTitle>
            <DCMain>
              {post?.map((item, idx) => {
                return <DCItem key={idx} item={item} isLoggedIn={isLoggedIn} />;
              })}
            </DCMain>
          </DCTable>
          <Link to="/discussion/write">
            <DCButton>글쓰기</DCButton>
          </Link>
          <div>
            <Paging
              discussion={true}
              totalElements={totalElements}
              currentPage={currentPage}
              handlePageChange={handlePageChange}
            ></Paging>
          </div>
        </DCContainer>
      </Container>
      <Footer />
    </div>
  );
};

export default Discussion;

const DCItem = ({ item, isLoggedIn }) => {
  const { id, title, user_nickname, create_at, read_hit } = item;
  return (
    <tr>
      <td>{id}</td>
      {isLoggedIn ? (
        <td className="title">
          <Link to={{ pathname: `/discussion/content/${id}`, state: { read_hit: read_hit } }}>{title} </Link>
        </td>
      ) : (
        <td className="title">
          <Link to="/login">{title}</Link>
        </td>
      )}
      <td>{read_hit}</td>
      <td>{user_nickname}</td>
      <td>{formatDate(create_at)}</td>
    </tr>
  );
};
