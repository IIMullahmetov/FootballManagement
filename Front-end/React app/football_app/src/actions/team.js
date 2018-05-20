import { createAction } from 'redux-act';

export default { 
  teamPending: createAction('team pending', teamId => ({ teamId })),
  teamPendingSuccess: createAction('team pending success', (teamItem) => ({ teamItem})),
  teamPendingError: createAction('team pending error', (error: Object) => ({ error })), 



};
