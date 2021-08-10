import styled from '@emotion/styled';

export const Container = styled.div`
  width: 250px;
  margin: 0px 10px;
  position: relative;
`;

export const Title = styled.div`
  font-weight: bold;
  font-size: 1.25rem;
  margin-bottom: 5px;
`;

export const Content = styled.div``;

export const BookTitle = styled.p`
  margin: 0px;
  font-size: 16px;
`;

export const NickName = styled.p`
  margin: 0px;
  font-size: 16px;
`;

export const DateTime = styled.p`
  margin: 0px;
`;

export const Tag = styled.p`
  /* padding-top: 5px; */
  margin-bottom: 5px;
  color: #939597;
  font-size: 12px;
`;

export const Detail = styled.button`
  border-radius: 3px;
  border: none;
  background-color: #939597;
  width: 80px;
  height: 38px;
  color: white;
  cursor: pointer;

  &:hover {
    background-color: rgba(147, 149, 151, 0.8);
    border: none;
  }
`;
