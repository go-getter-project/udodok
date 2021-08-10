import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1200px;
  /* height: 100vh; */
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  padding-bottom: 500px;
`;
export const DCHeader = styled.h2`
  padding: 50px 30px;
`;

export const DCContainer = styled.div`
  height: auto;
  min-height: 100%;
  & div {
    margin-top: 100px;
  }
`;

export const DCSearch = styled.div`
  display: flex;
  justify-content: flex-end;

  & i {
    position: absolute;
    font-size: 16px;
    color: #0f4c81;
    top: 248px;
    right: 5px;
    z-index: 2;
    cursor: pointer;
  }

  & select {
    border-color: #0f4c81;
    color: #0f4c81;
    border-radius: 3px;
    margin-right: 10px;
  }

  & input {
    width: 298px;
    height: 34px;
    border: 1px solid #939597;
    border-radius: 3px;
  }
`;

export const DCTable = styled.table`
  width: 100%;
  border-top: 0.5px solid #0f4c81;
  border-collapse: collapse;
  margin-top: 12px;
`;

export const DCTitle = styled.thead`
  border: 0px;
  border-bottom: 1px solid #0f4c81;
  text-align: center;
  & th {
    border: 0px;
    padding: 5px;
    height: 60px;
    font-size: 15px;
    color: #0f4c81;
    width: 110px;
  }

  & th.textNo {
    width: 150px;
  }

  & th.title {
    width: 850px;
  }
`;

export const DCMain = styled.tbody`
  height: 60px;
  text-align: center;

  & tr {
    height: 60px;
  }
  & tr.notice {
    font-weight: bold;
  }

  & td {
    border-bottom: 1px solid #939597;
  }

  & td.title {
    text-align: left;
    padding-left: 40px;
  }

  & td.title > a {
    text-decoration: none;
    color: black;
  }
`;

export const DCButton = styled.button`
  border: 0px;
  border-radius: 5px;
  background-color: #0f4c81;
  color: white;
  width: 120px;
  height: 45px;
  font-weight: bold;
  float: right;
  margin-top: 15px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
  :hover {
    cursor: pointer;
    background-color: rgba(15, 76, 129, 0.8);
  }
`;
