import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';

const mapStateToProps = ( { match: { postList } }) => ({
  postList
});


/*const mapDispatchToProps = dispatch => ({
  removeChoosen: address => dispatch(sidebarA.removeChoosen(address)),
  logout: push => dispatch(homeA.logoutPending(push)),
  confirm: () =>
    dispatch(modalsA.open('confirm', { text: 'Вы уверены что хотите выйти?', answer: { type: 'logout' } })),
});*/

const Post_block = ({
  posts,
  title  
}: 
{
 posts: Array<Object>,
 title: string
}) => (

  <ul className="unstyled post_list">
    <h3 style={{color: '#0fc272', fontWeight: 'bold'}}>{title}</h3>
    {posts.map((post) => { const imgUrl = 'http://footballmanagement.azurewebsites.net/file/download?guid=' + post.image;
    const postUrl = '/post/' + post.id;
    return (<li style={{padding: '5px'}}><p style={{color: 'black', fontSize: '14px'}}><img src={imgUrl} className='img-responsive'  style={{float: 'left', margin: '7px 7px 7px 0', width: '100px'}}/><Link to={postUrl} style={{fontSize: '18px', fontWeight: 'bold', color: 'black'}}>{post.title}</Link><br/>{post.intro} </p>
     </li>);})}        
  </ul>
);

export default connect()(Post_block);
