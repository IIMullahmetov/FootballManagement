import agent from 'utils/agent';

export default {
 	get_list:  (): Object => agent().get('/post/get_list'),

  	get_post: (): Object => agent().get('/post/get'),

  	//create_post: (): 
};