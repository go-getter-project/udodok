import React from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import ShareBoardWriteForm from '@components/ShareBoardWriteForm';
import { Container, BRContainer } from '@pages/MyBookRecord/BRWrite/styles';
import { withRouter } from 'react-router';

const Write = () => {
  return (
    <div style={{ height: '100%' }}>
      <BRContainer>
        <Header />
        <Container>
          <ShareBoardWriteForm />
        </Container>
      </BRContainer>
      <Footer />
    </div>
  );
};

export default withRouter(Write);
