import React from 'react';
import {
  FooterWrapper,
  SocialLinks,
  FooterLinks,
  FooterLink,
  CopyRight,
  CopyRights,
  CompanyName,
  FooterHeadWrapper,
  FooterFixed,
} from '@layouts/Footer/styles';

// import { Copyright } from '@material-ui/icons';

const Footer = () => {
  return (
    <FooterWrapper>
      <FooterFixed>
        <FooterHeadWrapper>
          <FooterLinks>
            <FooterLink>
              <a> 자주 묻는 질문 </a>
            </FooterLink>
            <FooterLink>
              <a> 문의하기 </a>
            </FooterLink>
            <FooterLink>
              <a> 공지사항 </a>
            </FooterLink>
            <FooterLink>
              <a> 이용 약관 </a>
            </FooterLink>
          </FooterLinks>
          <SocialLinks>
            <i className="fab fa-facebook-square fa-2x"></i>
            <i className="fab fa-instagram fa-2x"></i>
            <i className="fab fa-twitter fa-2x"></i>
            <i className="fab fa-youtube fa-2x"></i>
          </SocialLinks>
        </FooterHeadWrapper>
        <CompanyName>주식회사 Go-Getter</CompanyName>
        <CopyRights>
          <CopyRight>팀장: 변현우 | 전화번호: 010-1234-5678</CopyRight>
          <CopyRight>주소: 대한민국 서울특별시 강남구 000-000, 센트로폴리스 A동 20층 우편번호 123456</CopyRight>
          <CopyRight>사업자등록번호: 123-45-678910</CopyRight>
          <CopyRight>클라우드 호스팅: Amazon Web Services Inc.</CopyRight>
        </CopyRights>
      </FooterFixed>
    </FooterWrapper>
  );
};

export default Footer;
