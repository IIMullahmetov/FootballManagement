// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { profileA } from 'actions';
import history from 'utils/history';
import { profile  } from 'api';

function* getProfile() {
  try {
    
    const {body}: {body: Object} = yield call(profile.getProfile);

    const profileItem = body;

    console.log("profile object: " + profileItem.id);

    yield put(profileA.profilePendingSuccess(profileItem)); 
  } catch(error) {
    console.log(error);
    yield put(profileA.profilePendingError(error));
  }

}

export default function* watchFetchProfile(): Object { 
  yield takeLatest(profileA.profilePending.getType(), getProfile);
  
}