// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { playerA } from 'actions';
import history from 'utils/history';
import { player  } from 'api';

function* getPlayer({ payload: { playerId } }) {
  try {
    console.log("match id: " + playerId);
    const {body}: {body: Object} = yield call(player.getPlayer, playerId);

    const playerItem = body;

    console.log("match object: " + playerItem.id);

    yield put(playerA.playerPendingSuccess(playerItem)); 
  } catch(error) {
    console.log(error);
    yield put(playerA.playerPendingError(error));
  }

}

export default function* watchFetchPlayer(): Object { 
  yield takeLatest(playerA.playerPending.getType(), getPlayer);
  
}