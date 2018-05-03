import React from 'react';
import history from 'utils/history';
import { Provider } from 'react-redux';
import { Router, Route } from 'react-router-dom';


import Header from 'components/Header';
import Footer from 'components/Footer';

// import Permission from 'components/Permission';



import './app.css';

// const User = Permission(['user', 'operator', 'admin']);
// const Opertor = Permission(['oprator', 'admin']);
// const Admin = Permission(['admin']);

const App = ({ store }: { store: Object }) => (
  <div>
    <Provider store={store}>
      <Router basename="/" history={history}>
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
        </div>
      </Router>
    </Provider>
  </div>
);

export default App;
