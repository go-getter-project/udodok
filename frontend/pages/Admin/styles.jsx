import styled from '@emotion/styled';

export const MemberTable = styled.table`
  width: 900px;
  border-collapse: collapse;
  margin-top: 12px;
`;

export const MemberTitle = styled.thead`
  border: 0px;
  border-bottom: 1px solid #000;
  text-align: center;
  & th {
    border: 0px;
    padding: 5px;
    height: 60px;
    font-size: 15px;
    color: #000;
    width: 110px;
  }

  & th.textNo {
    width: 150px;
  }

  & th.title {
    width: 850px;
  }
`;
export const MemberMain = styled.tbody`
  height: 60px;
  text-align: center;

  & tr {
    height: 60px;
    :hover {
      background-color: #e4e2e2;
    }
  }

  & td {
    border-bottom: 1px solid #939597;
  }

  & td.delete {
    color: red;
    font-weight: bold;
    cursor: pointer;
  }
`;

export const Container = styled.div`
  height: auto;
  min-height: 100%;
  padding-bottom: 300px;
`;

export const AllDiv = styled.div`
  width: 1200px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  margin-bottom: 100px;
`;

export const Content = styled.div`
  margin-left: 70px;
  margin-top: 100px;
`;

export const MemberSearch = styled.div`
  display: flex;
  justify-content: flex-end;

  & i {
    position: absolute;
    font-size: 16px;
    color: #000000;
    top: 110px;
    right: 15px;
    z-index: 2;
    cursor: pointer;
  }

  & select {
    border-color: #000000;
    color: #000000;
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
