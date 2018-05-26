// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { commentA } from 'actions';
import history from 'utils/history';
import { comment } from 'api';

function* getCommentList({payload: { postId}}) {
  try {
    const  { body }: { body: Object } = yield call(comment.getList, postId);  

    const commentList = body.items;

    yield put(commentA.commentListPendingSuccess(commentList));
  } catch (error) {
    yield put(commentA.commentListPendingError(error));
  }
}

function* addComment({payload: { commentText, postId}}) {
  try {
  	console.log("aaaaaaaaaaaaaaaaaaaaaaaa");
  	const commentBody: {comment: string}= {comment : commentText};
  	console.log(commentBody);
    const  { body }: { body: Object } = yield call(comment.addComment, commentBody, postId);  

   console.log(body);
   
  } catch (error) {
  	console.log(error);
    yield put(commentA.commentListPendingError(error));
  }
}


export default function* watchFetchComment(): Object {
  yield takeLatest(commentA.commentListPending.getType(), getCommentList);
  yield takeLatest(commentA.commentAddPending.getType(), addComment); 
}

