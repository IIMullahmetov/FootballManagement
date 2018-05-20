import { createAction } from 'redux-act';

export default { 
  profilePending: createAction('profile pending'),
  profilePendingSuccess: createAction('profile pending success', (profileItem) => ({ profileItem})),
  profilePendingError: createAction('profile pending error', (error: Object) => ({ error })), 



};
