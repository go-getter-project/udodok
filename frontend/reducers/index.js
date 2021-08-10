import { combineReducers } from 'redux';
import auth from '@reducers/auth';
import message from '@reducers/message';
import conversation from '@reducers/conversation';

export default combineReducers({
  auth,
  message,
  conversation,
});
