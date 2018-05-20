import { createReducer } from 'redux-act';
import { matchA} from 'actions';

const initialState: {
  lastMatchesList: Array<Object>,
  matchItem: Object,
  
} = {  
  lastMatchesList: [],
  matchItem: {}
};

export default createReducer(

  {
    [matchA.matchListPending]: state => ({
      ...state,      
      lastMatchesList: [],
    }),
    [matchA.matchListPendingSuccess]: (state, { lastMatchesList }) => ({ ...state, lastMatchesList }),

    [matchA.matchPending]: state => ({
      ...state,      
      matchItem: {},
    }),
    [matchA.matchPendingSuccess]: (state, { matchItem }) => ({ ...state, matchItem })
  },
  
  initialState,
);
