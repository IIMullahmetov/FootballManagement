import agent from 'utils/agent';

export default {
  upload: (files): Object => 
	       agent()
	      .post('/file/upload')  
	      .set('Content-Type', 'multipart/form-data')
	     .attach('file', files)      	
  ,
      

  download: (): Object => agent().get('/file/download')
};