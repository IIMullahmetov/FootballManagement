import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { playerA} from 'actions';
import {withRouter} from 'react-router';

import './style.css';


const mapStateToProps = ({ player: { playerItem }, ...state }) => ({
  playerItem,
});

const mapDispatchToProp = dispatch => ({
  getPlayer: playerId => dispatch(playerA.playerPending(playerId)),
});



class Player extends React.Component<{
	getPlayer: Function,
	playerId: number,	
	playerItem : Object,
				

}> {



	 componentDidMount() {	 
		 
    	this.props.getPlayer(this.props.playerId);  

    	console.log(this.props);

  	}
  

  

  render() { 

  	const playerObject = this.props.playerItem;

  	console.log(playerObject);

  	if(playerObject.home) {

  		return (
  			<main>
  			
			  <div className="container">
			    <div className="row">
			    <div className="col-md-2 f_team_logo_block">
			      <img src="http://footballmanagement.azurewebsites.net/file/download?guid=d19f031c-e91c-4aa5-a8e2-037e601e67f1" className="img-responsive f_team_logo" />
			    </div>
			    <div className="col-md-2 f_team_gols_block">
			      <h1 className="gols_count">{matchObject.home.goals.length}</h1>
			      <ul className="list-unstyled">
			        <li>Унаи Бустинста 6’</li>
			      </ul>
			    </div> 
			    <div className="col-md-4 match_info_block">
			      <p className="match_time">21.02.2018, 14:00</p>      
			      <p className="defis">-</p>
			      <p  className="liga_name">Ла Лига</p>
			      <p className="stadium_name">Стадион: Бутарке</p>
			    </div>    
			    <div className="col-md-2 s_team_gols_block">
			      <h1 className="gols_count">{matchObject.guest.goals.length}</h1>
			      <ul className="list-unstyled">
			        <li>Лукас 11’</li>
			      </ul>
			    </div>    
			     <div className="col-md-2 s_team_logo_block">
			      <img src="img/s_team_logo.png" className="img-responsive f_team_logo" />
			    </div>
			   </div>
			    
			   <div className="col-md-12 match_stat_block"> 
			    <div className="row">
			      <div className="col-md-4 col-md-offset-4"> 
			        <ul className="nav nav-pills match_tabs" id="tabs">
			          <li ><a data-toggle="tab" href="#news_panel">Новости</a></li>
			          <li className="active"><a data-toggle="tab" href="#stat_panel">Статистика</a></li>
			          <li><a data-toggle="tab" href="#teams_panel">Составы</a></li>      
			      </ul> 
			    </div>
			    </div>

			      
			 
			<div className="tab-content">
			  <div id="news_panel" className="tab-pane fade ">
			    <h3>Тут должны быть новости</h3>  
			  </div>
			  <div id="stat_panel" className="tab-pane fade in active">
			     <table className="table table-striped group_table" id="stat_table">
			      <thead>
			        <tr>
			          <th>{matchObject.home.name}</th>
			          <th>Статистика матча</th>
			          <th>{matchObject.guest.name}</th>         
			        </tr>
			      </thead>           
			            <tr>
			              <td>7</td>
			              <td>Удары по воротам</td>
			              <td>13</td>              
			            </tr>
			             <tr>
			              <td>3</td>
			              <td>Удары в створ</td>
			              <td>7</td>              
			            </tr>
			             <tr>
			              <td>{matchObject.home.possession}%</td>
			              <td>Владение мячом</td>
			              <td>{matchObject.guest.possession}%</td>              
			            </tr>
			             <tr>
			              <td>7</td>
			              <td>Фолы</td>
			              <td>13</td>              
			            </tr>
			             <tr>
			              <td>3</td>
			              <td>Желтые карточки</td>
			              <td>1</td>              
			            </tr>
			             <tr>
			              <td>0</td>
			              <td>Красные карточки</td>
			              <td>0</td>              
			            </tr>
			             <tr>
			              <td>6</td>
			              <td>Угловые</td>
			              <td>5</td>              
			            </tr>
			             <tr>
			              <td>7</td>
			              <td>Оффсайды</td>
			              <td>13</td>              
			            </tr>
			           
			          </table>

			  </div>
			  <div id="teams_panel" className="tab-pane fade">
			    <table className="table table-striped group_table" id="teams_table">
			      <thead>
			        <tr>
			          <th>Леганес</th>        
			          <th>Реал Мадрид</th>         
			        </tr>
			      </thead>   
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <thead>
			        <tr>
			          <th>Запасные</th>        
			          <th>Запасные</th>         
			        </tr>
			      </thead> 
			       <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>
			      <tr>
			        <td>31 Wayne Hennessey</td>
			        <td>31 Wayne Hennessey</td>
			      </tr>  
			    </table>
			  </div>
			  </div>
			</div>

			 

			  
			  	</div>
  	}

	</main>
	);

}

return <main></main>
  	
  	
  		
  	

  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(Player);
