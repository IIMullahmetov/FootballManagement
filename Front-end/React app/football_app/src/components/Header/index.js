import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import {home} from 'actions';

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';
import Login from 'components/modals/Login';
import LoginButton from 'components/buttons/LoginButton';
import Registration from 'components/modals/Registration';
import RegistrationButton from 'components/buttons/RegistrationButton';


/*const mapStateToProps = ( {home: { loginData } }) => ({
  chosens,
  loginData,
  push,
});
const mapDispatchToProps = dispatch => ({
  removeChoosen: address => dispatch(sidebarA.removeChoosen(address)),
  logout: push => dispatch(homeA.logoutPending(push)),
  confirm: () =>
    dispatch(modalsA.open('confirm', { text: 'Вы уверены что хотите выйти?', answer: { type: 'logout' } })),
});
*/

const mapStateToProps = ({
  home: {loginData},
  home: {isLogin},
}) => ({
 loginData,
 isLogin,
});


const mapDispatchToProps = dispatch => ({  
  logout:() => dispatch(home.logoutPending()), 
  login:  () => dispatch(home.loginPending())
});


class Header extends React.Component<{
  loginData: string, 
  isLogin: boolean,
  logout: Function,
  login: Function,
}> {


  render() {
    

    const loginStatus = this.props.isLogin;
    return(
      <header id="header">
        <div className="container">
          <div className="row">
            <div className="col-md-2 logo_div">
              <h1>LOGO</h1>
            </div> 
            {!loginStatus &&
              <div>
               <div className="col-md-1 col-md-offset-7 login_div">
                <LoginButton />   
              </div>
            <div className="col-md-2 reg_div">
              <RegistrationButton />
            </div>
            </div>
            
          }

          {loginStatus &&
              
               <div className="col-md-1 col-md-offset-7 login_div">
               <button tabIndex={0} onKeyPress={this.props.logout} className="exit" onClick={this.props.logout} >Выйти</button>

               <Link to="/profile">Профиль</Link>
             
              </div>
             
                      
                       
          }


              
            
           
          </div>
        </div>
        <Login />
        <Registration />
      </header>
  );
    
  }
}

  
 


export default connect(mapStateToProps,mapDispatchToProps)(Header);
