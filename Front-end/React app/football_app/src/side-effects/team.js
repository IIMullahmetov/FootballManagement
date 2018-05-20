import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { teamA } from 'actions';
import history from 'utils/history';
import { team  } from 'api';

function* getTeam({ payload: { teamId } }) {
  try {
    console.log("team id: " + teamId);
    const {body}: {body: Object} = yield call(team.getTeam, teamId);

    const teamItem = body;

    console.log("team object: " + teamItem.id);

    yield put(teamA.teamPendingSuccess(teamItem)); 
  } catch(error) {
    console.log(error);
    yield put(teamA.teamPendingError(error));
  }

}

export default function* watchFetchTeam(): Object { 
  yield takeLatest(teamA.teamPending.getType(), getTeam);
  
}