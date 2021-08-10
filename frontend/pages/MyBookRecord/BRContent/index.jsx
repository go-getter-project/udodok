import React, { useEffect, useState } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import { Container, Title, Content } from '@pages/MyBookRecord/BRContent/styles';
import RecordDetailContent from '@components/RecordDetailContent';
import { useParams } from 'react-router';
import EditModal from '@components/Modal/RecordEdit';
import DeleteModal from '@components/Modal/RecordDelete';
import { useSelector } from 'react-redux';
import apiController from '@apis/apiController';

const BRContent = (props) => {
  const { boardId } = useParams();
  const state = useSelector((state) => state.auth.user);

  const [recordDetailData, setRecordDetailData] = useState([]);
  const [editModalOpen, setEditModalOpen] = useState(false);
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);

  const [successEdit, setSuccessEdit] = useState(false);
  const [successDelete, setSuccessDelete] = useState(false);

  useEffect(() => {
    bookReportDetail();
  }, []);

  const bookReportDetail = async () => {
    const res = await apiController({
      url: `/bkusers/book-reports/detail/${boardId}`,
      method: 'get',
    });

    setRecordDetailData(res.data.data);
  };

  if (successEdit) {
    setEditModalOpen(false);
    props.history.push(`/mybookrecord/detail/${boardId}`);
    window.location.reload();
    setSuccessEdit(false);
  }

  if (successDelete) {
    setDeleteModalOpen(false);
    props.history.push('/mybookrecord');
    window.location.reload();
    setSuccessDelete(false);
  }

  return (
    <div style={{ height: '100%' }}>
      <EditModal
        boardId={boardId}
        post={recordDetailData}
        editModalOpen={editModalOpen}
        setEditModalOpen={setEditModalOpen}
        setSuccessEdit={setSuccessEdit}
      />
      <DeleteModal
        deleteModalOpen={deleteModalOpen}
        setDeleteModalOpen={setDeleteModalOpen}
        boardId={boardId}
        setSuccessDelete={setSuccessDelete}
        userId={state.user_id}
      />
      <Container>
        <Header />
        <Title>나의 게시물</Title>
        <Content>
          <RecordDetailContent
            recordDetailData={recordDetailData}
            setEditModalOpen={setEditModalOpen}
            setDeleteModalOpen={setDeleteModalOpen}
          />
        </Content>
      </Container>
      <Footer />
    </div>
  );
};

export default BRContent;
