import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { home, postA} from '../actions';
import { Link } from 'react-router-dom';

import Tourney_tour from'../components/Tourney_tour';

import PostBlock from '../components/PostBlock';

import LastMatches from '../components/LastMatches';

const mapStateToProps = ( { post: { postList } }) => ({
  postList
});

const mapDispatchToProps = dispatch => ({  
  getLoginData: () => dispatch(home.loginContextPending()),
  getPostList: () => dispatch(postA.postListPending()),

});

class Main extends React.Component<{
  getHouses: Function,
  getPostList: Function,
  getTourneyTours: Function,
  getLoginData: Function,
  history: Object,
  status: number,
  postList: Array<Object>,
 
}> {
  componentDidMount() {
    this.props.getLoginData();
    this.props.getPostList();
  }

 

  render() {
    if (this.props.status) return <div className="content" />;

    if(this.props.postList[0] && this.props) {
      const mainPost = this.props.postList[0];

      console.log(mainPost);
      return (
      <main>
       <div className="container">
       <LastMatches />
       <div className="col-md-12" >
       <div style={{background: 'rgba(0, 170, 238, 0.9)'}}> 
         <div className="jumbotron" style={{background: 'url(http://footballmanagement.azurewebsites.net/file/download?guid=1b8df587-24ed-4ed5-b350-20994e724266) 100% 100% no-repeat', backgroundSize: 'cover'}}>
         
          <p style={{color: 'white', fontWeight: 'bold', marginTop: '200px'}}>{mainPost.title}</p>
          <p><Link to={`/post/${mainPost.id}`} className="btn btn-primary btn-lg" style={{backgroundColor: '#0fc272'}} role="button" >Узнать больше</Link></p>
          </div>
      </div>
      </div>
      <div className="col-md-5 col-md-offset-1">
        <PostBlock posts={this.props.postList} title='Главные новости'/>
         
         
          </div>
          <div className="col-md-5 col-md-offset-1">
        <PostBlock posts={this.props.postList} title='Последние новости'/>
         
         
          </div>
         
       </div>
      </main>
    );
    }

    return <main></main>

    
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Main);
