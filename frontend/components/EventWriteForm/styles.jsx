import styled from '@emotion/styled';

export const Container = styled.div`
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 100px;
  padding: 0px;
  width: 1200px;
`;

export const WriteHeader = styled.h3`
  padding: 50px 30px;
  font-weight: bold;
`;

export const Form = styled.div`
  margin-left: 30px;
  margin-right: 30px;
  height: auto;
  min-height: 100%;
  padding-bottom: 300px;
  font-size: 17px;
  color: #4a4a4a;
  font-weight: bold;
  & div {
    margin-bottom: 20px;
  }

  & .react-datepicker-wrapper {
    height: 0px;
  }

  & button.date-input {
    margin-left: 15px;
    border: 1px solid #ced4da;
    width: 240px;
    height: 30px;
    border-radius: 3px;
    background-color: white;
  }

  & span {
    color: #ced4da;
    margin: 5px 20px;
    font-size: 20px;
  }

  & div#content {
    margin-top: 20px;
  }

  & .file label {
    display: inline-block;
    width: 100px;
    height: 45px;
    background-color: #4a4a4a;
    color: #fff;
    cursor: pointer;
    line-height: 45px;
    border-radius: 5px;
    text-align: center;
  }

  & .file input[type='file'] {
    position: absolute;
    width: 1px;
    height: 1px;
    margin: -1px;
    clip: rect(0, 0, 0, 0); /* 클립에 범위만큼만 노출시킴 */
    overflow: hidden;
    padding: 0;
  }
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
  height: 35px;
  padding-top: 11px;
  padding-bottom: 13px;
  line-height: 1.33333333;
  font-size: 14px;
  resize: none;

  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }
`;

export const TextArea = styled.textarea`
  resize: none;
  border-radius: 3px;
  border: 1px solid #ced4da;
  transition: border 80ms ease-out, box-shadow 80ms ease-out;
  box-sizing: border-box;
  margin: 12px 0;
  margin-bottom: 0px;
  width: 100%;
  color: rgba(var(--sk_primary_foreground, 29, 28, 29), 1);
  background-color: rgba(var(--sk_primary_background, 255, 255, 255), 1);
  padding: 12px;
  height: 300px;
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
  &:hover {
    background-color: rgba(15, 76, 129, 0.8);
    border: none;
    cursor: pointer;
  }
  &:focus {
    --saf-0: rgba(var(--sk_highlight, 18, 100, 163), 1);
    box-shadow: 0 0 0 1px var(--saf-0), 0 0 0 2px rgba(29, 155, 209, 0.3);
  }

  & > a {
    text-decoration: none;
    color: white;
  }
`;

export const SelectBox = styled.select`
  border-radius: 3px;
  border: 1px solid #ced4da;
  height: 30px;
  margin-left: 15px;
`;
