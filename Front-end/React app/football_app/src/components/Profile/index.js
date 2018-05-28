import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { profileA} from 'actions';
import {withRouter} from 'react-router';
import ProfileEdit from 'components/modals/ProfileEdit';
import ProfileEditButton from 'components/buttons/ProfileEditButton';

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

             
              <h2>{profileObject.firstName} {profileObject.lastName}</h2> 
            </div>

           <div className="col-md-3 head-col-3">
               <p> Записи в блоге <span className="quantity">1</span> </p>
                <p> Комментарии <span className="quantity">1</span> </p>
           </div>
        </div>


      <div className="col-md-10 profile-info col-md-offset-1 panel panel-default">
         

          <p> <span>Дата регистрации:</span>  {profileObject.registrationDt}</p>
          <p> <span>Пол:</span>   {profileObject.gender===0 ? 'Мужской': 'Женский'}</p>
          <p> <span>Email:</span> {profileObject.email}</p>
          <p> <span>Страна:</span>  Россия</p>
          <p> <span>Город:</span> Альметьевск </p>

          <div  className="col-md-3 col-md-offset-8"><ProfileEditButton /></div>
         
      </div>
  </div>
  <ProfileEdit />
</main>
	);




  	
  	
  		
  	

  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(Profile);