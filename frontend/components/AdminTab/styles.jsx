import styled from '@emotion/styled';

export const TabDiv = styled.div`
  margin-top: 40px;
  width: 220px;
  background-color: #fff;

  & h2 {
    padding: 20px 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #000;
    margin: 0;
    margin-bottom: 20px;
    height: 50px;
    font-size: 24px;
    font-weight: bold;
  }

  & ul {
    list-style: none;
    padding: 0;
    margin: 0;
    position: fixed;
    overflow: auto;
  }

  & li {
    height: 40px;
    width: 200px;
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    padding-left: 40px;
  }

  & a {
    text-decoration: none;
    color: #000;
    font-size: 18px;
  }

  & li.select {
    background-color: #000000;
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    color: white;
    cursor: pointer;
  }

  & li:last-child {
  }
`;

export const LinkClicked = styled.div`
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  cursor: pointer;
  color: ${(props) => (props.isActive ? '#ffffff' : '#000000')};
  background-color: ${(props) => (props.isActive ? '#000' : '#fff')};
  :hover {
    background-color: #000000;
    color: white;
  }
`;
