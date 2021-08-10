import styled from '@emotion/styled';

export const MyContainer = styled.div``;

export const Container = styled.div`
  width: 1200px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  margin-bottom: 100px;
  padding-bottom: 300px;
`;

export const BookRecord = styled.div`
  height: auto;
  min-height: 100%;
`;

export const MyHeader = styled.h2`
  padding: 50px 30px;
`;

export const MySearch = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-bottom: 40px;

  & i {
    position: absolute;
    font-size: 16px;
    color: #0f4c81;
    top: 160px;
    right: 4px;
    z-index: 2;
    cursor: pointer;
  }

  & input {
    width: 200px;
    height: 34px;
    border: 1px solid #939597;
    border-radius: 3px;
  }
`;

export const Wrapper = styled.div`
  display: block;
  margin-bottom: 70px;
  margin-right: 60px;
`;
export const Ul = styled.ul`
  list-style: none;
  text-align: right;
`;
export const Li = styled.li`
  display: inline-block;
  margin-left: 10px;

  & div {
    text-align: right;
    margin-bottom: 2px;
  }

  & div.title {
    margin-top: 10px;
    font-weight: bold;
    font-size: 17px;
  }

  & div.date {
    color: #939597;
  }

  & div.tag {
    font-size: 13px;
    color: #0f4c81;
    :hover {
      cursor: pointer;
    }
  }

  & div.imgDiv {
    overflow: hidden;
    top: 0;
    left: 0;
    box-sizing: border-box;
    width: 100%;
    height: 40%;
    border: 1px solid #eae9e8;
    border-radius: 5px;
    background: #f8f8f8;
    -webkit-transition: 300ms;
    transition: 300ms;
  }

  & img {
    vertical-align: top;
    width: 100%;
    height: 100%;
    opacity: 1;
    object-fit: cover;
    transition: opacity 420ms ease 0s;
  }
`;

export const Button = styled.button`
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
  z-index: 9999;
  &:hover {
    background-color: rgba(15, 76, 129, 0.8);
    border: none;
    cursor: pointer;
  }
  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }
`;
