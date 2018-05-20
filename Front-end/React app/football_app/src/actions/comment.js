import { createAction } from 'redux-act';

export default {
  commentListPending: createAction('comment list pending'),
  commentListPendingSuccess: createAction('comment list pending success', (commentList) => ({ commentList  })),
  commentListPendingError: createAction('comment list pending error', (error: Object) => ({ error })),  




};
