import { createReducer } from 'redux-act';
import { home } from 'actions';

const initialState: {
  loginData: Object,
  user: Object,
} = {
  loginData: JSON.parse(localStorage.getItem('context')) || {},
  user: JSON.parse(localStorage.getItem('user')) || {},
};

export default createReducer(
  {
    [home.loginPendingSuccess]: (state, { user }) => ({ ...state, user }),
    [home.loginContextPendingSuccess]: (state, { data }) => ({
      ...state,
      loginData: data,
    }),
    [home.logoutPendingSuccess]: () => ({ loginData: {}, user: {} }),
  },
  initialState,
);
