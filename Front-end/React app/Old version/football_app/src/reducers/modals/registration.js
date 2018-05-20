import { createReducer } from 'redux-act';
import { modalsA, home } from 'actions';
import moment from 'moment';

const initialState: {
  open: boolean,
  failure: boolean,
  email: string,
  firstName: string,
  lastName: string,
  password: string,
  confirmPassword: string,
  birthDay: date,
  gender: string,
  error: Object,
} = {
  open: false,
  failure: false,
  email: '',
  firstName: '',
  lastName: '',
  password: '',
  confirmPassword: '',
  birthDay: moment().format('dddd, MMMM DD YYYY, h:mm:ss'),
  gender: '',
};

export default createReducer(
  {
    [modalsA.open]: (state, { modal }) => (modal === 'registration' ? { ...state, open: true } : state),
    [modalsA.close]: (state, { modal }) => (modal === 'registration' ? { ...state, open: false } : state),
    [modalsA.set]: (state, { modal, event }) =>
      (modal === 'registration' ? { ...state, [event.dataset.prop]: event.value } : state),
    [home.registrationPendingError]: state => ({ ...state, failure: true }),
    [home.registrationPendingSuccess]: () => ({
      open: false,
      failure: false,
      email: '',
      firstName: '',
      lastName: '',
      password: '',
      confirmPassword: '',
      birthDay: moment().format('dddd, MMMM DD YYYY, h:mm:ss'),
      gender: '',
    }),
  },
  initialState,
);
