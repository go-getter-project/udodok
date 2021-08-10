import React, { useCallback } from 'react';
import {
  Content,
  ContentLeft,
  ContentImg,
  ContentRight,
  ContentTitle,
  ContentText,
  ContentEdit,
} from '@components/RecordDetailContent/styles';

const DetailContent = (props) => {
  const bookContent = props.recordDetailData;

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
          <div className="title">{bookContent.book_name}</div>
        </ContentLeft>
        <ContentRight>
          <ContentTitle>
            <div className="title">{bookContent.title}</div>
            <div className="time">{bookContent.create_at}</div>
          </ContentTitle>
          <ContentText>
            {bookContent.content}
            <div id="tag">{bookContent.book_report_tag}</div>
          </ContentText>
          <ContentEdit>
            <button className="edit" onClick={onClickEditBtn}>
              수정하기
            </button>
            <button className="delete" onClick={onClickDeleteBtn}>
              삭제하기
            </button>
          </ContentEdit>
        </ContentRight>
      </Content>
    </div>
  );
};

export default DetailContent;
