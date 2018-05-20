// @flow
import { put, takeLatest, call, select } from 'redux-saga/effects';
import { home, modalsA } from 'actions';
import history from 'utils/history';
import { registration } from 'api';

function* register({ payload: user }: { payload: { user: { email: string, firstName: string, lastName: string, password: string, confirmPassword: string, birthday: Date, gender: string} } }) {
  try {
    const authSuccess = yield call(registration.register, user);

    // yield (document.cookie = `assess=${authSuccess.body.accessToken}`);
    // yield (document.cookie = `refresh=${authSuccess.body.refreshToken}`);
  
    // window.token = authSuccess.body.accessToken;
   

   
    yield put(home.registrationPendingSuccess());
  } catch (error) {
    yield put(home.registrationPendingError(error));
  }
}

export default function* watchFetchRegistration(): Object {
  yield takeLatest(home.registrationPending.getType(), register);
  
}




