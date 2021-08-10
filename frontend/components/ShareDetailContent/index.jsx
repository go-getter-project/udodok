import React, { useState, useCallback } from 'react';
import {
  Content,
  ContentLeft,
  ContentImg,
  ContentRight,
  ContentTitle,
  ContentText,
  ContentEdit,
} from '@components/ShareDetailContent/styles';
import { useSelector } from 'react-redux';
import { useParams, withRouter } from 'react-router';

const ShareDetailContent = (props) => {
  const { boardId } = useParams();
  const state = useSelector((state) => state.auth);
  let userId = -1;

  if (state.user !== null) {
    userId = state.user.user_id;
  }
  // console.log(props);

  const onClickEditBtn = useCallback((e) => {
    e.preventDefault();
    props.setEditModalOpen(true);
  }, []);

  const onClickDeleteBtn = useCallback((e) => {
    e.preventDefault();
    props.setDeleteModalOpen(true);
  }, []);

  return (
    <div>
      <Content>
        <ContentLeft>
          <ContentImg>
            <img src="/images/placeholder.png"></img>
          </ContentImg>
          <div className="title">{props.bookTitle}</div>
        </ContentLeft>
        <ContentRight>
          <ContentTitle>
            <div className="title">{props.title}</div>
            <div className="date">{props.create}</div>
          </ContentTitle>
          <ContentText>
            {props.content}
            <div id="tag">{props.tag ? props.tag : null}</div>
          </ContentText>
          {props.writerId === userId && (
            <ContentEdit>
              <button className="edit" onClick={onClickEditBtn}>
                수정하기
              </button>
              <button className="delete" onClick={onClickDeleteBtn}>
                삭제하기
              </button>
            </ContentEdit>
          )}
        </ContentRight>
      </Content>
    </div>
  );
};

export default withRouter(ShareDetailContent);
