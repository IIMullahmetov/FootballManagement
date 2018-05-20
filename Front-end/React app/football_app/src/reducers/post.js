import { createReducer } from 'redux-act';
import { postA} from 'actions';

const initialState: {
    postItem: Object, 
    postList: Array<object> 
} = {    
    postItem: {},
    postList:[],
};

export default createReducer(
  {
    [postA.postPending]: state => ({
      ...state,      
      postItem: {},
    }),
    [postA.postPendingSuccess]: (state, { postItem }) => ({ ...state, postItem }),


    [postA.postListPending]: state => ({
      ...state,      
      postList: [],
    }),
    [postA.postListPendingSuccess]: (state, { postList }) => ({ ...state, postList }),
  },  
  initialState,
);
