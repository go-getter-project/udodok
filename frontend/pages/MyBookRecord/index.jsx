import React, { useEffect, useState, useCallback } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import { Container, Title, Content, PopularCards, SharedCards, Button } from '@pages/ShareBoard/styles';
import Cards from '@components/Cards';
import { Row } from 'antd';
import Paging from '@components/Paging';
import { withRouter } from 'react-router';
import { useSelector } from 'react-redux';
import apiController from '@apis/apiController';

const MyBookRecord = (props) => {
  const state = useSelector((state) => state.auth);
  const userId = state.user.user_id;

  const [currentPage, setCurrentPage] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [sharedBoards, setSharedBoards] = useState([]);

  useEffect(() => {
    handlePageChange(currentPage);
  }, []);

  const handlePageChange = async (page) => {
    let params = {
      page: page - 1,
    };
    const res = await apiController({
      url: `/bkusers/book-reports/${userId}`,
      method: 'get',
      data: params,
    });

    if (res.data.message === '조회성공') {
      setSharedBoards(res.data.data.content);
      setTotalElements(res.data.pagination.total_elements);
      setCurrentPage(res.data.pagination.current_page);
    }
  };

  const onClickEdit = useCallback(() => {
    props.history.push('/mybookrecord/write');
  }, []);

  // if (!sharedBoards || !totalElements) {
  //   return <div>Loading...</div>;
  // }

  return (
    <div style={{ height: '100%' }}>
      <Container>
        <Header />
        <Title>기록 게시판</Title>
        <Content>
          <h4>나의 게시물</h4>
          <SharedCards>
            <Row gutter={[16, 32]}>
              {sharedBoards &&
                sharedBoards.map((board, index) => (
                  <React.Fragment key={index}>
                    <Cards
                      title={board.title}
                      createdBoard={board.create_at}
                      boardId={board.book_report_id}
                      bookTitle={board.book_name}
                      bookTag={board.book_report_tag}
                    />
                  </React.Fragment>
                ))}
            </Row>
          </SharedCards>
        </Content>
        <div style={{ marginBottom: '50px' }}>
          <Button onClick={onClickEdit}>작성하기</Button>
        </div>
        <div style={{ marginLeft: '150px' }}>
          <Paging
            bookBoard={true}
            totalElements={totalElements}
            currentPage={currentPage}
            handlePageChange={handlePageChange}
          />
        </div>
      </Container>
      <Footer />
    </div>
  );
};

export default withRouter(MyBookRecord);
