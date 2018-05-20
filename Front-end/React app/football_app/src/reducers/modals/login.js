import { createReducer } from 'redux-act';
import { modalsA, home } from 'actions';

const initialState: {
  open: boolean,
  failure: boolean,
  email: string,
  password: string,
  error: Object,
} = {
  open: false,
  failure: false,
  email: '',
  password: '',
};

export default createReducer(
  {
    [modalsA.open]: (state, { modal }) => (modal === 'login' ? { ...state, open: true } : state),
    [modalsA.close]: (state, { modal }) => (modal === 'login' ? { ...state, open: false } : state),
    [modalsA.set]: (state, { modal, event }) =>
      (modal === 'login' ? { ...state, [event.dataset.prop]: event.value } : state),
    [home.loginPendingError]: state => ({ ...state, failure: true }),
    [home.loginPendingSuccess]: () => ({
      open: false,
      failure: false,
      email: '',
      password: '',
    }),
  },
  initialState,
);
