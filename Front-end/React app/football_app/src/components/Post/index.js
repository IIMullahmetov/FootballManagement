import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import {postA} from 'actions';
import {withRouter} from 'react-router';

import './style.css';


const mapStateToProps = ({ post: { postItem }, ...state }) => ({
  postItem,
});

const mapDispatchToProp = dispatch => ({
  getPost: postId => dispatch(postA.postPending(postId)),
});



class Post extends React.Component<{
	getPost: Function,
	postId: number,	
	postItem : Object,
				

}> {

	


	 componentDidMount() {	 		 
    	this.props.getPost(this.props.postId);  
    	console.log(this.props);
  	}



 
  render() { 

  	const postObject = this.props.postItem;

  	console.log(postObject);

  	if(postObject.items) {
  		const imgSrc = 'http://footballmanagement.azurewebsites.net/file/download?guid=';
  		
  		return (  			
  			 <div className="col-md-12 post">
		        <h6 className="post_time" style={{color: 'gray'}}>{postObject.createDt}</h6>
		        <h2 className="post_name">{postObject.title}</h2>
		        <span className="news_intro">
		        {postObject.intro} 
		        </span>
		        <p className='news_body'>
					{ postObject.items.map((item) => {
						if(item.type === 'text')  return  <p>{item.text}</p>;
						if(item.type === 'image') return <img src={imgSrc+item.guid} className="img-responsive"/>;				
								                
		                  
		                  })
		              }

		          
		        </p>
		         <hr style={{borderWidth: '3px'}}/>
		     </div>
	);

}

return <main></main>
  	
  	
  		
  	

  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(Post);