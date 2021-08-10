import styled from '@emotion/styled';

export const Content = styled.div`
  margin-top: 100px;
  display: flex;
  flex-direction: row;
  padding-bottom: 25px;
  height: auto;
`;

export const ContentLeft = styled.div`
  & div {
    text-align: right;
  }

  & div.title {
    font-weight: bold;
    font-size: 17px;
  }

  & div.author {
    color: #4e4e4e;
  }
`;

export const ContentRight = styled.div`
  margin-left: 100px;
  width: 100%;
  text-align: right;
`;

export const ContentImg = styled.div`
  margin-bottom: 15px;
`;

export const ContentTitle = styled.div`
  padding-bottom: 13px;
  border-bottom: 1px solid grey;

  & div.title {
    font-weight: bold;
    font-size: 19px;
    padding-bottom: 10px;
  }

  & div.date {
    color: #4e4e4e;
  }
`;

export const ContentText = styled.div`
  margin-top: 35px;
  font-size: 17px;
  line-height: 27px;

  & div {
    color: #0f4c81;
    padding-top: 30px;
  }
`;

export const ContentShare = styled.div``;

export const ContentEdit = styled.div`
  margin-top: 50px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  align-content: space-between;

  & div i {
    font-size: 25px;
    cursor: pointer;
  }

  & button {
    margin-left: 15px;
    width: 119px;
    height: 42px;
    border-radius: 3px;
    border: 0px;
    color: white;
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
    cursor: pointer;
  }

  & button.edit {
    background: #939597;
    :hover {
      background-color: rgba(147, 149, 151, 0.8);
    }
  }

  & button.delete {
    background: #900020;
    :hover {
      background-color: rgba(144, 0, 32, 0.8);
    }
  }
`;
