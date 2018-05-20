// @flow
import React from 'react';
import { connect } from 'react-redux';

import { modalsA } from 'actions';

import './style.css';

const mapDispatchToProps = dispatch => ({
  open: () => dispatch(modalsA.open('registration')),
});

const RegistrationButton = ({ open }: { open: Function }) => (
	<button
	 	className="btn btn-block"
	 	onClick={open}
    	onKeyPress={open}>
			<h5>Зарегистрироваться</h5>
    </button>
 
);

export default connect(null, mapDispatchToProps)(RegistrationButton);
