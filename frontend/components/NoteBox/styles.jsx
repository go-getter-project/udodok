import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
`;

export const Content = styled.div`
  margin: 0 0 0 30px;
  width: 330px;
  height: 100%;
  border: 1px solid #ced4da;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;

  & h4 {
    margin: 0;
    font-size: 24px;
    padding: 30px 20px;
    border-bottom: 1px solid #ced4da;
  }
`;

export const Chats = styled.div`
  flex: 1;
`;
