import { createAction } from 'redux-act';

export default {
  

  playerPending: createAction('player pending', playerId => ({ playerId })),
  playerPendingSuccess: createAction('player pending success', (playerItem) => ({ playerItem})),
  playerPendingError: createAction('player pending error', (error: Object) => ({ error })), 



};