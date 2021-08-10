import React, { useState, useCallback, useEffect } from 'react';
import { Container, Form, WriteHeader, Input, TextArea, Button, SelectBox } from '@components/EventWriteForm/styles';
import useInput from '@hooks/useInput';
import axios from 'axios';
import { Link, useHistory } from 'react-router-dom';
import { useSelector } from 'react-redux';
import DatePicker from 'react-datepicker';
import { ko } from 'date-fns/esm/locale';
import 'react-datepicker/dist/react-datepicker.css';
import apiController from '@apis/apiController';

const EventWriteForm = ({ setSuccessWrite }) => {
  const users = useSelector((state) => state.auth.user);
  const history = useHistory();
  const DateInput = ({ value, onClick }) => (
    <button className="date-input" onClick={onClick}>
      {value}
    </button>
  );
  const [dateRange, setDateRange] = useState([null, null]);
  const [startDate, endDate] = dateRange;
  const [title, onChangeTitle] = useInput('');
  const [content, onChangeContent] = useInput('');
  const [img_url, setImg_Url] = useInput('');
  const [post, setPost] = useState();
  const [select, setSelect] = useState();
  const [coupon_id, setCoupon_Id] = useState();

  useEffect(() => {
    apiController({
      url: `/admin/events/coupons`,
      method: 'get',
    })
      .then((res) => {
        setPost(res.data.data);
      })
      .catch((err) => {
        console.dir(err);
      });
  }, []);

  const onSubmit = (e) => {
    console.log(users.user_id);
    if (!title.length || !title.trim().length) {
      alert('제목을 작성해주세요.');
    } else if (!content.length || !content.trim().length) {
      alert('내용을 작성해주세요.');
    } else {
      let params = {
        content,
        title,
        img_url,
        coupon_id,
        start_date: dateRange[0],
        end_date: dateRange[1],
      };
      apiController({
        url: `/admin/events`,
        method: 'post',
        data: params,
      })
        .then((res) => {
          alert('성공적으로 작성되었습니다.');
          setSuccessWrite(true);
          history.push('/event/now');
        })
        .catch((err) => {
          console.dir(err);
        });
    }
  };

  const handleChange = (e) => {
    const { value } = e.target;
    setCoupon_Id(value);
  };

  return (
    <div>
      <Container>
        <WriteHeader>이벤트 게시글 작성</WriteHeader>
        <Form>
          <div id="title">
            제목
            <Input id="title" name="title" placeholder="제목을 입력해주세요." onChange={onChangeTitle} value={title} />
          </div>
          <div id="coupon_id">
            쿠폰
            <SelectBox onChange={handleChange} value={select}>
              <option>선택</option>
              {post &&
                post.map((item, idx) => (
                  <React.Fragment key={idx}>
                    <option value={item.coupon_id}>{item.coupon_name}</option>
                  </React.Fragment>
                ))}
            </SelectBox>
          </div>
          <div className="date">
            기간
            <DatePicker
              fixedHeight
              selectsRange={true}
              startDate={startDate}
              endDate={endDate}
              onChange={(update) => setDateRange(update)}
              locale={ko}
              dateFormat="yyyy-MM-dd"
              placeholderText="이벤트 날짜 선택"
              isClearable={true}
              customInput={<DateInput />}
            />
          </div>
          <div id="content">
            내용
            <TextArea
              id="content"
              name="content"
              placeholder="내용을 입력해주세요."
              onChange={onChangeContent}
              value={content}
            />
          </div>
          <div className="file">
            <label for="file">첨부파일</label>
            <input type="file" id="file" />
          </div>

          <Button type="submit" onClick={onSubmit}>
            작성하기
          </Button>
        </Form>
      </Container>
    </div>
  );
};
export default EventWriteForm;
