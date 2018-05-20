import agent from 'utils/agent';

export default {
  upload: (): ({{ user }: { user: { email: string, password: string } }): Object =>
    agent()
      .post('/file/upload')
      .send(user), 

  download: (): Object => agent().get('/file/download')
};