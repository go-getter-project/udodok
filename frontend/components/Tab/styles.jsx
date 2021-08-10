import styled from '@emotion/styled';

export const TabDiv = styled.div`
  margin-top: 40px;
  width: 200px;
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
    padding-left: 40px;
  }

  & a {
    text-decoration: none;
    color: #000;
    font-size: 18px;
  }

  & li.select {
    background-color: #778fa8;
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
    color: white;
  }

  & li:last-child {
  }
`;
