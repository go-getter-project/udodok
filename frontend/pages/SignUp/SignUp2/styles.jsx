import styled from '@emotion/styled';

export const SignUpWrapper = styled.div`
  margin-top: 40px;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 100px;
  padding: 20px;
  width: 500px;
`;

export const SignUpDiv = styled.div`
  height: auto;
  min-height: 100%;
  padding-bottom: 300px;
`;

export const SignUpTitle = styled.div`
  font-size: 30px;
  font-weight: bold;
`;

export const SignForm = styled.form`
  margin-top: 40px;
`;

export const FormGroup = styled.div`
  margin-bottom: 20px;
`;

export const Label = styled.label`
  font-size: 16px;
  margin-bottom: 0px;

  & span {
    color: blue;
  }
`;

export const Email = styled.div`
  width: 460px;
`;

export const EmailInput = styled.input`
  border: 1px solid #ced4da;
  border-radius: 3px;
  width: 353px;
  height: 45px;
  margin-top: 8px;
  padding: 12px;
  height: 38px;
  padding-top: 11px;
  padding-bottom: 13px;
  line-height: 1.33333333;
  font-size: 14px;

  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }
`;
export const TextInput = styled.input`
  border: 1px solid #ced4da;
  border-radius: 3px;
  width: 460px;
  margin-top: 8px;
  padding: 12px;
  height: 38px;
  padding-top: 11px;
  padding-bottom: 13px;
  line-height: 1.33333333;
  font-size: 14px;

  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }
`;

export const SmallText = styled.div`
  font-size: 13px;
  color: #939597;
`;

export const FormCheck = styled.div`
  margin-top: 50px;
`;

export const CheckText = styled.span`
  font-size: 12px;
  padding-left: 5px;

  & a {
    color: #1778b5;
    :hover {
      color: red;
      cursor: pointer;
    }
  }
`;

export const Error = styled.div`
  color: #e01e5a;
  margin: 8px 0 16px;
  font-weight: bold;
  font-size: 12px;
`;

export const AuthButton = styled.button`
  border-radius: 3px;
  border: none;
  background-color: #900020;
  width: 100px;
  height: 38px;
  color: white;
  margin-left: 7px;
  cursor: pointer;
  margin-top: 8px;

  &:hover {
    background-color: rgba(144, 0, 32, 0.8);
    border: none;
  }
`;

export const AuthSuccessBtn = styled.button`
  border-radius: 3px;
  border: none;
  background-color: #939597;
  width: 100px;
  height: 38px;
  color: white;
  margin-left: 7px;
  cursor: pointer;
  margin-top: 8px;
  pointer-events: none;
`;

export const SignUpButton = styled.button`
  font-size: 16px;
  color: white;
  background-color: #0f4c81;
  border: 0px;
  border-radius: 3px;
  width: 460px;
  height: 47px;
  margin-top: 8px;
  cursor: pointer;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);

  &:hover {
    background-color: rgba(15, 76, 129, 0.8);
    border: none;
  }
`;
