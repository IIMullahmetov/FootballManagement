import agent from 'utils/agent';

export default {

	getPlayer: (playerId): Object =>
  	 agent()
  	 	.get(`/player/get/${playerId}`),  

  	 	 	
 

  	
};