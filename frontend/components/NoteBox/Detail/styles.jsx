import styled from '@emotion/styled';

export const Container = styled.div`
  width: 760px;
  margin-left: 10px;
  height: 100%;
  border: 1px solid #ced4da;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
`;

export const Top = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  border-bottom: 1px solid #ced4da;

  & h4 {
    width: 70%;
    margin: 0;
    font-size: 24px;
    padding: 30px 20px;
  }

  & div {
    width: 30%;
    font-size: 12px;
    text-align: right;
    padding: 30px 20px;
  }

  & div > i {
    padding: 0px 10px;
    color: #0f4c81;
    cursor: pointer;
  }
`;
