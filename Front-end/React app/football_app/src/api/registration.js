import agent from 'utils/agent';

export default {
  register: ({ user }: { user: { email: string, firstName: string, lastName: string, password: string, confirmPassword: string, birthday: Date, gender: string} }): Object =>
    agent()
      .post('/registration/register')
      .send(user),

};