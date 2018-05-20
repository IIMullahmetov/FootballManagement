import { createReducer } from 'redux-act';
import { commentA} from 'actions';

const initialState: {
  commentList: Array<Object>, 
} = {  
  commentList: [],
};

export default createReducer(

  {
    [commentA.commentListPending]: state => ({
      ...state,      
      commentList: [],
    }),
    [commentA.commentPendingSuccess]: (state, { commentList }) => ({ ...state, commentList }),  

  initialState,
);
