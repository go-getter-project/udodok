import React, { useState } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import DiscussionEditForm from '@components/DiscussionEditForm';
import { Container, DCContainer } from '@pages/Discussion/DCEdit/styles';

const DCEdit = (props) => {
  console.log('EDIT');
  // console.log(props.location.state);
  const title = props.location.state.title;
  const content = props.location.state.content;
  const Id = props.match.params.id;

  return (
    <div style={{ height: '100%' }}>
      <DCContainer>
        <Header />
        <Container>
          <DiscussionEditForm Id={Id} title={title} content={content}></DiscussionEditForm>
        </Container>
      </DCContainer>
      <Footer />
    </div>
  );
};

export default DCEdit;
