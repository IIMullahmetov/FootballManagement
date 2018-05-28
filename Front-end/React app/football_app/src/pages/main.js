import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { home, postA, tourneyA} from '../actions';
import { Link } from 'react-router-dom';

import TourneyBlock from'../components/TourneyBlock';

import PostBlock from '../components/PostBlock';

import LastMatches from '../components/LastMatches';

const mapStateToProps = ( { post: { postList } }) => ({
  postList
});

const mapDispatchToProps = dispatch => ({  
  getLoginData: () => dispatch(home.loginContextPending()),
  getPostList: () => dispatch(postA.postListPending()),
  getTourneyList: () => dispatch(tourneyA.tourneyListPending()),

});

class Main extends React.Component<{
  getHouses: Function,
  getPostList: Function,
  getTourneyList: Function,
  getLoginData: Function,
  history: Object,
  status: number,
  postList: Array<Object>,
 
}> {
  componentDidMount() {
    this.props.getLoginData();
    this.props.getPostList();
    this.props.getTourneyList();
  }

 

  render() {
    if (this.props.status) return <div className="content" />;

    if(this.props.postList[0] && this.props) {
      const mainPost = this.props.postList[0];
      const mainPostImg = 'http://footballmanagement.azurewebsites.net/file/download?guid=' + mainPost.image;
      console.log(mainPostImg);

      console.log(mainPost);
      return (
      <main>
       <div className="container">
       <LastMatches />
       <div className="col-md-12" >
      
         <div className="jumbotron" style={{background: 'url('+ mainPostImg + ') 100% 100% no-repeat', backgroundSize: 'cover'}}>
         
          <p style={{color: 'white', fontWeight: 'bold', marginTop: '200px'}}>{mainPost.title}</p>
          <p><Link to={`/post/${mainPost.id}`} className="btn btn-primary btn-lg" style={{backgroundColor: '#0fc272'}} role="button" >Узнать больше</Link></p>
          </div>
      </div>
      
      <div className="col-md-4 ">
        <PostBlock posts={this.props.postList} title='Главные новости'/>
         
         
          </div>
          <div className="col-md-4">
        <PostBlock posts={this.props.postList} title='Последние новости'/>
         
         
          </div>
          <TourneyBlock tourneyList={this.props.tourneyList}/>
         
       </div>
      </main>
    );
    }

    return <main></main>

    
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Main);