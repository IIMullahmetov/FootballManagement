// @flow
import { put, select, takeLatest, call } from 'redux-saga/effects';
import { saveAs } from 'file-saver';

import { entranceA } from 'actions';
import { getActiveCounterType, getHouseId, getHouseData } from 'selectors';
import { entrance } from 'api';

import { fixedPayload } from 'modules';

function* loadExcel() {
  try {
    const data = yield select(getHouseData);
    // const fileName = yield select(state => state.housesConfig.fileName);
    yield import('xlsx').then(({ utils, write }) => {
      const jsonArr = data
        .map((item) => {
          /* prettier-ignore */
          const currentSum = fixedPayload(item.totalVal.reduce((prev, curr) =>
            prev + curr.payloads.current_value, 0));
          /* prettier-ignore */
          const monthSum = fixedPayload(item.lastPeriodVal.reduce((prev, curr) =>
            prev + curr.payloads.month_rate, 0));

          return item.totalVal.map((key, index) => ({
            'Место сбора данных': index === 0 ? `${item.roomTypeFullname} ${item.roomNumber}` : '',
            'Нахождение счетчика': key.setup_place,
            'Текущие показания': fixedPayload(key.payloads.current_value),
            Сумма: index === 0 ? currentSum : '',
            'Расход за месяц': fixedPayload(key.payloads.month_rate),
            'Сумма расхода': index === 0 ? monthSum : '',
          }));
        })
        .reduce((a, b) => (b ? a.concat(b) : a), []);

      const ws = utils.json_to_sheet(jsonArr, {
        header: [
          'Место сбора данных',
          'Нахождение счетчика',
          'Текущие показания',
          'Сумма',
          'Расход за месяц',
          'Сумма расхода',
        ],
      });

      const workbook = utils.book_new();
      const wopts = { bookType: 'xlsx', bookSST: false, type: 'array' };
      utils.book_append_sheet(workbook, ws);
      const wbout = write(workbook, wopts);
      saveAs(
        new File([wbout], { type: 'data:application/octet-stream:filename=filename.xlsx' }),
        'filename.xlsx',
      );
    });
  } catch (error) {
    console.log(error);
  }
}

function* fetchEntrances({ payload: { houseId } }) {
  try {
    let storeHouseId = null;
    if (!houseId) storeHouseId = yield select(getHouseId);
    const { body } = yield call(entrance.getEntrances, houseId || storeHouseId);

    const entrances = body.sort((a, b) => a.entranceNumber - b.entranceNumber);

    yield put(entranceA.entrancesPendingSuccess(entrances));
  } catch (error) {
    yield put(entranceA.entrancesPendingError(error));
  }
}

function* fetchRoomInHouse({ payload: { houseId, entranceId } }) {
  try {
    // const houseId = yield select(getHouseId);
    const deviceTypeAlias = yield select(getActiveCounterType);
    const roomOf = !entranceId ? 'roomInHouse' : 'roomInEntrance';
    const { body } = yield call(
      entrance.getHouseRooms,
      roomOf,
      deviceTypeAlias.alias,
      houseId,
      entranceId,
      deviceTypeAlias.alias === 'sensor',
    );

    if (deviceTypeAlias.alias === 'sensor') {
      yield put(entranceA.housesDataPendingSuccess({ rooms: body }));
    } else {
      yield put(entranceA.housesDataPendingSuccess(body));
    }
  } catch (error) {
    yield put(entranceA.housesDataPendingError(error));
  }
}

function* fetchGetHousesConfig() {
  try {
    const houseId = yield select(getHouseId);
    const { body } = yield call(entrance.getHousesConfig, houseId);

    yield put(entranceA.getHousesConfigSuccess(body));
  } catch (error) {
    yield put(entranceA.HousesConfigError(error));
  }
}

function* fetchSetHousesConfig({ payload: { day } }) {
  try {
    const houseId = yield select(getHouseId);
    const { body } = yield call(entrance.setHousesConfig, houseId, day);

    yield put(entranceA.setHousesConfigSuccess(body));
    yield put(entranceA.getHousesConfigSuccess({ month_rate_day: +day }));
  } catch (error) {
    yield put(entranceA.HousesConfigError(error));
  }
}

export default function* watchFetchEntrances(): Object {
  yield takeLatest(entranceA.entrancesPending.getType(), fetchEntrances);
  yield takeLatest(entranceA.housesDataPending.getType(), fetchRoomInHouse);

  yield takeLatest(entranceA.setHousesConfig.getType(), fetchSetHousesConfig);
  yield takeLatest(entranceA.getHousesConfig.getType(), fetchGetHousesConfig);

  yield takeLatest(entranceA.createExcel.getType(), loadExcel);
}
