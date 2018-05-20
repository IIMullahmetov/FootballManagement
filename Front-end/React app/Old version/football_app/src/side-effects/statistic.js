// @flow
import { put, takeLatest, select, call } from 'redux-saga/effects';
import moment from 'moment';

// import agent from '../agent';
import { statisticA } from 'actions';
import { statistic } from 'api';
import { getStatisticDates } from 'selectors';

// import mocks from './mocks';

function* fetchStatistic({ payload: { deviceId, date } }) {
  try {
    let dates: Object = {};
    if (date) {
      dates = {
        from: moment(date).format(),
      };
    } else {
      dates = yield select(getStatisticDates);
    }

    const { body }: { body: Object } = yield call(statistic.getPayloads, dates, deviceId);

    yield put(statisticA.deviceStatisticPendingSuccess(body));
  } catch (error) {
    yield put(statisticA.deviceStatisticPendingError(error));
  }
}

export default function* watchFetchStatistic(): Object {
  yield takeLatest(statisticA.deviceStatisticPending.getType(), fetchStatistic);
}
