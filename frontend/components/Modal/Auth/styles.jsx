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

export const ModalBtn = styled.button`
  border-radius: 3px;
  border: none;
  background-color: #900020;
  width: 225px;
  height: 39px;
  color: white;
  cursor: pointer;
  margin-top: 10px;

  &:hover {
    background-color: rgba(144, 0, 32, 0.8);
    border: none;
  }
`;

export const ModalWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100%;
`;

export const MainText = styled.div`
  font-size: 18px;
`;

export const SubText = styled.div`
  padding-top: 5px;
  font-size: 12px;
  color: #c4c4c4;
`;

export const Container = styled.div`
  width: 420px;
  height: 190px;
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

export const TextInput = styled.input`
  border: 1px solid #ced4da;
  border-radius: 3px;
  width: 225px;
  margin-top: 8px;
  padding: 12px;
  height: 38px;
  padding-top: 11px;
  padding-bottom: 13px;
  line-height: 1.33333333;
  font-size: 14px;

  &:focus {
    outline: none;
  }
`;
