import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';



import Match from '../components/Match';

//const mapStateToProps = ({ errors: { status } }) => ({ status });



class MatchPage extends React.Component<{
  
}> {
  

  render() {
    
    return (
      <Match 
        matchId={this.props.match.params.matchId}
      />
    );
  }
}

export default connect(null, null)(MatchPage);
