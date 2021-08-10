import React, { useEffect, useState, useCallback } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import { Container, Title, Content, PopularCards, SharedCards, Button } from '@pages/ShareBoard/styles';
import Cards from '@components/Cards';
import CardsPopular from '@components/CardsPopular';
import { Row } from 'antd';
import axios from 'axios';
import Paging from '@components/Paging';
import { withRouter } from 'react-router';

const ShareBoard = (props) => {
  const [currentPage, setCurrentPage] = useState(0);
  const [totalElements, setTotalElements] = useState(0);
  const [sharedBoards, setSharedBoards] = useState([]);
  const [popularSharedBoards, setPopularSharedBoards] = useState([]);

  useEffect(() => {
    axios
      .get('/api/weekly-best', {
        withCredentials: true,
      })
      .then((res) => {
        // console.log(res);
        setPopularSharedBoards(res.data.data);
      })
      .catch((err) => {
        console.dir(err);
      });

    handlePageChange(currentPage);
  }, []);

  const handlePageChange = (page) => {
    axios
      .get('/api/sharings', {
        withCredentials: true,
        params: {
          page: page - 1,
        },
      })
      .then((res) => {
        // console.log(res.data);
        setSharedBoards(res.data.data);
        // console.log(page);

        const pagination = res.data.pagination;
        const { total_pages, total_elements, current_page, current_elements } = pagination;
        setTotalElements(total_elements);
        setCurrentPage(current_page);
        window.scrollTo(0, 0);
      })
      .catch((err) => {
        console.dir(err);
      });
  };

  const onClickEdit = useCallback(() => {
    props.history.push('/shareboard/write');
  }, []);

  if (!sharedBoards.length || !popularSharedBoards.length) {
    return <div>loading...</div>;
  }

  return (
    <div style={{ height: '100%' }}>
      <Container>
        <Header />
        <Title>공유 게시판</Title>
        <Content>
          <h4>인기 게시물</h4>
          {/* 인기 게시물 grid */}
          <PopularCards>
            <Row gutter={[16, 32]}>
              {popularSharedBoards &&
                popularSharedBoards.map((board, index) => (
                  <React.Fragment key={index}>
                    <CardsPopular
                      title={board.title}
                      createdBoard={board.created_at}
                      boardId={board.id}
                      likeCnt={board.like_cnt}
                      bookTitle={board.book_title}
                      writerNickName={board.writer_info.nick_name}
                      bookTag={board.tag_content}
                    />
                  </React.Fragment>
                ))}
            </Row>
          </PopularCards>
          <h4>공유된 게시물</h4>
          <SharedCards>
            <Row gutter={[16, 32]}>
              {sharedBoards &&
                sharedBoards.map((board, index) => (
                  <React.Fragment key={index}>
                    <Cards
                      share
                      title={board.title}
                      createdBoard={board.created_at}
                      boardId={board.id}
                      likeCnt={board.like_cnt}
                      bookTitle={board.book_title}
                      writerNickName={board.writer_info.nick_name}
                      bookTag={board.tag_content}
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

export default withRouter(ShareBoard);
