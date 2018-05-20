import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

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
const Header = ({
  isLoginOpened
  /*removeChoosen,
  loginData,
  chosens,
  // logout,
  confirm,*/
}: // push,
{
  isLoginOpened: boolean
  /*loginData: { communal: string, name: string },
  chosens: Array<Object>,
  // push: Function,
  // logout: Function,
  confirm: Function,
  removeChoosen: Function,*/
}) => (
  <header>
    <div className="container">
      <div className="row">
        <div className="col-md-2 logo_div">
          <h1>LOGO</h1>
        </div>
        <div className="col-md-1 col-md-offset-7 login_div">
         <LoginButton />   
        </div>
        <div className="col-md-2 reg_div">
          <RegistrationButton />
        </div>
      </div>
    </div>
    <Login />
    <Registration />
  </header>
 
);

export default /*connect(mapStateToProps, mapDispatchToProps)*/(Header);
