import { createReducer } from 'redux-act';
import { tourneyA} from 'actions';

const initialState: {
  tourneyList: Array<Object>,
  tourneyConfig: { tourneys: Array<{ value: number, text: string }> },
  tourneyItem: Object,
  
} = {  
  tourneyList: [],
  tourneyItem: {},
   tourneyConfig: { tourneys: [] }
};

export default createReducer(

  {
    [tourneyA.tourneyListPending]: state => ({
      ...state,      
      tourneyList: [],
    }),
    [tourneyA.tourneyListPendingSuccess]: (state, { tourneyConfig }) => ({ ...state, tourneyConfig }),

    [tourneyA.tourneyPending]: state => ({
      ...state,      
      tourneyItem: {},
    }),
    [tourneyA.tourneyPendingSuccess]: (state, { tourneyItem }) => ({ ...state, tourneyItem })
  },
  
  initialState,
);
