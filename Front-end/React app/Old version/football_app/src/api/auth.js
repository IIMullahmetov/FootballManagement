import agent from 'utils/agent';

export default {
  login: ({ user }: { user: { email: string, password: string } }): Object =>
    agent()
      .post('/auth/login')
      .send(user),

  logout: (): Object => agent().get('/rest/logout'),

  role: (): Object => agent().get('/api/role'),

  refresh: (): Object =>
    agent()
      .post('/auth/refresh_token')
      .send({ refresh_token: localStorage.getItem('refresh') }),
};