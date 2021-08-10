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
} from '@components/Modal/Check/styles';
import { Link, Redirect, useHistory } from 'react-router-dom';
import apiController from '@apis/apiController';

const Check = ({ checkModalOpen, setCheckModalOpen, Id, userId }) => {
  // console.log({ checkModalOpen });
  // console.log(Id);
  const history = useHistory();

  const Delete = useCallback(
    (e) => {
      apiController({
        url: `/users/discussions/del/${Id}?userId=${userId}`,
        method: 'delete',
      }).then((res) => {
        history.push('/discussion');
      });
    },
    [Id, userId], // 넣어줘야함
  );

  return (
    <>
      <Modal checkModal={checkModalOpen} />
      <Container checkModal={checkModalOpen}>
        <ModalWrapper
          onClick={() => {
            setCheckModalOpen(false);
          }}
        >
          <MainText>삭제하시겠습니까?</MainText>
          <SubText></SubText>
          <ModalBtn>
            <NoBtn
              onClick={() => {
                setCheckModalOpen(false);
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

export default Check;
