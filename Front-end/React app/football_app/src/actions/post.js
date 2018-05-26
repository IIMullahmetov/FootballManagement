import { createAction } from 'redux-act';

export default { 
  postPending: createAction('post pending', postId => ({ postId })),
  postPendingSuccess: createAction('post pending success', (postItem) => ({ postItem})),
  postPendingError: createAction('post pending error', (error: Object) => ({ error })), 

  postListPending: createAction('post list pending'),
  postListPendingSuccess: createAction('post list pending success', (postList) => ({ postList  })),
 postListPendingError: createAction('post list pending error', (error: Object) => ({ error })),  

 addTextInput: createAction('add text input'),



};
