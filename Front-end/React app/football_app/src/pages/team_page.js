import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';



import Team from '../components/Team';

//const mapStateToProps = ({ errors: { status } }) => ({ status });



class TeamPage extends React.Component<{
  
}> {
  

  render() {
    
    return (
      <Team 
        teamId={this.props.match.params.teamId}
      />
    );
  }
}

export default connect(null, null)(TeamPage);
