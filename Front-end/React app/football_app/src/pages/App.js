import React from 'react';
import history from 'utils/history';
import { Provider } from 'react-redux';
import { Router, Route } from 'react-router-dom';

import Limits from 'components/modals/Limits';
import Login from 'components/modals/Login';
import Sms from 'components/modals/Sms';
import Error from 'components/modals/Error';
import CreateCounter from 'components/modals/CreateCounter/';
import CounterAbout from 'components/modals/CounterAbout/';
import Alert from 'components/modals/Alert';
import Confirm from 'components/modals/Confirm';

import Header from 'components/Header';
import Footer from 'components/Footer';

// import Permission from 'components/Permission';

import Home from './home';
import Entrance from './entrance';
import Devices from './devices';
import Statistic from './statistic';
import Auth from './auth';

import './app.css';

// const User = Permission(['user', 'operator', 'admin']);
// const Opertor = Permission(['oprator', 'admin']);
// const Admin = Permission(['admin']);

const App = ({ store }: { store: Object }) => (
  <div>
    <Provider store={store}>
      <Router basename="/abdev" history={history}>
        <div className="container">
          {/* <Route path="/" exact render={() => <Redirect to="/login" />} /> */}
          <Route
            path="/"
            render={() => (
              <div>
                <CounterAbout />
                <CreateCounter />
                <Sms />
                <Error />
                <Login />
                <Confirm />
                <Limits />
                <Alert />
              </div>
            )}
          />
          <Route path="/operator" component={Header} />
          <Route path="/login" component={Auth} />
          <Route path="/operator/home" component={Home} />
          <Route path="/operator/entrance" component={Entrance} />
          <Route path="/operator/devices" component={Devices} />
          <Route path="/operator/statistic" component={Statistic} />
          <Route path="/" component={Footer} />
        </div>
      </Router>
    </Provider>
  </div>
);

export default App;
