import React from 'react';
import history from 'utils/history';
import { Provider } from 'react-redux';
import { Router, Route, Switch } from 'react-router-dom';


import Header from 'components/Header';
import Footer from 'components/Footer';
import Main from './main';
import MatchPage from './match_page';
import ProfilePage from './profile_page';
import PostPage from './post_page';
import TeamPage from './team_page';
import PlayerPage from './player_page';
import AddPostPage from './add_post_page';

// import Permission from 'components/Permission';



import './App.css';

// const User = Permission(['user', 'operator', 'admin']);
// const Opertor = Permission(['oprator', 'admin']);
// const Admin = Permission(['admin']);

const App = ({ store }: { store: Object }) => (
  <div id="app">
    <Provider store={store}>
      <Router basename="/" history={history}>
        <div>      
          <Route path="/" component={Header} />
          <Switch>
            <Route exact path="/" component={Main} />
            <Route path="/match/:matchId" component={MatchPage} />
            <Route path="/profile" component={ProfilePage} />
            <Route exact path="/post/:postId" component={PostPage} />
            <Route path="/team/:teamId" component={TeamPage} />
            <Route path="/player/:playerId" component={PlayerPage} />
            <Route exact path="/create_post" component={AddPostPage} />          
          </Switch>
          <Route path="/" component={Footer} />       
        </div>         
      </Router>    
    </Provider>
  </div>
);

export default App;