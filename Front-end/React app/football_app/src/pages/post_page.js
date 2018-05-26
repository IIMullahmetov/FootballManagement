import React from 'react';
import { connect } from 'react-redux';
import {postA } from 'actions';
import { Link } from 'react-router-dom';
// import { Route } from 'react-router-dom';



import Post from '../components/Post';
import CommentBlock from 'components/CommentBlock';

const mapStateToProps = ( { post: { postList } }) => ({
  postList
});


const mapDispatchToProps = dispatch => ({  
  
  getPostList: () => dispatch(postA.postListPending()),

});

//const mapStateToProps = ({ errors: { status } }) => ({ status });

class PostPage extends React.Component<{
  postList: Array<Object>,
  getPostList: Function,
}> { 

  constructor(props) {
    super(props);
    this.props.getPostList();
  }

  


  render() {  

  console.log(this.props.postList);  
   
    return (
    	<main>
    	<div className="container">
    		<div className="row">
    			<div className="col-md-8">
    				<Post 
        				postId={this.props.match.params.postId}
      				/>
              <CommentBlock postId={this.props.match.params.postId}/>

    			</div>
          <div className="col-md-4  last_posts">
          <ul className="unstyled post_list">
            <h3 style={{color: '#0fc272', fontWeight: 'bold'}}>Последние новости</h3>
            {this.props.postList.map((post) => {
              const postUrl = '/post/' + post.id;
              return (<li><p style={{color: 'black'}}><span style={{fontSize: '14px', color: 'gray'}}>{post.createDt}</span> <br/><Link style={{color: 'black'}} to={postUrl}>{post.title}</Link></p></li>);
            })
            }         
          </ul>
          </div>
    		</div>
    	</div>

      		
      </main>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(PostPage);
