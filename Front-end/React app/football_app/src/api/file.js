import agent from 'utils/agent';

export default {
  upload: (): ({files }): Object =>
    agent()
      .post('/file/upload')
      .attach(files[0].name, files[0]), 

  download: (): Object => agent().get('/file/download')
};