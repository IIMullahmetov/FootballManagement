// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { matchA } from 'actions';
import history from 'utils/history';
import { match } from 'api';

function* getLastMatchesList() {
  try {
    const  { body }: { body: Object } = yield call(match.getList);  

    const lastMatchesList = body.items;

    yield put(matchA.matchListPendingSuccess(lastMatchesList));
  } catch (error) {
    yield put(matchA.matchListPendingError(error));
  }
}

function* getMatch({ payload: { matchId } }) {
  try {
    console.log("match id: " + matchId);
    const {body}: {body: Object} = yield call(match.getMatch, matchId);

    const matchItem = body;

    console.log("match object: " + matchItem.id);

    yield put(matchA.matchPendingSuccess(matchItem)); 
  } catch(error) {
    console.log(error);
    yield put(matchA.matchPendingError(error));
  }

}

export default function* watchFetchMatches(): Object {
  yield takeLatest(matchA.matchListPending.getType(), getLastMatchesList);
  yield takeLatest(matchA.matchPending.getType(), getMatch);
  
}




