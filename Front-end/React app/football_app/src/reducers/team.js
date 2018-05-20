import { createReducer } from 'redux-act';
import { teamA} from 'actions';

const initialState: {
    teamItem: Object,  
} = {    
  teamItem: {},
};

export default createReducer(
  {
    [teamA.teamPending]: state => ({
      ...state,      
      teamItem: {},
    }),
    [teamA.teamPendingSuccess]: (state, { teamItem }) => ({ ...state, teamItem })
  },  
  initialState,
);
