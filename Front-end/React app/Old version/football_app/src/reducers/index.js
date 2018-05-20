// @flow
import { combineReducers } from 'redux';

/*import statistic from './statistic';
import entrance from './entrance';
import counters from './counters';
import sidebar from './sidebar';
import devices from './devices';
import modals from './modals';
import errors from './errors';
import info from './info';

import map from './map';
*/

import home from './home';
import modals from './modals';
import match from './match';


export default combineReducers({
  home,
  match,
  ...modals,
  /*statistic,
  counters,
  entrance,
  sidebar,
  devices,
  errors,
  info,
  home,
  map,
  ...modals,*/
});
