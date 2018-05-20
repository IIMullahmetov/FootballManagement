import React from 'react';
import { connect } from 'react-redux';
import Modal from 'react-modal';
import { InputText, Button } from 'components/elements';

import { modalsA, home } from 'actions';

import './style.css';

Modal.setAppElement ('#root');

const mapStateToProps = ({
  login: {
    email, password, open, failure,
  }, errors,
}) => ({
  user: { email, password },
  opened: open,
  failure,
  errors,
});
const mapDispatchToProps = dispatch => ({
  set: ({ target: { value, dataset } }) => dispatch(modalsA.set({ value, dataset }, 'login')),
  close: () => dispatch(modalsA.close('login')),
  loginRequest: user => dispatch(home.loginPending(user)),
});
class Login extends React.PureComponent<{
  opened: boolean,
  user: {
    email: string,
    password: string,
  },
  errors: {
    type: string,
    status: number,
    text: string,
  },
  failure: boolean,
  open: boolean,
  set: Function,
  loginRequest: Function,
  close: Function,
}> {
  render() {
    const {
      user, opened, open, set, loginRequest, errors, failure,
    } = this.props;
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
            height: '290px',
          },
        }}
      >
        <div className="login-modal">
        <span className="close_btn" onClick={this.props.close}>X</span>
          {failure && (
            <div className="login-error">
              <p style={{ color: '#ed1c24' }}>Ошибка авторизации</p>
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
              placeholder="Логин"
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
            <Button
              style={{
                marginTop: '30px',
                paddingTop: '10px',
                fontSize: '14px',
              }}
              width={290}
              height={40}
              handler={() => loginRequest(user)}
              text="Войти"
            />
          </div>
        </div>
      </Modal>
    );
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Login);