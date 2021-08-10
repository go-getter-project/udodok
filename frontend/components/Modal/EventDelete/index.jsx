import React, { useCallback } from 'react';
import {
  Modal,
  ModalBtn,
  OKBtn,
  NoBtn,
  ModalWrapper,
  MainText,
  SubText,
  Container,
} from '@components/Modal/EventDelete/styles';
import axios from 'axios';

const EventDelete = (props) => {
  // console.log(props);
  const Delete = useCallback(
    (e) => {
      e.preventDefault();

      axios
        .delete(`/api/admin/events/${props.eventId}`, {
          withCredentials: true,
        })
        .then((res) => {
          alert('삭제되었습니다.');
          props.setSuccessDelete(true);
        })
        .catch((err) => {
          console.dir(err);
        });
    },
    [props], // 넣어줘야함
  );

  return (
    <>
      <Modal deleteModal={props.deleteModalOpen} />
      <Container deleteModal={props.deleteModalOpen}>
        <ModalWrapper
          onClick={() => {
            props.setDeleteModalOpen(false);
          }}
        >
          <MainText>삭제하시겠습니까?</MainText>
          <SubText></SubText>
          <ModalBtn>
            <NoBtn
              onClick={() => {
                props.setDeleteModalOpen(false);
              }}
            >
              취소하기
            </NoBtn>

            <OKBtn onClick={Delete}>삭제하기</OKBtn>
          </ModalBtn>
        </ModalWrapper>
      </Container>
    </>
  );
};

export default EventDelete;
