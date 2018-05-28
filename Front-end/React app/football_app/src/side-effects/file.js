// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { fileA } from 'actions';
import history from 'utils/history';
import { file } from 'api';

function* uploadFile({ payload: { files } }) {
  try {  	
  	console.log(files);

  	yield put(fileA.fileUploadPendingSuccess(true)); 
  	 
    const  { body }: { body: Object } = yield call(file.upload, files); 
    console.log(body);    

    
  } catch (error) {
    console.log(error);
  }
}


export default function* watchFetchFile(): Object {
  yield takeLatest(fileA.fileUploadPending.getType(), uploadFile); 
}

