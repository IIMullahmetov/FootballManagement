import { createAction } from 'redux-act';

export default {
  tourneyListPending: createAction('tourney list pending'),
  tourneyListPendingSuccess: createAction('tourney list pending success', (tourneyConfig) => ({ tourneyConfig  })),
  tourneyListPendingError: createAction('tourney list pending error', (error: Object) => ({ error })),  

  tourneyPending: createAction('tourney pending', tourneyId => ({ tourneyId })),
  tourneyPendingSuccess: createAction('tourney pending success', (tourneyItem) => ({ tourneyItem})),
  tourneyPendingError: createAction('tourney pending error', (error: Object) => ({ error })), 

   setTourneyConfig: createAction('set tourney config', id => ({ id })),
  setTourneyConfigSuccess: createAction('set tourney config success'),



};
