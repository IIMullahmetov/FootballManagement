// @flow
import { put, takeLatest, call, select } from 'redux-saga/effects';
import { home, modalsA } from 'actions';
import history from 'utils/history';
import { auth } from 'api';

import { getConfirmAnswer } from 'selectors';

function* login({ payload: user }: { payload: { user: { email: string, password: string } } }) {
  try {
    const authSuccess = yield call(auth.login, user);

    // yield (document.cookie = `assess=${authSuccess.body.accessToken}`);
    // yield (document.cookie = `refresh=${authSuccess.body.refreshToken}`);
    yield localStorage.setItem('access', authSuccess.body.accessToken);
    yield localStorage.setItem('refresh', authSuccess.body.refreshToken);
    yield localStorage.setItem('role', authSuccess.body.role);
    // window.token = authSuccess.body.accessToken;   

    yield call([history, history.push], '/');
    yield put(home.loginPendingSuccess());
  } catch (error) {
    yield put(home.loginPendingError(error));
  }
}

function* fetchLoginContext() {
  try {
    const data: Object = yield call(auth.loginContext);
    const info = JSON.parse(data.text);

    yield localStorage.setItem('context', JSON.stringify(info));

    yield put(home.loginContextPendingSuccess(info));
  } catch (error) {
    yield put(home.loginContextPendingError(error));
  }
}

function* logout() {
  try {
    const isLogout = yield select(getConfirmAnswer);
    console.log('isLogout', isLogout);
    if (isLogout.type === 'logout' && isLogout.value) {
      yield localStorage.removeItem('access');
      yield localStorage.removeItem('user');
      yield localStorage.removeItem('context');

      yield call([history, history.push], '/login');
      yield put(home.logoutPendingSuccess());
    }
  } catch (error) {
    yield put(home.logoutPendingError(error));
  }
}

export default function* watchFetchHouses(): Object {
  yield takeLatest(home.loginPending.getType(), login);
  yield takeLatest(home.loginContextPending.getType(), fetchLoginContext);
  yield takeLatest(home.logoutPending.getType(), logout);

  yield takeLatest(modalsA.confirm.getType(), logout);
}
