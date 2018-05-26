import agent from 'utils/agent';

export default {
 	getList:  (isMain): Object => agent().get('/post/get_list').query(isMain),

  	getPost: (postId): Object =>
  	 agent()
  	 	.get(`/post/get/${postId}`),  	 	

  	//create_post: (): 
};