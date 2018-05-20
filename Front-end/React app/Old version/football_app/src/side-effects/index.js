import { all, fork } from 'redux-saga/effects';

/*
import watchFindHouseMarker from './map';
import watchFetchEntrance from './entrance';
import watchFetchDevices from './devices';
import watchFetchStatistic from './statistic';
import watchFindTypedAddress from './address';
import hotAuth from './hot-auth';*/

import watchFetchAuth from './auth';
import watchFetchRegistration from './registration';
import watchFetchMatches from './match';

export default function* sagas() {
  yield all([
  	fork(watchFetchAuth),
  	fork(watchFetchRegistration),
  	fork(watchFetchMatches),
   /* fork(hotAuth),
    fork(watchFetchAuth),
    fork(watchFetchDevices),
    fork(watchFetchEntrance),
    fork(watchFetchStatistic),
    fork(watchFindHouseMarker),
    fork(watchFindTypedAddress),*/
  ]);
}
