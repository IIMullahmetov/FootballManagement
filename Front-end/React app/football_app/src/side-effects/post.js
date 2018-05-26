// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { postA } from 'actions';
import history from 'utils/history';
import { post  } from 'api';

function* getPost({ payload: { postId } }) {
  try {
    console.log("post id: " + postId);
    const {body}: {body: Object} = yield call(post.getPost, postId);

    const postItem = body;

    console.log("match object: " + postItem.id);

    yield put(postA.postPendingSuccess(postItem)); 
  } catch(error) {
    console.log(error);
    yield put(postA.postPendingError(error));
  }

}

function* getPostList() {
  try {
    const  { body }: { body: Object } = yield call(post.getList);  

    const postList = body.items;

    yield put(postA.postListPendingSuccess(postList));
  } catch (error) {
    yield put(postA.postListPendingError(error));
  }
}




export default function* watchFetchPost(): Object { 
  yield takeLatest(postA.postPending.getType(), getPost);
  yield takeLatest(postA.postListPending.getType(), getPostList);
  
}