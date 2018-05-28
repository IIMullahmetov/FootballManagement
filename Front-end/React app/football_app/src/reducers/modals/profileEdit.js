import { createReducer } from 'redux-act';
import { modalsA, home } from 'actions';


const initialState: {
  open: boolean,
  failure: boolean, 
  firstName: string,
  lastName: string, 
  genders:  Array<{ value: number, text: number }>,
  error: Object,
  gender: string
} = {
  open: false,
  failure: false,  
  firstName: '',
  lastName: '',  
  genders: [
      {
        text: 'мужской',
        value: 'man'
      },
      {
        text: 'женский',
        value: 'woman'
      }

  ]
  ,
  gender: 'man',
};

export default createReducer(
  {
    [modalsA.open]: (state, { modal }) => (modal === 'profileEdit' ? { ...state, open: true } : state),
    [modalsA.close]: (state, { modal }) => (modal === 'profileEdit' ? { ...state, open: false } : state),
    [modalsA.set]: (state, { modal, event }) =>
      (modal === 'profileEdit' ? { ...state, [event.dataset.prop]: event.value } : state),
    [home.profileEditPendingError]: state => ({ ...state, failure: true }),
    [home.profileEditPendingSuccess]: () => ({
      open: false,
      failure: false,     
      firstName: '',
      lastName: '',     
      gender: '',
    }),
  },
  initialState,
);
