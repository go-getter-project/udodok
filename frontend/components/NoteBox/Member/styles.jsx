import styled from '@emotion/styled';

export const Container = styled.button`
  width: 100%;
  height: 70px;
  padding: 15px;
  color: #939597;
  background-color: #fff;
  cursor: pointer;
  border: 0;
  outline: 0;

  &:focus {
    border: none;
    outline: none;
  }
`;

export const Top = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;

  & h5 {
    width: 50%;
    font-size: 14px;
    font-weight: bold;
    margin: 0;
    color: #222;
    text-align: left;
  }

  & span {
    font-size: 12px;
    text-align: right;
    width: 50%;
  }
`;

export const Bottom = styled.div`
  margin-top: 5px;
  width: 100%;
  display: flex;
  flex-direction: row;
  font-size: 14px;
`;
