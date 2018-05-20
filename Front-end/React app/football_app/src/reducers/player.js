import { createReducer } from 'redux-act';
import { playerA} from 'actions';

const initialState: {
    playerItem: Object,  
} = {    
  playerItem: {},
};

export default createReducer(
  {
    [playerA.playerPending]: state => ({
      ...state,      
      playerItem: {},
    }),
    [playerA.playerPendingSuccess]: (state, { playerItem }) => ({ ...state, playerItem })
  },  
  initialState,
);
