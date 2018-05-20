import agent from 'utils/agent';

export default {

	getMatch: (matchId): Object =>
  	 agent()
  	 	.get(`/match/get/${matchId}`),
  	 	
 	getList:  (): Object => agent().get('/match/get_list'),

  	
};