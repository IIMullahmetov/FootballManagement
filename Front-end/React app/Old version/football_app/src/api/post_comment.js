import agent from 'utils/agent';

export default {
 	get_list:  (): Object => agent().get('/post/{id}/comment/get_list'),

  	add_comment: (): Object => agent().post('/post/{id}/comment/add'),

  	del_comment: (): Object => agent().post('/post/{id}/comment/delete')

  	
};