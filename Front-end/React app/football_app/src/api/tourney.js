import agent from 'utils/agent';

export default {

	getTourney: (tourneyId): Object =>
  	 agent()
  	 	.get(`/tourney/get/${tourneyId}`),
  	 	
 	getList:  (size): Object => agent().get('/tourney/get_list').query({ size: size }),

  	
};