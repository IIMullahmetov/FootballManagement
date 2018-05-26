// @flow
import { put, takeLatest, call, select, takeEvery} from 'redux-saga/effects';
import { fileA } from 'actions';
import history from 'utils/history';
import { file } from 'api';

function* uploadFile({ payload: { files } }) {
  try {
    const  { body }: { body: Object } = yield call(file.uploadFile, files); 
    console.log(body);    

    
  } catch (error) {
    console.log('error uploading file');
  }
}


export default function* watchFetchComment(): Object {
  yield takeLatest(fileA.uploadFIle.getType(), uploadFile); 
}

