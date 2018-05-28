import React from 'react';
import { connect } from 'react-redux';
import Dropzone from 'react-dropzone';
// import { Route } from 'react-router-dom';

import {withRouter} from 'react-router';
import { postA, fileA} from 'actions';

import './style.css';

const mapStateToProps = ({ post: { addPostInputs, error}, ...state }) => ({
  addPostInputs : addPostInputs,
  error: error,
});


const mapDispatchToProp = dispatch => ({
  addTextInput: () => dispatch(postA.addTextInput()),
  uploadFile: (files) => dispatch(fileA.fileUploadPending(files)),
});




class AddPost extends React.Component<{
	addPostInputs: Array<Object>,
	addTextInput: Function,
	uploadFile: Function,
	error: Boolean,
}> {

	addTextInput(something) {
       return  this.props.pageCode;
    }

  render() { 

  	let inputs = this.props.addPostInputs

  	console.log(inputs);
  	let creatingError = 'false';
  	let errorItem = this.props.error;

  	console.log(creatingError);

  	
		return(<main><div className="container"><div className="row">
			<div className="col-md-6 col-md-offset-3">
			<input className="form-control" type="text" placeholder="Title" size="40" />
			<br/>
			<input className="form-control" type="text" placeholder="Intro" size="40" />
			<br/>
			<button className="btn btn-primary" onClick={this.props.addTextInput}>Добавить текстовое поле </button>
			<br/>
			{inputs.map((postInput) => postInput)}
			<br/>
			<br/>
			<Dropzone 			 
			  onDrop={acceptedFiles => {
    acceptedFiles.forEach(file => {
        const reader = new FileReader();
        reader.onload = () => {
            const fileAsBinaryString = reader.result;
            this.props.uploadFile(fileAsBinaryString);
        };
        reader.onabort = () => console.log('file reading was aborted');
        reader.onerror = () => console.log('file reading has failed');

        reader.readAsBinaryString(file);
    });

}}
			  multiple={false}			  
			>
			Перетащите сюда изображение или нажмите
			</Dropzone>
			<br/>
			<br/>



			<button className="btn btn-lg btn-success" onClick={() => errorItem=true}>Создать новость</button>

			{errorItem && <p>Ошибка создани новости</p>}
			
			
			</div>
			</div>
			</div>
			</main>
			);
		
	 	
  	

	
		
  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(AddPost);
  	