import agent from 'utils/agent';

export default {

	getMatch: (matchId): Object =>
  	 agent()
  	 	.get(`/match/get/${matchId}`),
  	 	
 	getList:  (size): Object => agent().get('/match/get_list').query({ size: size }),

  	
};