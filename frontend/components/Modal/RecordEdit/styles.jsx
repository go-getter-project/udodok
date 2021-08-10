import styled from '@emotion/styled';

export const Modal = styled.div`
  display: ${(props) => (props.editModal ? 'block' : 'none')};
  position: fixed;
  /* width: 1920px;
  height: 100%; */
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 9;
  opacity: 1;
  background-color: rgba(0, 0, 0, 0.2);
`;

export const ModalBtn = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
`;

export const OKBtn = styled.button`
  border-radius: 3px;
  border: none;
  width: 100px;
  height: 39px;
  color: white;
  margin-left: 7px;
  cursor: pointer;
  margin-top: 35px;

  background: #900020;
  :hover {
    background-color: rgba(144, 0, 32, 0.8);
  }
`;

export const NoBtn = styled.button`
  border-radius: 3px;
  border: none;
  background-color: #939597;
  width: 100px;
  height: 39px;
  color: white;
  margin-left: 7px;
  cursor: pointer;
  margin-top: 35px;

  &:hover {
    background-color: rgba(147, 149, 151, 0.8);
    border: none;
  }
`;

export const ModalForm = styled.form`
  display: flex;
  flex-direction: column;
  padding: 50px 20px;
  height: 100%;

  & h5 {
    font-weight: bold;
  }
`;

export const Title = styled.textarea`
  font-size: 15px;
  color: #4e4e4e;
  width: 100%;
  height: 50px;
  border: 1px solid #ced4da;
  resize: none;
  padding: 10px;
  margin-bottom: 10px;
  overflow: hidden;

  &:focus {
    outline: none;
  }
`;

export const Content = styled.textarea`
  padding: 10px;
  font-size: 15px;
  color: #4e4e4e;
  width: 100%;
  height: 350px;
  border: 1px solid #ced4da;
  margin-bottom: 10px;
  resize: none;

  &:focus {
    outline: none;
  }
`;

export const Tag = styled.textarea`
  padding: 10px;
  font-size: 15px;
  color: #4e4e4e;
  width: 500px;
  height: 50px;
  border: 1px solid #ced4da;
  resize: none;
  overflow: hidden;

  &:focus {
    outline: none;
  }
`;

export const Container = styled.div`
  border: 3px solid #939597;
  width: 800px;
  height: 720px;
  background-color: white;
  display: ${(props) => (props.editModal ? 'block' : 'none')};
  position: fixed;
  top: 50%;
  left: 50%;
  right: auto;
  bottom: auto;
  margin-right: -50%;
  transform: translate(-50%, -50%);
  z-index: 99;
`;
