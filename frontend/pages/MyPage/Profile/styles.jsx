import styled from '@emotion/styled';

export const AllDiv = styled.div`
  width: 1200px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  margin-bottom: 100px;
`;

export const MyPageDiv = styled.div`
  height: auto;
  min-height: 100%;
  padding-bottom: 300px;
`;

export const ContentDiv = styled.div`
  width: 950px;
  padding-top: 40px;
`;

export const ProfileDiv = styled.div`
  width: 500px;
  position: relative;
  left: 50%;
  transform: translateX(-50%);
  padding: 10px 25px;

  & h2 {
    font-weight: bold;
    font-size: 30px;
    margin: 0 auto;
    margin-bottom: 10px;
  }
`;

export const Profile = styled.div`
  width: 100vh;
  height: 200px;
  display: flex;
`;

export const CantModified = styled.div`
  width: 225px;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  font-weight: bold;

  & span {
    padding-left: 5px;
    margin: 5px;
    font-weight: normal;
  }
`;

export const ProfileImage = styled.div`
  width: 225px;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const Label = styled.label`
  font-size: 16px;
  margin-bottom: 0px;

  & span {
    color: blue;
  }
`;

export const ProfileForm = styled.form`
  margin-top: 20px;
`;

export const FormGroup = styled.div`
  margin-bottom: 20px;
`;

export const Input = styled.input`
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

export const ModifiedBtn = styled.button`
  font-size: 16px;
  color: white;
  background-color: #0f4c81;
  border: 0px;
  border-radius: 3px;
  width: 460px;
  height: 47px;
  margin-top: 25px;
  cursor: pointer;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);

  &:hover {
    background-color: rgba(15, 76, 129, 0.8);
    border: none;
  }
`;
