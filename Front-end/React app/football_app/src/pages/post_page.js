import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';



import Post from '../components/Post';

//const mapStateToProps = ({ errors: { status } }) => ({ status });

class PostPage extends React.Component<{
  
}> { 

  render() {    
    return (
    	<main>
    	<div className="container">
    		<div className="row">
    			<div className="col-md-12">
    				<Post 
        				postId={this.props.match.params.postId}
      				/>
    			</div>
    		</div>
    	</div>
      		
      </main>
    );
  }
}

export default connect(null, null)(PostPage);
