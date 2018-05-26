import { createAction } from 'redux-act';

export default {
  commentListPending: createAction('comment list pending', postId => ({ postId})),
  commentListPendingSuccess: createAction('comment list pending success', (commentList) => ({ commentList  })),
  commentListPendingError: createAction('comment list pending error', (error: Object) => ({ error })),  

  commentAddPending: createAction('comment add pending', (commentText, postId) => ({commentText, postId})),
  commentAddPendingSuccess: createAction('comment add pending success'),
  commentAddPendingError: createAction('comment add pending error', (error: Object) => ({ error })),  

  set: createAction(
    'set comment property',
    (event: { value: string, dataset: { prop: string } }) => ({ event }),
  ),






};
