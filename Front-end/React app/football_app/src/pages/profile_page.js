import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';



import Profile from '../components/Profile';

//const mapStateToProps = ({ errors: { status } }) => ({ status });

class ProfilePage extends React.Component<{
  
}> { 

  render() {    
    return (
      <Profile />
    );
  }
}

export default connect(null, null)(ProfilePage);
