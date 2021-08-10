import React from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import Auth from '../../hoc/auth';
import ScrollToTop from '../../utils/scrollToTop';

import Main from '@pages/Main';
import LogIn from '@pages/LogIn';
import SignUp from '@pages/SignUp';
import SignUp2 from '@pages/SignUp/SignUp2';
import MyBookRecord from '@pages/MyBookRecord';
import BRWrite from '@pages/MyBookRecord/BRWrite';
import BRContent from '@pages/MyBookRecord/BRContent';
import Discussion from '@pages/Discussion';
import DCContent from '@pages/Discussion/DCContent';
import DCEdit from '@pages/Discussion/DCEdit';
import DCWrite from '@pages/Discussion/DCWrite';
import MyPageProfile from '@pages/MyPage/Profile';
import ShareBoard from '@pages/ShareBoard';
import ShareBoardWrite from '@pages/ShareBoard/Write';
import ShareDetail from '@pages/ShareBoard/ShareDetail';
import Note from '@pages/Note';
import Event from '@pages/Event/Now';
import EventDetail from '@pages/Event/Detail';
import EventEnd from '@pages/Event/End';
import EventWrite from '@pages/Event/Write';
import Admin from '@pages/Admin';
import Monthly from '@pages/Admin/Monthly';

const App = () => {
  return (
    <div>
      <ScrollToTop>
        <Switch>
          <Redirect exact path="/" to="/main" />
          <Route path="/main" component={Main} />
          <Route path="/login" component={Auth(LogIn, 'GUEST')} />
          <Route path="/signup" component={Auth(SignUp, 'GUEST')} />
          <Route path="/signup2" component={Auth(SignUp2, 'GUEST')} />
          <Route path="/mybookrecord/detail/:boardId" component={BRContent} />
          <Route path="/mybookrecord/write" component={Auth(BRWrite, 'USER')} />
          <Route path="/mybookrecord" component={Auth(MyBookRecord, 'USER')} />
          <Route path="/discussion/write" component={Auth(DCWrite, 'USER')} />
          <Route path="/discussion/content/:id" component={DCContent} />
          <Route path="/discussion/edit/:id" component={DCEdit} />
          <Route path="/discussion" component={Discussion} />
          <Route path="/shareboard/detail/:boardId" component={ShareDetail} />
          <Route path="/shareboard/write" component={ShareBoardWrite} />
          <Route path="/shareboard" component={ShareBoard} />
          <Route path="/event/now/:id" component={EventDetail} />
          <Route path="/event/now" component={Auth(Event, 'USER')} />
          <Route path="/event/write" component={Auth(EventWrite, 'ADMIN')} />
          <Route path="/event/end" component={EventEnd} />
          <Route path="/note" component={Note} />
          <Route path="/mypage/profile" component={Auth(MyPageProfile, 'USER')} />
          <Route path="/admin/monthly" component={Auth(Monthly, 'ADMIN')} />
          <Route path="/admin" component={Auth(Admin, 'ADMIN')} />
        </Switch>
      </ScrollToTop>
    </div>
  );
};

export default App;
