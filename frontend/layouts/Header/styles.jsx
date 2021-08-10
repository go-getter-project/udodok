import styled from '@emotion/styled';

export const Head = styled.header`
  height: 60px;
  width: 1200px;
  display: flex;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
`;

export const Head2 = styled.div`
  position: sticky;
  top: 0;
  background-color: white;
  border-bottom: 1px solid #f7f7f5;
  z-index: 8;
`;

export const HeaderTop = styled.div`
  width: 120px;
  display: flex;
  align-items: center;
  font-weight: bold;
  padding: 0 20px;

  font-weight: bold;

  & a {
    text-decoration: none;
    color: #000;
  }

  & a:hover {
    color: #000;
  }
`;
