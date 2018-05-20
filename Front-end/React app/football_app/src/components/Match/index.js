import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { matchA} from 'actions';
import {withRouter} from 'react-router';

import './style.css';


const mapStateToProps = ({ match: { matchItem }, ...state }) => ({
  matchItem,
});

const mapDispatchToProp = dispatch => ({
  getMatch: matchId => dispatch(matchA.matchPending(matchId)),
});



class Match extends React.Component<{
	getMatch: Function,
	matchId: number,	
	matchItem : Object,
				

}> {



	 componentDidMount() {	 
		 
    	this.props.getMatch(this.props.matchId);  

    	console.log(this.props);

  	}
  

  

  render() { 

  	const matchObject = this.props.matchItem;



  	console.log(matchObject);

  	if(matchObject.home) {

  		const homeTeamLogoUrl = "http://footballmanagement.azurewebsites.net/file/download?guid=" + matchObject.home.logotype;
  		const guestTeamLogoUrl = "http://footballmanagement.azurewebsites.net/file/download?guid=" + matchObject.guest.logotype;

  		return (
  			<main>
  			
			  <div className="container">
			    <div className="row">
			    <div className="col-md-2 f_team_logo_block">
			      <img src={homeTeamLogoUrl} className="img-responsive f_team_logo" />
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

export default connect(mapStateToProps, mapDispatchToProp)(Match);
