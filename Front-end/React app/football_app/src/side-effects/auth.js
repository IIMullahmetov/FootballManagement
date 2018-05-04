/*import { put, takeLatest, call, select } from 'redux-saga/effects';
import { homeA, modalsA } from 'actions';
import history from 'utils/history';
import { auth } from 'api';

import { getConfirmAnswer } from 'selectors';

function* login({ payload: user }: { payload: { user: { username: string, password: string } } }) {
  try {
    const authSuccess = yield call(auth.login, user);

    // yield (document.cookie = `assess=${authSuccess.body.accessToken}`);
    // yield (document.cookie = `refresh=${authSuccess.body.refreshToken}`);
    yield localStorage.setItem('access', authSuccess.body.accessToken);
    yield localStorage.setItem('refresh', authSuccess.body.refreshToken);
    // window.token = authSuccess.body.accessToken;
    const { body } = yield call(auth.role);

    yield localStorage.setItem('user', JSON.stringify(body));

    yield call([history, history.push], '/operator/home');
    yield put(homeA.loginPendingSuccess(body));
  } catch (error) {
    yield put(homeA.loginPendingError(error));
  }
}

function* fetchLoginContext() {
  try {
    const data: Object = yield call(auth.loginContext);
    const info = JSON.parse(data.text);

    yield localStorage.setItem('context', JSON.stringify(info));

    yield put(homeA.loginContextPendingSuccess(info));
  } catch (error) {
    yield put(homeA.loginContextPendingError(error));
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
      yield put(homeA.logoutPendingSuccess());
    }
  } catch (error) {
    yield put(homeA.logoutPendingError(error));
  }
}

export default function* watchFetchHouses(): Object {
  yield takeLatest(homeA.loginPending.getType(), login);
  yield takeLatest(homeA.loginContextPending.getType(), fetchLoginContext);
  yield takeLatest(homeA.logoutPending.getType(), logout);

  yield takeLatest(modalsA.confirm.getType(), logout);
}
*/