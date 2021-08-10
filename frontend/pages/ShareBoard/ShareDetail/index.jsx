import React, { useState, useEffect } from 'react';
import Header from '@layouts/Header';
import Footer from '@layouts/Footer';
import { Container, Title, Content } from '@pages/ShareBoard/ShareDetail/styles';
import axios from 'axios';
import { Redirect, useParams, withRouter } from 'react-router';
import ShareDetailContent from '@components/ShareDetailContent';
import ShareBoardReply from '@components/ShareBoardReply';
import EditModal from '@components/Modal/ShareEdit';
import DeleteModal from '@components/Modal/ShareDelete';
import useInput from '@hooks/useInput';
import apiController from '@apis/apiController';

const ShareDetail = (props) => {
  const { boardId } = useParams();
  const [post, onChangePost, setPost] = useInput([]);
  const [reply, setReply] = useState([]);
  const [writerId, setWriterId] = useState('');
  const [writerNickName, setWriterNickName] = useState('');

  const [editModalOpen, setEditModalOpen] = useState(false);
  const [deleteModalOpen, setDeleteModalOpen] = useState(false);

  const [successEdit, setSuccessEdit] = useState(false);
  const [successDelete, setSuccessDelete] = useState(false);

  useEffect(() => {
    apiController({
      url: `/bkusers/sharings?id=${boardId}`,
      method: 'get',
    }).then((res) => {
      setPost(res.data.data);
      setWriterId(res.data.data.writer_info.writer_id);
      setWriterNickName(res.data.data.writer_info.nick_name);
    });
    // axios
    //   .get(`/api/bkuser/sharings?id=${boardId}`)
    //   .then((res) => {
    //     // console.log(res.data.data);
    //     setPost(res.data.data);
    //     setWriterId(res.data.data.writer_info.writer_id);
    //     setWriterNickName(res.data.data.writer_info.nick_name);
    //     // console.log('상세게시판 api', res.data.data);
    //   })
    //   .catch((err) => {
    //     console.dir(err);
    //   });

    apiController({
      url: `/users/sharing-reply?boardId=${boardId}`,
      method: 'get',
    }).then((res) => {
      setReply(res.data.data);
    });

    // axios
    //   .get(`/api/users/sharing-reply?boardId=${boardId}`)
    //   .then((res) => {
    //     setReply(res.data.data);
    //     // console.log('상세게시판 댓글 api', res.data.data);
    //   })
    //   .catch((err) => {
    //     console.dir(err);
    //   });
  }, []);

  if (!post) {
    return <div>Loading...</div>;
  }

  if (successEdit) {
    setEditModalOpen(false);
    props.history.push(`/shareboard/detail/${boardId}`);
    window.location.reload();
    setSuccessEdit(false);
  }

  if (successDelete) {
    setDeleteModalOpen(false);
    props.history.push('/shareboard');
    window.location.reload();
    setSuccessDelete(false);
  }

  return (
    <div style={{ height: '100%' }}>
      <EditModal
        boardId={boardId}
        post={post}
        editModalOpen={editModalOpen}
        setEditModalOpen={setEditModalOpen}
        setSuccessEdit={setSuccessEdit}
      />
      <DeleteModal
        deleteModalOpen={deleteModalOpen}
        setDeleteModalOpen={setDeleteModalOpen}
        boardId={boardId}
        setSuccessDelete={setSuccessDelete}
        userId={writerId}
      />
      <Container>
        <Header />
        <Title>{writerNickName}님의 게시물</Title>
        <Content>
          <ShareDetailContent
            title={post.title}
            bookTitle={post.book_title}
            content={post.content}
            create={post.created_at}
            like={post.like_cnt}
            writerId={writerId}
            tag={post.tag_content}
            setEditModalOpen={setEditModalOpen}
            setDeleteModalOpen={setDeleteModalOpen}
          />
          <ShareBoardReply
            boardId={boardId}
            userId={post.id}
            replyCnt={post.reply_cnt}
            reply={reply}
            setReply={setReply}
          />
        </Content>
      </Container>
      <Footer />
    </div>
  );
};

export default withRouter(ShareDetail);
