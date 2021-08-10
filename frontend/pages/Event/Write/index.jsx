import React, { useState } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import EventWriteForm from '@components/EventWriteForm';
import { Container, DCContainer } from '@pages/Discussion/DCWrite/styles';

const EventWrite = (props) => {
  const [successWrite, setSuccessWrite] = useState(false);

  if (successWrite) {
    props.history.push('/event/now');
    window.location.reload();
    setSuccessWrite(false);
  }
  return (
    <div style={{ height: '100%' }}>
      <DCContainer>
        <Header />
        <Container>
          <EventWriteForm setSuccessWrite={setSuccessWrite} />
        </Container>
      </DCContainer>
      <Footer />
    </div>
  );
};

export default EventWrite;
