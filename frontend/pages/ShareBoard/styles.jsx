import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1200px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
  margin-bottom: 100px;
  padding-bottom: 300px;
`;

export const Title = styled.h2`
  padding: 50px 30px;
  border-bottom: 1px solid rgba(147, 149, 151, 0.8);
  margin-bottom: 30px;
  padding-bottom: 30px;
`;

export const Content = styled.div`
  padding: 0px 30px;
  min-height: 100%;

  & h4 {
    font-weight: bold;
    margin-bottom: 30px;
  }
`;

export const PopularCards = styled.div`
  padding: 0px 30px;
  margin-bottom: 30px;
`;

export const SharedCards = styled.div`
  padding: 0px 30px;
  margin-bottom: 30px;
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
