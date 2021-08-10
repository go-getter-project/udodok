import styled from '@emotion/styled';

export const Modal = styled.div`
  display: ${(props) => (props.signUpModal ? 'block' : 'none')};
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 9;
  opacity: 1;
  background-color: rgba(0, 0, 0, 0.6);
`;

export const ModalBtn = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
`;

export const OKBtn = styled.button`
  border-radius: 3px;
  border: none;
  background-color: #0f4c81;
  width: 100px;
  height: 39px;
  color: white;
  margin-left: 7px;
  cursor: pointer;
  margin-top: 35px;

  &:hover {
    background-color: rgba(15, 76, 129, 0.8);
    border: none;
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

export const ModalWrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 20px;
  height: 100%;
`;

export const MainText = styled.div`
  font-size: 16px;
  margin-bottom: 10px;
  padding-bottom: 10px;
  border-bottom: 1px solid #ced4da;
`;

export const SubText = styled.textarea`
  padding: 10px;
  font-size: 12px;
  color: #4e4e4e;
  width: 460px;
  height: 200px;
  border: 1px solid #ced4da;
  resize: none;

  &:focus {
    outline: none;
  }
`;

export const Container = styled.div`
  width: 500px;
  height: 350px;
  background-color: white;
  display: ${(props) => (props.signUpModal ? 'block' : 'none')};
  position: fixed;
  top: 50%;
  left: 50%;
  right: auto;
  bottom: auto;
  margin-right: -50%;
  transform: translate(-50%, -50%);
  z-index: 99;
`;
