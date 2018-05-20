// @flow
import { take, select, call, put } from 'redux-saga/effects';
import { mapA, entranceA, homeA, statisticA, devicesA } from 'actions';
import { auth } from 'api';

export default function* hotAuth() {
  try {
    while (true) {
      yield take([
        mapA.housesError.getType(),
        entranceA.entrancesPendingError.getType(),
        statisticA.deviceStatisticPendingError.getType(),
        devicesA.roomDevicesPendingError.getType(),
        devicesA.ownerPendingError.getType(),
      ]);
      const status = yield select(state => state.errors.status);

      if (status === 401) {
        const refresh = yield call(auth.refresh);
        yield localStorage.setItem('access', refresh.body.accessToken);
        yield localStorage.setItem('refresh', refresh.body.refreshToken);

        const { body } = yield call(auth.role);

        yield localStorage.setItem('user', JSON.stringify(body));

        yield put(homeA.loginPendingSuccess(body));
      }
    }
  } catch (error) {
    console.log('hot', error);
    yield put(homeA.unauthorized(error));
  }
}
