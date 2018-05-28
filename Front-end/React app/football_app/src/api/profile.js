import agent from 'utils/agent';

export default {

	getProfile: (p): Object =>
  	 agent()
  	 	.get(`/get`),  

  	 editProfile: ({ user }: { user: { firstName: string, lastName: string, gender: string} }): Object =>
    agent()
      .post('/edit')
      .send(user)	 	
 

  	
};