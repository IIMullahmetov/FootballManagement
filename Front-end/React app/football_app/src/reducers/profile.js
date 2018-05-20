import { createReducer } from 'redux-act';
import { profileA} from 'actions';

const initialState: {
    profileItem: Object,  
} = {    
  profileItem: {},
};

export default createReducer(
  {
    [profileA.profilePending]: state => ({
      ...state,      
     profileItem: {},
    }),
    [profileA.profilePendingSuccess]: (state, { profileItem }) => ({ ...state, profileItem })
  },  
  initialState,
);
