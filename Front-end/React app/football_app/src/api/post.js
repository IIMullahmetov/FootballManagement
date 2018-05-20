import agent from 'utils/agent';

export default {
 	getList:  (): Object => agent().get('/post/get_list'),

  	getPost: (postId): Object =>
  	 agent()
  	 	.get(`/post/get/${postId}`),  	 	

  	//create_post: (): 
};