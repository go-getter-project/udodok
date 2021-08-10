const NEWPARTNER = 'conversation/NEWPARTNER';
const NEWMESSAGE = 'conversation/NEWMESSAGE';
const RECEIVED = 'conversation/RECEIVED';
const CHATLIST = 'conversation/ADDCHATLIST';

export const insertPartner = (partner) => ({ type: NEWPARTNER, payload: partner });
export const insertMessage = (msg) => ({ type: NEWMESSAGE, payload: msg });
export const receive = (msg) => ({ type: RECEIVED, payload: msg });
export const conversationList = (partner) => ({ type: CHATLIST, payload: partner });

const initialState = [];

const Conversation = (state = initialState, action) => {
  switch (action.type) {
    case NEWPARTNER:
      // console.log(action.payload.list);
      state[action.payload.partner] = [...action.payload.list];
      return { ...state };
    case CHATLIST:
      // console.log(action.payload);
      state[action.payload.partner] = [...state[action.payload.partner], action.payload.list];
      return { ...state };
    case NEWMESSAGE:
      // console.log(action.payload);
      state[action.payload.the_other_user_id] = [...state[action.payload.the_other_user_id], action.payload];
      return { ...state };
    case RECEIVED:
      // console.log(action.payload);
      if (state[action.payload.the_other_user_id] === undefined) {
        state[action.payload.the_other_user_id] = [action.payload];
      } else {
        state[action.payload.the_other_user_id] = [...state[action.payload.the_other_user_id], action.payload];
      }
      return { ...state };
    default:
      return state;
  }
};

export default Conversation;
