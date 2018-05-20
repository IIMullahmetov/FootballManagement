import React from 'react';
import { connect } from 'react-redux';
import Modal from 'react-modal';
import { InputText, Button, Select, DataPicker } from 'components/elements';

import { modalsA, home } from 'actions';

import './style.css';

const mapStateToProps = ({
  registration: {
    email, firstName, lastName, password, confirmPassword, birthDay, gender, open, failure,genders,
  }, errors,
}) => ({
  user: { email, firstName, lastName, password, confirmPassword, birthDay, gender },
  opened: open,
  failure,
   genders: genders,
  errors,
 
});



const mapDispatchToProps = dispatch => ({
  set: ({ target: { value, dataset } }) => dispatch(modalsA.set({ value, dataset }, 'registration')),
  loginRequest: user => dispatch(home.registrationPending(user)),
  close: () => dispatch(modalsA.close('registration')),
});
class Login extends React.PureComponent<{
  opened: boolean,
  user: {
    email: string,
    firstName: string,
    lastname: string,   
    password: string,
    gender: string,
    birthDay: Date,
  },
  genders: Array<{value: string, text: string}>,
  errors: {
    type: string,
    status: number,
    text: string,
  },
  failure: boolean,
  open: boolean,
  set: Function,
  close: Function,
  loginRequest: Function,
}> {
  render() {

    console.log(this.props);
    const {
      user, opened, open, set, loginRequest, errors, failure, genders
    } = this.props;

    console.log(user);
    return (
      <Modal
        isOpen={open || opened}
        shouldCloseOnOverlayClick
        overlayClassName={open ? 'login-modal__overlay--clear' : 'login-modal__overlay--black'}
        style={{
          content: {
            boxShadow: '0 0px 10px rgba(0, 0, 0, 0.09)',
            borderRadius: '5px',
            right: open ? 'calc(100em - 50px)' : '40px',
            bottom: open ? 'calc(100em - 80em)' : '40px',
            position: 'absolute',
            margin: 'auto',
            width: '370px',
            height: '570px',
          },
        }}
      >
        <div className="login-modal">  
        <span className="close_btn" onClick={this.props.close}>X</span>      
          {failure && (
            <div className="login-error">
              <p style={{ color: '#ed1c24' }}>Ошибка регистрации</p>
            </div>
          )}
          <div className="login-modal__form">
            <InputText
              withoutAlert
              data-prop="email"
              onChange={set}
              width={260}
              height={40}
              onKeyPress={e => e.which === 13 && loginRequest(user)}
              placeholder="E-mail"
            />
            <InputText
              withoutAlert
              data-prop="firstName"
              onChange={set}
              width={260}
              height={40}
              onKeyPress={e => e.which === 13 && loginRequest(user)}
              placeholder="Имя"
            />
            <InputText
              withoutAlert
              data-prop="lastName"
              onChange={set}
              width={260}
              height={40}
              onKeyPress={e => e.which === 13 && loginRequest(user)}
              placeholder="Фамилия"
            />
            <InputText
              withoutAlert
              data-prop="password"
              onChange={set}
              width={260}
              height={40}
              type="password"
              onKeyPress={e => e.which === 13 && loginRequest(user)}
              placeholder="Пароль"
            />
            <InputText
              withoutAlert
              data-prop="confirmPassword"
              onChange={set}
              width={260}
              height={40}
              type="password"
              onKeyPress={e => e.which === 13 && loginRequest(user)}
              placeholder="Подтверждение пароля"
            />            
            <Select   
              withoutAlert           
              data-prop="gender"
              onChange={set}
              width={260}
              height={40}                        
               options={genders} 
            selected={user.gender}
            defaultValue={user.gender}
                   
            />
           <DataPicker
              withoutAlert
              data-prop="birthDay"
              onChange={set}
              width={260}
              height={40}            
              placeholder="Подтверждение пароля"
            />

            <Button
              style={{
                marginTop: '30px',
                paddingTop: '10px',
                fontSize: '14px',
                backgroundColor: 'red',
              }}
              width={300}
              height={40}
              handler={() => loginRequest(user)}
              text="Зарегистрироваться"
            />
          </div>
        </div>
      </Modal>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);