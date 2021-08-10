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
import apiController from '@apis/apiController';

const ShareDelete = (props) => {
  // console.log(props);

  const onClickDelete = useCallback(
    (e) => {
      e.preventDefault();
      apiController({
        url: `/users/sharings?boardId=${props.boardId}&userId=${props.userId}`,
        method: 'delete',
      }).then((res) => {
        alert('삭제되었습니다.');
        props.setSuccessDelete(true);
      });
    },
    [props],
  );

  return (
    <>
      <Modal checkModal={props.deleteModalOpen} />
      <Container checkModal={props.deleteModalOpen}>
        <ModalWrapper>
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

            <OKBtn onClick={onClickDelete}>삭제하기</OKBtn>
          </ModalBtn>
        </ModalWrapper>
      </Container>
    </>
  );
};

export default ShareDelete;
