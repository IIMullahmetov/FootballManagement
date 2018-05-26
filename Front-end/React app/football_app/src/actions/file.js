import { createAction } from 'redux-act';

export default {
  fileUploadPending: createAction('file upload pending'),
  commentListPendingSuccess: createAction('comment list pending success', (commentList) => ({ commentList  })),
  commentListPendingError: createAction('comment list pending error', (error: Object) => ({ error })),  




};
