// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { commentA } from 'actions';
import history from 'utils/history';
import { comment } from 'api';

function* getCommentList() {
  try {
    const  { body }: { body: Object } = yield call(comment.getList);  

    const commentList = body.items;

    yield put(commentA.commentListPendingSuccess(commentList));
  } catch (error) {
    yield put(commentA.commentListPendingError(error));
  }
}


export default function* watchFetchComment(): Object {
  yield takeLatest(commentA.commentListPending.getType(), getCommentList); 
}

