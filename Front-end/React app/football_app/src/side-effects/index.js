import { all, fork } from 'redux-saga/effects';


import watchFetchAuth from './auth';
import watchFetchRegistration from './registration';
import watchFetchMatches from './match';
import watchFetchPlayer from './player';
import watchFetchTeam from './team';
import watchFetchProfile from './profile';
import watchFetchPost from './post';

export default function* sagas() {
  yield all([
  	fork(watchFetchAuth),
  	fork(watchFetchRegistration),
  	fork(watchFetchMatches),
    fork(watchFetchPlayer),
    fork(watchFetchTeam),
    fork(watchFetchProfile),
    fork(watchFetchPost),
 
  ]);
}
