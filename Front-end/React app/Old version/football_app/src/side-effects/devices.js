// @flow
import { put, takeLatest, call, select } from 'redux-saga/effects';
import { devicesA, statisticA } from 'actions';
import moment from 'moment';

import { devices } from 'api';
import { getActiveCounterType } from 'selectors';

function* fetchRoomsList({ payload: { entranceId } }) {
  try {
    const { body } = yield call(devices.getRoomsList, entranceId);

    const rooms = body.sort((a, b) => a.number - b.number);

    yield put(devicesA.roomsListPendingSuccess(rooms));
  } catch (error) {
    yield put(devicesA.roomsListPendingError(error));
  }
}

function* fetchDeviceOwner({ payload: { roomId } }) {
  try {
    const { body }: { body: Object } = yield call(devices.getRoomOwner, roomId);

    yield put(devicesA.ownerPendingSuccess(body));
  } catch (error) {
    yield put(devicesA.ownerPendingError(error));
  }
}

function* fetchRoomInfo({ payload: { roomId } }) {
  try {
    const { body }: { body: Object } = yield call(devices.getRoomInfo, roomId);

    yield put(devicesA.roomInfoPendingSuccess(body));
  } catch (error) {
    yield put(devicesA.roomInfoPendingError(error));
  }
}

function* fetchRoomDevices({ payload: { roomId } }) {
  try {
    const active = yield select(getActiveCounterType);
    const { body }: { body: Object } = yield call(devices.getRoomDevices, roomId, active.id === 8);

    yield put(devicesA.roomDevicesPendingSuccess(body));
  } catch (error) {
    yield put(devicesA.roomDevicesPendingError(error));
  }
}

function* createDatesArray({ payload: { device } }) {
  try {
    let currentDate = moment().utcOffset(3);
    const array = [];

    for (let i = 0; i < 60; i += 1) {
      array.push(currentDate);
      currentDate = moment(currentDate).subtract(device.pollingInterval, 'minutes');
    }

    yield put(devicesA.createDatesArraySuccess(array.reverse()));

    yield put(statisticA.deviceStatisticPending(device.id, array[0].format()));
  } catch (error) {
    yield put(devicesA.createDatesArrayError(error));
  }
}

export default function* watchFetchDevices(): Object {
  // yield takeLatest(devicesA.ownerPending.getType(), fetchDeviceOwner);
  // yield takeLatest(devicesA.roomInfoPending.getType(), fetchRoomInfo);
  yield takeLatest(devicesA.roomsListPending.getType(), fetchRoomsList);
  yield takeLatest(devicesA.roomDevicesPending.getType(), fetchRoomDevices);
  yield takeLatest(devicesA.createDatesArray.getType(), createDatesArray);

  yield takeLatest(devicesA.roomDevicesPending.getType(), fetchDeviceOwner);
  yield takeLatest(devicesA.roomDevicesPending.getType(), fetchRoomInfo);
}
