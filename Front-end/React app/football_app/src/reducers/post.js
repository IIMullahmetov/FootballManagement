import { createReducer } from 'redux-act';
import { postA} from 'actions';
import React from 'react';


const initialState: {
    postItem: Object, 
    postList: Array<Object>,
    addPostInputs: Array<Object>,
} = {    
    postItem: {},
    postList:[],
    addPostInputs: [],
};

export default createReducer(
  {
    [postA.postPending]: state => ({
      ...state,      
      postItem: {},
    }),
    [postA.postPendingSuccess]: (state, { postItem }) => ({ ...state, postItem }),


    [postA.postListPending]: state => ({
      ...state,      
      postList: [],
    }),
    [postA.postListPendingSuccess]: (state, { postList }) => ({ ...state, postList }),

    [postA.addTextInput]: (state) => ({
       ...state,
        addPostInputs: [...state.addPostInputs, (<input type="text" placeholder="Title" size="40" />)],     
    }),


    [postA.postListPendingSuccess]: (state, { postList }) => ({ ...state, postList }),
  },  
  initialState,
);
