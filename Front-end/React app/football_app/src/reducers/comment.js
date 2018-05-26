import { createReducer } from 'redux-act';
import { commentA} from 'actions';

const initialState: {
  commentList: Array<Object>, 
  comment: string
} = {  
  commentList: [],
  comment: ''
};

export default createReducer(

  {
    [commentA.commentListPending]: state => ({
      ...state,      
      commentList: [],
    }),
    [commentA.commentListPendingSuccess]: (state, { commentList }) => ({ ...state, commentList }), 
   [commentA.set]: (state, { event }) =>
      ( {...state, [event.dataset.prop]: event.value } : state),
      
},

  initialState,
);
