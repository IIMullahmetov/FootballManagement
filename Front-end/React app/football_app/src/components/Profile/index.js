import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { profileA} from 'actions';
import {withRouter} from 'react-router';

import './style.css';


const mapStateToProps = ({ profile: { profileItem }, ...state }) => ({
  profileItem,
});

const mapDispatchToProp = dispatch => ({
  getProfile: () => dispatch(profileA.profilePending()),
});



class Profile extends React.Component<{
	getProfile: Function,		
	profileItem : Object,			

}> {

	 componentDidMount() {	 
		 
    	this.props.getProfile();  

    	console.log(this.props);

  	}

  	
  

  render() { 

  	const profileObject = this.props.profileItem;

  	console.log(profileObject);

  	

  		return (
  			<main className="panel panel-default">
  			<div className="container">

          	<div className="col-md-10 col-md-offset-1 profile-head">
              
              <div className="col-md-2">
                <img src="img/profile_photo.png" alt="" className="profile_photo"/>
              </div>

              <div className="col-md-7 head-col-2">

              <h2>BolshoyLeha</h2>
              <p>Алексей Большаков</p> 
            </div>

           <div className="col-md-3 head-col-3">
               <p> Записи в блоге <span className="quantity">12</span> </p>
                <p> Комментарии <span className="quantity">12</span> </p>
           </div>
        </div>


      <div className="col-md-10 profile-info col-md-offset-1 panel panel-default">
          <button className="btn btn-lg">Редактировать</button>

          <p> <span>Дата регистрации:</span>  27.12.2016</p>
          <p> <span>Пол:</span> Мужской</p>
          <p> <span>Email:</span>  lehabolshoy@big.com</p>
          <p> <span>Страна:</span>  Россия</p>
          <p> <span>Город:</span> Альметьевск </p>
         
      </div>
  </div>
</main>
	);




  	
  	
  		
  	

  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(Profile);