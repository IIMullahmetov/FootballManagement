import agent from 'utils/agent';

export default {
 	getList:  (postId): Object => agent().get('/post/${postId}/comment/get_list'),

  	add_comment: (): Object => agent().post('/post/{id}/comment/add'),

  	del_comment: (): Object => agent().post('/post/{id}/comment/delete')

  	
};