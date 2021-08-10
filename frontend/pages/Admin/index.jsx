import Header from '@layouts/Header';
import AdminTab from '@components/AdminTab';
import Footer from '@layouts/Footer';
import React, { useEffect, useState, useCallback } from 'react';
import apiController from '@apis/apiController';
import Paging from '@components/Paging';
import axios from 'axios';
import MemberDelete from '@components/Modal/MemberDelete';
import { useHistory } from 'react-router';
import useInput from '@hooks/useInput';
import { MemberTable, MemberTitle, MemberMain, Container, AllDiv, Content, MemberSearch } from '@pages/Admin/styles';
import formatPhoneNumber from '@utils/formatPhoneNumber';

const Admin = () => {
  const [totalElements, setTotalElements] = useState(0);
  const [currentPage, setCurrentPage] = useState(0);
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);
  const [successDelete, setSuccessDelete] = useState(false);
  const [userId, setUserId] = useState();
  const [post, setPost] = useState([]);
  const [select, setSelect] = useState();
  const [selectValue, setSelectValue] = useState();
  const [text, setText] = useInput('');
  const history = useHistory();

  useEffect(() => {
    handlePageChange(currentPage);
  }, []);

  const handlePageChange = (page) => {
    // let params = {
    //   page: page - 1,
    // };
    // const res = await apiController({
    //   url: `/admin/user/list`,
    //   method: 'get',
    //   data: params,
    // });

    axios
      .get('/api/admin/user/list', {
        withCredentials: true,
        params: {
          page: page - 1,
        },
      })
      .then((res) => {
        const data = res.data.data.content;
        setPost(data);
        // console.log(data);
        setTotalElements(res.data.pagination.total_elements);
        setCurrentPage(res.data.pagination.current_page);
      });
  };

  if (successDelete) {
    setDeleteModalOpen(false);
    history.push(`/admin`);
    window.location.reload();
    setSuccessDelete(false);
  }

  const handleChange = (e) => {
    const { value } = e.target;
    setSelectValue(value);
  };

  const onSubmit = (e) => {
    axios
      .get(`/api/admin/user/${selectValue}/${text.toUpperCase()}`, {
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
    <div style={{ height: '100%' }}>
      <MemberDelete
        deleteModalOpen={deleteModalOpen}
        setDeleteModalOpen={setDeleteModalOpen}
        setSuccessDelete={setSuccessDelete}
        userId={userId}
      />
      <Container>
        <Header />
        <AllDiv>
          <AdminTab />
          <Content>
            <MemberSearch>
              <select onChange={handleChange} value={select}>
                <option>선택</option>
                <option value="email-search">이메일</option>
                <option value="grade-search">유저 등급</option>
                <option value="name-search">이름</option>
                <option value="nick-search">닉네임</option>
              </select>
              <i className="fas fa-search" onClick={onSubmit}></i>
              <input placeholder="검색어를 입력해주세요." onChange={setText} value={text}></input>
            </MemberSearch>
            <MemberTable>
              <MemberTitle>
                <tr>
                  <th>이메일</th>
                  <th>이름</th>
                  <th>닉네임</th>
                  <th>휴대폰</th>
                  <th>가입날짜</th>
                  <th>유저 등급</th>
                  <th>탈퇴</th>
                </tr>
              </MemberTitle>
              <MemberMain>
                {post &&
                  post.map((item, idx) => {
                    return (
                      <MemberList
                        key={idx}
                        item={item}
                        deleteModalOpen={deleteModalOpen}
                        setDeleteModalOpen={setDeleteModalOpen}
                        setSuccessDelete={setSuccessDelete}
                        setUserId={setUserId}
                        history={history}
                        formatPhoneNumber={formatPhoneNumber}
                      />
                    );
                  })}
              </MemberMain>
            </MemberTable>
          </Content>
        </AllDiv>
        <div style={{ marginLeft: '150px' }}>
          <Paging totalElements={totalElements} currentPage={currentPage} handlePageChange={handlePageChange} />
        </div>
      </Container>
      <Footer />
    </div>
  );
};

export default Admin;

const MemberList = ({ item, setDeleteModalOpen, setUserId, history, formatPhoneNumber }) => {
  const { user_id, email, name, phone_number, nick_name, user_grade, created_at } = item;

  const [select, setSelect] = useState();
  const [selectValue, setSelectValue] = useState();
  let phoneNum;

  if (phone_number) {
    phoneNum = formatPhoneNumber(phone_number);
  }

  const onClickDelete = useCallback((e) => {
    e.preventDefault();
    setDeleteModalOpen(true);
    setUserId(user_id);
  }, []);

  const handleChange = useCallback(
    (e) => {
      const { value } = e.target;
      e.preventDefault();
      let params = { user_grade: value };
      apiController({
        url: `/admin/user/change-grade/${user_id}`,
        method: 'patch',
        data: params,
      }).then((res) => {
        alert('수정되었습니다.');
        window.location.reload();
        history.push('/admin');
      });
    },
    [user_id],
  );

  const User = () => (
    <select onChange={handleChange} value={select}>
      <option>{user_grade}</option>
      <option value="BLACK">BLACK</option>
    </select>
  );

  const Black = () => (
    <select onChange={handleChange} value={select}>
      <option>{user_grade}</option>
      <option value="USER">USER</option>
    </select>
  );

  return (
    <>
      <tr>
        <td>{email}</td>
        <td>{name}</td>
        <td>{nick_name}</td>
        <td>{phoneNum}</td>
        <td>{created_at}</td>
        {user_grade === 'USER' ? (
          <td>
            <User />
          </td>
        ) : user_grade === 'BLACK' ? (
          <td>
            <Black />
          </td>
        ) : (
          <td>{user_grade}</td>
        )}

        {user_grade === 'BLACK' ? (
          <td className="delete" onClick={onClickDelete}>
            탈퇴
          </td>
        ) : (
          <td></td>
        )}
      </tr>
    </>
  );
};
