import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';



import AddPost from '../components/AddPost';

//const mapStateToProps = ({ errors: { status } }) => ({ status });



class AddPostPage extends React.Component<{
  
}> {
  

  render() {
    
    return (<AddPost />);
  }
}

export default connect(null, null)(AddPostPage);
