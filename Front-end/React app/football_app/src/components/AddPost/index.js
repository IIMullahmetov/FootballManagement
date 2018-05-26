import React from 'react';
import { connect } from 'react-redux';
import Dropzone from 'react-dropzone';
// import { Route } from 'react-router-dom';

import {withRouter} from 'react-router';
import { postA, fileA} from 'actions';

import './style.css';

const mapStateToProps = ({ post: { addPostInputs }, ...state }) => ({
  addPostInputs : addPostInputs,
});


const mapDispatchToProp = dispatch => ({
  addTextInput: () => dispatch(postA.addTextInput()),
  uploadFile: (files) => dispatch(fileA.fileUploadPending(files)),
});




class AddPost extends React.Component<{
	addPostInputs: Array<Object>,
	addTextInput: Function,
	uploadFile: Function,
}> {

	addTextInput(something) {
       return  this.props.pageCode;
    }

  render() { 

  	const inputs = this.props.addPostInputs

  	console.log(inputs);


  	
		return(<main><div className="container"><div className="row">
			<input type="text" placeholder="Title" size="40" /><input type="text" placeholder="Intro" size="40" /><button onClick={this.props.addTextInput}>Add text</button>
			{inputs.map((postInput) => postInput)}
			<Dropzone 
			  accept="image/**"
			  onDrop={(files) => this.props.uploadFile (files)}
			  multiple={false}
			  accept="image/*" 
			/>
			</div>
			</div>
			</main>
			);
		
	 	
  	

	
		
  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(AddPost);
  	