import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import { commentA } from 'actions';

import { InputText, Button } from 'components/elements';

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';


const mapDispatchToProps = dispatch => ({
  getCommentList: (postId) => dispatch(commentA.commentListPending(postId)),
  addComment: (postId, comment) => dispatch(commentA.commentAddPending(postId, comment)),
   set: ({ target: { value, dataset } }) => dispatch(commentA.set({ value, dataset })),
});

const mapStateToProps = ({ comment: { commentList, comment } }) => ({
  commentList,
  commentText: comment,
});


class CommentBlock extends React.Component<{   
  commentList: Array<Object>,  
  getCommentList: Function,
  addComment: Function,
  postId: number, 
  set: Function, 
  commentText: string,   

}> {
    constructor(props) {
        super(props);
        this.props.getCommentList(this.props.postId);
      }

    shouldComponentUpdate(nextProps, nextState) {
      if(this.props.postId != nextProps.postId) {
        return true;
      }

      return false;
    }

    componentWillUpdate(nextProps) {
      nextProps.getCommentList(nextProps.postId);
    }


 



  render() {
    const postId = this.props.postId;
    const commentText = this.props.commentText;
    console.log(postId);
    console.log(commentText);

    console.log(this.props.commentList);


    return(
        <div className="col-md-12 comments_block">
          <h4 className="comments_h4">Комментарии</h4>
          <h5>{this.props.commentList.length} комментария</h5>
          <br/>
          <br/>
          <InputText
              withoutAlert
              data-prop="comment"
              onChange={this.props.set}
              width={500}
              height={50}
              
              placeholder="Что думаете о происходящем"
            />
          
          <Button style={{
                marginTop: '30px',
                marginLeft: '30px',
                padding: '20px',
                paddingLeft: '30px',
                paddingRight: '30px',
                fontSize: '14px',
                backgroundColor: '#0fc272',
              }}
              width={500}
              height={200} 
              handler={() => this.props.addComment(this.props.commentText, postId) } className="btn btn-lg send_cmnt_btn"
              text="Отправить"
              />           
            
          <ul className="comments_list list-unstyled">
           {this.props.commentList.map((commentItem) => {
            return(
              <li>
              <img src="img/comment_ava.png" alt="" className="img-responsive comment_ava"/>
              <h4>{commentItem.firstName}</h4>            
              <p class="comment_time">
                22.03.2017 15:51
              </p>  
              <p>{commentItem.comment}</p>
            </li>);
            })}
           
          </ul>
        </div>
        
      );
  }
}


  


export default connect(mapStateToProps, mapDispatchToProps)(CommentBlock);
