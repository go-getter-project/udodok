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
} from '@components/Modal/MemberDelete/styles';
import apiController from '@apis/apiController';

const MemberDelete = ({ userId, ...rest }) => {
  console.log(userId);

  const onClickDelete = useCallback(
    (e) => {
      e.preventDefault();

      apiController({
        url: `/admin/bkuser/delete/${userId}`,
        method: 'delete',
      }).then((res) => {
        alert('탈퇴되었습니다.');
        rest.setSuccessDelete(true);
      });
    },
    [userId, rest],
  );

  return (
    <>
      <Modal checkModal={rest.deleteModalOpen} />
      <Container checkModal={rest.deleteModalOpen}>
        <ModalWrapper>
          <MainText>탈퇴시키겠습니까?</MainText>
          <SubText></SubText>
          <ModalBtn>
            <NoBtn
              onClick={() => {
                rest.setDeleteModalOpen(false);
              }}
            >
              취소하기
            </NoBtn>

            <OKBtn onClick={onClickDelete}>탈퇴하기</OKBtn>
          </ModalBtn>
        </ModalWrapper>
      </Container>
    </>
  );
};

export default MemberDelete;
