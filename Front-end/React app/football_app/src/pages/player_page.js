import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';



import Player from '../components/Player';

//const mapStateToProps = ({ errors: { status } }) => ({ status });

class PlayerPage extends React.Component<{
  
}> { 

  render() {    
    return (
      <Player 
        playerId={this.props.match.params.playerId}
      />
    );
  }
}

export default connect(null, null)(PlayerPage);
