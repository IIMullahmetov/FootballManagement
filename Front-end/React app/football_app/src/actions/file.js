import { createAction } from 'redux-act';

export default {
  fileUploadPending: createAction('file upload pending', (files: Object) => ({ files  })),
  fileUploadPendingSuccess: createAction('file upload pending success', (error) => ({ error  })),
  




};
