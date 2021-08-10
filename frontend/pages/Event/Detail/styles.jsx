import styled from '@emotion/styled';

export const EventContainer = styled.div`
  height: auto;
  min-height: 100%;
  padding-bottom: 300px;
`;
export const Container = styled.div`
  width: 1200px;
  /* height: 100vh; */
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  margin-bottom: 100px;
`;
export const Contents = styled.div`
  text-align: center;
  border-bottom: 1px solid #ccc;
  margin-bottom: 50px;
  padding-bottom: 50px;
  margin-top: 100px;
`;
export const ListButton = styled.div`
  text-align: center;

  & button {
    border: 1px solid #0f4c81;
    background-color: white;
    width: 144px;
    height: 42px;
    color: #0f4c81;
    cursor: pointer;
  }

  & button > a {
    text-decoration: none;
    color: #0f4c81;
  }
`;

export const EditBtn = styled.div`
  float: right;
  & i {
    font-size: 25px;
    color: #ccc;
    cursor: pointer;
  }

  & .delete {
    margin-left: 10px;
    background-color: white;
    border: 0px;
    font-size: 35px;
    color: red;
    cursor: pointer;
  }

  & .delete:focus {
    border: none;
    outline: none;
  }
`;

export const EventHeader = styled.h3`
  padding: 50px 30px;
  font-weight: bold;
`;

export const EventTab = styled.ul`
  list-style: none;
  margin-bottom: 34px;
  & li {
    display: inline-block;
    vertical-align: middle;
    width: 50%;
    text-align: center;
    font-size: 14px;
    color: #6a6a6a;
    font-weight: 400;
    cursor: pointer;
    line-height: 52px;
    border: 1px solid #d9d9d9;
    box-sizing: border-box;
    border-bottom: 1px solid #0f4c81;
  }
  & li.on {
    background: #fff;
    color: #000;
    font-weight: bold;
    border: 1px solid #0f4c81;
    border-bottom: 1px solid #fff;
  }
`;

export const TabArea = styled.div`
  display: flex;
  flex-wrap: wrap;
`;

export const EventList = styled.div`
  margin-top: 20px;
  text-align: center;
  :after {
    content: '';
    display: block;
    clear: both;
  }
  & span.evt_title {
    max-height: 40px;
    width: 100%;
    font-size: 18px;
    color: #4b4b4b;
    line-height: 20px;
    margin: 20px 0 0;
    font-weight: bold;
    word-wrap: break-word;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    text-overflow: ellipsis;
    overflow: hidden;
    display: -webkit-box;
  }

  & em {
    display: block;
    font-size: 14px;
    text-align: center;
  }
  & div {
    width: 590px;
    height: 320px;
    margin-top: 70px;
  }
`;
