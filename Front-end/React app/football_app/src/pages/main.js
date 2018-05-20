import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { home} from '../actions';

import Tourney_tour from'../components/Tourney_tour';

import PostBlock from '../components/PostBlock';

import LastMatches from '../components/LastMatches';

//const mapStateToProps = ({ errors: { status } }) => ({ status });

const mapDispatchToProps = dispatch => ({  
  getLoginData: () => dispatch(home.loginContextPending()),
});

class Main extends React.Component<{
  getHouses: Function,
  getPosts: Finction,
  getTourneyTours: Function,
  getLoginData: Function,
  history: Object,
  status: number,
}> {
  componentDidMount() {
    this.props.getLoginData();
  }

  componentWillUpdate() {
    this.props.getLoginData();
  }

  render() {
    if (this.props.status) return <div className="content" />;
    return (
      <main>
       <div className="container">
       <LastMatches />
        <Tourney_tour />
        <PostBlock />
         <PostBlock />
          <PostBlock />
       </div>
      </main>
    );
  }
}

export default connect(null, mapDispatchToProps)(Main);
