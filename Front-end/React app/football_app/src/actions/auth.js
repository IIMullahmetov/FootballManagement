import { createAction } from 'redux-act';

export default {
  loginPending: createAction('login', (user: { email: string, password: string }) => ({ user })),
  loginPendingSuccess: createAction('login success', (user: Object) => ({ user })),
  loginPendingError: createAction('login error', (error: Object) => ({ error })),

  loginContextPending: createAction('login context pending'),
  loginContextPendingSuccess: createAction('login context pending success', (data: Object) => ({
    data,
  })),
  loginContextPendingError: createAction('login context pending error', (error: Object) => ({
    error,
  })),

  logoutPending: createAction('logout pending'),
  logoutPendingSuccess: createAction('logout pending success'),
  logoutPendingError: createAction('logout pending error', (error: Object) => ({ error })),

  registrationPending: createAction('registration pending', (user: { email: string, firstName: string, lastName: string, password: string, confirmPassword: string, birthday: Date, gender: string}) => ({ user })),
  registrationPendingSuccess: createAction('registration pending success', (user: Object) => ({ user })),
  registrationPendingError: createAction('registration pending error', (error: Object) => ({ error })),

  



  unauthorized: createAction('unauthorized', error => ({ error })),
};
