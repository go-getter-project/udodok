import React from 'react';
import { ReplyContents, ReplyUser, ReplyContent } from '@components/Reply/styles';

const ReplyComment = (props) => {
  // console.log(props);
  return (
    <ReplyContents>
      <ReplyUser>
        <i className="fas fa-user-circle"></i>
        <div>
          <div className="nickName">{props.writer}</div>
          <div className="date">{props.create}</div>
        </div>
      </ReplyUser>
      <ReplyContent>
        {/* {user_id === currentId ? (
          <button className="delete" onClick={onDelete}>
            &times;
          </button>
        ) : null} */}
        <div>{props.comment}</div>
      </ReplyContent>
    </ReplyContents>
  );
};

export default ReplyComment;
