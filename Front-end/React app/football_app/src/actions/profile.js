import { createAction } from 'redux-act';

export default { 
  profilePending: createAction('profile pending'),
  profilePendingSuccess: createAction('profile pending success', (profileItem) => ({ profileItem})),
  profilePendingError: createAction('profile pending error', (error: Object) => ({ error })), 

  profileEditPending: createAction('profile edit pending', (user: { firstName: string, lastName: string, gender: string}) => ({ user })),
  profileEditPendingSuccess: createAction('profile edit pending success', (user: Object) => ({ user })),
  profileEditPendingError: createAction('profile edit pending error', (error: Object) => ({ error })),



};
