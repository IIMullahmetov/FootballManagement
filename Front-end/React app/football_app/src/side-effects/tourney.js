// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { tourneyA } from 'actions';
import history from 'utils/history';
import { tourney } from 'api';

function* getTourneyList() {
  try {
    const { body }: { body: Object } = yield call(tourney.getList);  
    
    const tourneyList = body.items;  



    const tourneyConfig: { tourneys: Array<{ value: number, text: string }> } ={tourneys: []};

     tourneyList.forEach((tourney) => {
      tourneyConfig.tourneys.push({value: tourney.id, text: tourney.name});
    })

    console.log(tourneyConfig);

    yield put(tourneyA.tourneyListPendingSuccess(tourneyConfig));
  } catch (error) {
    yield put(tourneyA.tourneyListPendingError(error));
  }
}

function* getTourney({ payload: { tourneyId } }) {
  try {
    console.log("tourney id: " + tourneyId);
    const {body}: {body: Object} = yield call(tourney.getTourney, tourneyId);

    const tourneyItem = body;

    console.log("v object: " + tourneyItem.id);

    yield put(tourneyA.tourneyPendingSuccess(tourneyItem)); 
  } catch(error) {
    console.log(error);
    yield put(tourneyA.tourneyPendingError(error));
  }

}

export default function* watchFetchTourney(): Object {
  yield takeLatest(tourneyA.tourneyListPending.getType(), getTourneyList);
  yield takeLatest(tourneyA.tourneyPending.getType(), getTourney);
  
}



