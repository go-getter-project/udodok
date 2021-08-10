import React, { useState, useRef } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import { Container } from '@pages/Note/styles';
import Content from '@pages/Note/Content';
import { useSelector } from 'react-redux';

const Note = () => {
  const userId = useSelector((state) => state.auth.user.user_id);
  return (
    <div style={{ height: '100%' }}>
      <Container>
        <Header />
        <Content userId={userId} />
      </Container>
      <Footer />
    </div>
  );
};

export default Note;
