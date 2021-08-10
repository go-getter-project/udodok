import styled from '@emotion/styled';

export const Fixed = styled.div`
  position: relative;
  width: 100%;
  height: 100%;
`;

export const Inner = styled.div`
  height: 100%;
  display: flex;
  float: right;

  & ul {
    display: flex;
    margin: 0;
    padding: 0;
    overflow: hidden;
    list-style: none;
    align-items: center;
    justify-content: center;
  }

  & ul > li {
    display: inline;
    margin-left: 15px;
  }

  & ul > li > a {
    text-decoration: none;
    text-align: center;
    font-weight: bold;
    color: #939597;
  }

  & ul > li > a:hover {
    color: #0f4c81;
  }
`;

export const LinkClicked = styled.div`
  color: ${(props) => (props.isActive ? '#0f4c81' : '#939597')};
  :hover {
    color: #0f4c81;
  }

  & i {
    color: #939597;
  }

  & i > span {
    font-size: 5px;
    position: absolute;
    top: 18px;
    right: -1px;
    z-index: 2;
    color: #0f4c81;
  }
`;
