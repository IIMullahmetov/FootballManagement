/*import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
*/

import 'moment/locale/ru';
import React from 'react';
import { render } from 'react-dom';
import createSagaMiddleware from 'redux-saga';
import { createStore, applyMiddleware, compose } from 'redux';
import moment from 'moment';

import sagas from './side-effects';
import rootReducer from './reducers';
import App from './pages/app';

import 'jquery/src/jquery';
import 'bootstrap/dist/js/bootstrap.min.js';

import 'bootstrap/dist/css/bootstrap.css';
import './index.css';












moment.locale('ru');

// window.$ = $;
/* eslint-disable no-underscore-dangle */
const sagaMiddleware = createSagaMiddleware();
const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const store = createStore(
  rootReducer,
  /* preloadedState, */ composeEnhancers(applyMiddleware(sagaMiddleware)),
);
/* eslint-enable */

store.subscribe(() => {
	console.log('subscribe', store.getState());
})

sagaMiddleware.run(sagas);
render(<App store={store} />, document.getElementById('root'));
