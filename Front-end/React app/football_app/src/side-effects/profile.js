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

function* profileEdit({ payload: user }: { payload: { user: { firstName: string, lastName: string, gender: string} } }) {
  try {
    const editSuccess = yield call(profile.editProfile, user);

    yield call([history, history.push], '/');

    // yield (document.cookie = `assess=${authSuccess.body.accessToken}`);
    // yield (document.cookie = `refresh=${authSuccess.body.refreshToken}`);
  
    // window.token = authSuccess.body.accessToken;
   

   
    yield put(profileA.profileEditPendingSuccess());
  } catch (error) {
    console.log(error);
    yield put(profileA.profileEditPendingError(error));
  }
}

export default function* watchFetchProfile(): Object { 
  yield takeLatest(profileA.profilePending.getType(), getProfile);
  yield takeLatest(profileA.profileEditPending.getType(), profileEdit);
  
}