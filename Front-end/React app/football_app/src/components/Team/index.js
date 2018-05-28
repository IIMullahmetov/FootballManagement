import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { teamA} from 'actions';
import {withRouter} from 'react-router';

import './style.css';


const mapStateToProps = ({ team: { teamItem }, ...state }) => ({
  teamItem,
});

const mapDispatchToProp = dispatch => ({
  getTeam: teamId => dispatch(teamA.teamPending(teamId)),
});



class Team extends React.Component<{
	getTeam: Function,
	teamId: number,	
	teamItem : Object,
				

}> {



	 componentDidMount() {	 
		 
    	this.props.getTeam(this.props.teamId);  

    	console.log(this.props);

  	}
  

  

  render() { 

  	const teamObject = this.props.teamItem;



  	console.log(teamObject);

  	
  	const teamImgUrl = 'http://footballmanagement.azurewebsites.net/file/download?guid=' + this.props.teamItem.logo;
  		

  		return (
  			<main>
			    <div className="container">
			      <div className="row">
			        <div className="col-md-2 col-md-offset-3 team_logo">
			          <img className="img-responsive" style={{}} src={teamImgUrl}  alt=""/>          
			        </div>
			        <div className="col-md-3 team_info_head">
			          <h2>{this.props.teamItem.name}</h2>
			          
			          <br/>
			          
			        </div>
			        <div className="col-md-3 team_last_maches">
			          <div className="row">
			                
			        </div>
			      </div>
			      

			      <div className="col-md-12 team_stat_block"> 
			    <div className="row">
			      <div className="col-md-4 col-md-offset-4"> 
			        <ul className="nav nav-pills match_tabs" id="tabs">		     
			          <li className="active"><a data-toggle="tab" href="#team_panel">Состав</a></li>      
			      </ul> 
			    </div>
			    </div>

			    <div className="tab-content">
			      

			      
			   
			
			  <div id="team_panel" class="tab-pane fade in active">
			    <div class="row">     		  
			     
			       <div class="col-md-10 col-md-offset-1">
			        <table class="table table-striped group_table"> 
			          <thead> 
			            <tr>
			                <th>Фото</th>
			                <th>Игрок</th>              
			                
			            </tr>
			            
			          </thead>
			          <tbody> 
			          	{this.props.teamItem.players && this.props.teamItem.players.map((player) => {const playerImg = 'http://footballmanagement.azurewebsites.net/file/download?guid=' + player.image;

			          		return(
			            	<tr>  
			              		<td><img className="img-responsive" style={{width: '50px'}}src={playerImg} /></td>
			              		<td>{player.firstName} {player.lastName}</td>	             
			            	</tr>
			            );
			          	})}

			           
			          </tbody>
			        </table>
			      </div>
			     </div>
			    
			  </div>
			  </div>
			</div>
			</div>



			    </div>
  </main>
	);




  	
  	
  		
  	

  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(Team);