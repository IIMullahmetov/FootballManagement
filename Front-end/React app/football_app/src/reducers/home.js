import { createReducer } from 'redux-act';
import { home } from 'actions';

const initialState: {
  loginData: Object,
  user: Object,
  isLogin: boolean,
} = {
  loginData: localStorage.getItem('role') || {},
  user: JSON.parse(localStorage.getItem('user')) || {}, 
  isLogin: localStorage.getItem('role') ? true: false,
};

export default createReducer(
  {
   
    [home.loginPendingSuccess]: (state, { role }) => ({
      ...state,
      loginData: role,
      isLogin: true,
    }),
    [home.logoutPendingSuccess]: () => ({ loginData: {}, user: {} , isLogin: false}),
  },
  initialState,
);
