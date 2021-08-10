import styled from '@emotion/styled';

export const LoginDiv = styled.div`
  width: 1200px;
  height: 100vh - 180px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  margin-bottom: 100px;
`;

export const SignUpDiv = styled.div`
  height: auto;
  min-height: 100%;
  padding-bottom: 300px;
`;

export const Form = styled.form`
  width: 300px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
`;

export const Search = styled.div`
  width: 300px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
  padding-bottom: 30px;
  border-bottom: 1px solid #ced4da;

  & span > a {
    color: #939597;
    font-size: 14px;
  }
`;

export const Login = styled.div`
  width: 300px;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
  position: relative;

  & h2 {
    width: 100%;
    margin: 40px 0;
    margin-bottom: 60px;
    font-size: 30px;
  }

  & span {
    font-size: 14px;
  }

  & span > a {
    color: #0f4c81;
    font-size: 14px;
  }
`;

export const GoLogIn = styled.div`
  text-align: right;
  display: flex;
  flex-direction: column;
`;

export const Social = styled.div`
  margin-top: 25px;
  width: 300px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  text-align: center;
`;

export const Input = styled.input`
  border-radius: 3px;
  border: 1px solid #ced4da;
  transition: border 80ms ease-out, box-shadow 80ms ease-out;
  box-sizing: border-box;
  margin: 10px 0;
  margin-bottom: 0px;
  width: 100%;
  color: rgba(var(--sk_primary_foreground, 29, 28, 29), 1);
  background-color: rgba(var(--sk_primary_background, 255, 255, 255), 1);
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

export const Button = styled.button`
  text-align: center;
  align-items: center;
  justify-content: center;
  margin-top: 12px;
  margin-bottom: 12px;
  width: 100%;
  max-width: 100%;
  color: #fff;
  background-color: #0f4c81;
  border: none;
  font-size: 14px;
  font-weight: 900;
  height: 48px;
  min-width: 96px;
  transition: all 80ms linear;
  user-select: none;
  outline: none;
  cursor: pointer;
  border-radius: 3px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
  &:hover {
    background-color: rgba(15, 76, 129, 0.8);
    border: none;
  }
  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }
`;

export const FaceBook = styled.button`
  text-align: center;
  align-items: center;
  justify-content: center;
  margin-bottom: 12px;
  width: 100%;
  max-width: 100%;
  color: #fff;
  background-color: #4167b2;
  border: none;
  font-size: 14px;
  font-weight: 900;
  height: 48px;
  min-width: 96px;
  transition: all 80ms linear;
  user-select: none;
  outline: none;
  cursor: pointer;
  border-radius: 3px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
  &:hover {
    background-color: rgba(65, 103, 178, 0.8);
    border: none;
  }
  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }
  & i {
    font-size: 16px;
    text-align: center;
  }
`;

export const Google = styled.button`
  text-align: center;
  align-items: center;
  justify-content: center;
  width: 100%;
  max-width: 100%;
  color: #fff;
  background-color: #f13f31;
  border: none;
  font-size: 14px;
  font-weight: 900;
  height: 48px;
  min-width: 96px;
  transition: all 80ms linear;
  user-select: none;
  outline: none;
  cursor: pointer;
  border-radius: 3px;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
  &:hover {
    background-color: rgba(241, 63, 49, 0.8);
    border: none;
  }
  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }
  & i {
    font-size: 16px;
    text-align: center;
  }
`;
