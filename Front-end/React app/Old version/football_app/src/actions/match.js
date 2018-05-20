import { createAction } from 'redux-act';

export default {
  matchListPending: createAction('match list pending'),
  matchListPendingSuccess: createAction('match list pending success', (lastMatchesList) => ({ lastMatchesList  })),
  matchListPendingError: createAction('match list pending error', (error: Object) => ({ error })),  

  matchPending: createAction('match pending', matchId => ({ matchId })),
  matchPendingSuccess: createAction('match pending success', (matchItem) => ({ matchItem})),
  matchPendingError: createAction('match pending error', (error: Object) => ({ error })), 



};
