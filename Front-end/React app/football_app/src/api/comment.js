import agent from 'utils/agent';

export default {
 	getList:  (postId): Object => agent().get(`/post/${postId}/comment/get_list`),

  	addComment: (comment, postId): Object => agent().post(`/post/${postId}/comment/add`).send(comment),

  	del_comment: (): Object => agent().post('/post/{id}/comment/delete')

  	
};