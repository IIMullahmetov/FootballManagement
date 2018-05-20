import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';



/*const mapDispatchToProps = dispatch => ({
  removeChoosen: address => dispatch(sidebarA.removeChoosen(address)),
  logout: push => dispatch(homeA.logoutPending(push)),
  confirm: () =>
    dispatch(modalsA.open('confirm', { text: 'Вы уверены что хотите выйти?', answer: { type: 'logout' } })),
});*/

class CommentBlock extends React.Component<{   
  comments: Array<Object>,       

}> {

  render() {
    return(
        <div className="col-md-12 comments_block">
          <h4 className="comments_h4">Комментарии</h4>
          <h5>4 комментария</h5>
          <br/>
          <textarea name="cmnt_txt_area" className="form-control cmnt_txt_area" id="" cols="30" rows="3" placeholder="Что думаете о происходящем?"></textarea>
          <button className="btn btn-lg send_cmnt_btn">
            Отправить
          </button>
          <ul className="comments_list list-unstyled">
            <li>
              <img src="img/comment_ava.png" alt="" className="img-responsive comment_ava"/>
              <h4>Толя Хлопуня</h4>            
              <p class="comment_time">
                22.03.2017 15:51
              </p>  
              <p>Идейные соображения высшего порядка, а также постоянный количественный рост и сфера нашей активности играет важную роль в формировании дальнейших направлений развития. Товарищи!</p>
            </li>
            <li>
              <img src="img/comment_ava.png" alt="" className="img-responsive comment_ava"/>
              <h4>Толя Хлопуня</h4>            
              <p className="comment_time">
                22.03.2017 15:51
              </p>  
              <p>Идейные соображения высшего порядка, а также постоянный количественный рост и сфера нашей активности играет важную роль в формировании дальнейших направлений развития. Товарищи!</p>
            </li>
            <li>
              <img src="img/comment_ava.png" alt="" className="img-responsive comment_ava"/>
              <h4>Толя Хлопуня</h4>            
              <p className="comment_time">
                22.03.2017 15:51
              </p>  
              <p>Идейные соображения высшего порядка, а также постоянный количественный рост и сфера нашей активности играет важную роль в формировании дальнейших направлений развития. Товарищи!</p>
            </li>
            <li>
              <img src="img/comment_ava.png" alt="" className="img-responsive comment_ava"/>
              <h4>Толя Хлопуня</h4>            
              <p className="comment_time">
                22.03.2017 15:51
              </p>  
              <p>Идейные соображения высшего порядка, а также постоянный количественный рост и сфера нашей активности играет важную роль в формировании дальнейших направлений развития. Товарищи!</p>
            </li>
          </ul>
        </div>
        
      );
  }
}


  


export default connect()(CommentBlock);
