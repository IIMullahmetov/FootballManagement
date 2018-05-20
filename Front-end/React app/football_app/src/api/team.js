import agent from 'utils/agent';

export default {

	getTeam: (teamId): Object =>
  	 agent()
  	 	.get(`/team/get/${teamId}`),  	 	
 

  	
};