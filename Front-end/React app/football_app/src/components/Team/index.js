import React from 'react';
import { connect } from 'react-redux';
// import { Route } from 'react-router-dom';
import { teamA} from 'actions';
import {withRouter} from 'react-router';

import './style.css';


const mapStateToProps = ({ team: { matchItem }, ...state }) => ({
  matchItem,
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

  	

  		

  		return (
  			<main>
			    <div className="container">
			      <div className="row">
			        <div className="col-md-2 team_logo">
			          <img src="img/milan_logo.png"  alt=""/>          
			        </div>
			        <div className="col-md-3 team_info_head">
			          <h2>Милан</h2>
			          <p>Associazione Calcio Milan</p>
			          <br/>
			          <p>
			            <img src="img/italy_flag.png" alt=""/>
			             Милан, Италия
			          </p>
			          <p><b >Тренер:</b> Дженаро Гаттузо</p>
			        </div>
			        <div className="col-md-3 team_last_maches">
			          <div className="row">
			                <div className="col-md-12 match_block">
			                   <h4>- : -</h4>                
			                   <p>
			                      Милан - Интер<br/>
			                      25 апреля. 20:15. Серия А.
			                  </p>
			               
			              </div> 			              
			              <div className="col-md-12 match_block">
			                   <h4>- : -</h4>                
			                   <p>
			                      Милан - Интер<br/>
			                      25 апреля. 20:15. Серия А.
			                  </p>               
			              </div>          
			              <div className="col-md-12 match_block">
			                   <h4>- : -</h4>     
			                   <p>
			                      Милан - Интер<br/>
			                      25 апреля. 20:15. Серия А.
			                  </p>
			                
			              </div>                   
			        </div>
			      </div>
			      <div className="col-md-4 short_stat">
			        <p><b>Италия. Серия А. Сезон 2017/2018.</b></p>
			        <ul className="list-inline achievments_list">
			          <li>
			            <p>Место</p>
			            <h3>1</h3>
			          </li>
			          <li>
			            <p>Очки</p>
			            <h3>85</h3>
			          </li>
			          <li>
			            <p>Побед</p>
			            <h3>76</h3>
			          </li>
			        </ul>
			        <h5>Лучший бомбардир</h5>
			        <p className="best_goleador">
			          <img src="img/best_goleador.png" alt=""/>Паоло Дибала<br/>Голы: 21</p>
			      </div>

			      <div className="col-md-12 team_stat_block"> 
			    <div className="row">
			      <div className="col-md-4 col-md-offset-4"> 
			        <ul className="nav nav-pills match_tabs" id="tabs">
			          <li className="active"><a data-toggle="tab" href="#calendar_panel">Календарь</a></li>
			          <li ><a data-toggle="tab" href="#stat_panel">Статистика</a></li>
			          <li><a data-toggle="tab" href="#team_panel">Состав</a></li>      
			      </ul> 
			    </div>
			    </div>

			    <div className="tab-content">
			      <div id="calendar_panel" className="col-md-12 tab-pane fade in active ">
			        <div className="row"> 
			          <div className="col-md-12"> 
			             <form className="form-inline from-horizontal select_form">
			         <div className="form-group">
			        <button type="submit" className="btn btn-default ">Показать</button>  
			        </div>  
			        <div className="form-group">
			        <label for="sezon_select" className="control-label">Сезон:</label>    
			          <select name="sezon_select" id="sezon_select">
			             <option value=" ">2017/2018</option>      
			          </select>    
			      </div>   

			      <div class="form-group">
			          <label for="turnir_select" className="control-label">Турнир:</label>      
			            <select name="turnir_select" id="turnir_select" >   
			               <option value=" ">Все турниры</option>    
			            </select>         
			      </div>
			      
			    </form>
			    </div>
			    </div>
			     <div className="row">
			       <div className="col-md-10 col-md-offset-1">
			        <table className="table table-striped group_table"> 
			          <thead> 
			            <tr>
			                <th>Дата</th>
			                <th>Матч</th>
			                <th>Турнир</th>
			            </tr>
			            
			          </thead>
			          <tbody> 
			            <tr>  
			              <td >  
			                21.04.2018<br/>
			                20:00
			              </td>
			                  
			              <td className="match_td">  
			                Бавария<img src="img/bavaria_logo.png"/>
			                <span>-:-</span>
			                <img src="img/sevilia_logo.png"/>
			                Севилья
			              </td>
			              <td>
			                Товарищеские матчи (клубы)
			              </td>
			            </tr>
			          </tbody>
			        </table>
			      </div>
			     </div>  
			  </div>
			  <div id="stat_panel" className="tab-pane fade ">
			    <div className="row"> 
			          <div class="col-md-12"> 
			             <form class="form-inline from-horizontal select_form">
			         <div class="form-group">
			        <button type="submit" class="btn btn-default ">Показать</button>  
			        </div>  
			        <div class="form-group">
			        <label for="sezon_select" class="control-label">Сезон:</label>    
			          <select name="sezon_select" id="sezon_select">
			             <option value=" ">2017/2018</option>      
			          </select>    
			      </div>   

			      <div class="form-group">
			          <label for="turnir_select" class="control-label">Турнир:</label>      
			            <select name="turnir_select" id="turnir_select" >   
			               <option value=" ">Все турниры</option>    
			            </select>         
			      </div>
			      
			    </form>
			    </div>
			    </div>
			     <div class="row">
			       <div class="col-md-10 col-md-offset-1">
			        <table class="table table-striped group_table"> 
			          <thead> 
			            <tr>
			                <th>Номер</th>
			                <th>Игрок</th>
			                <th>М</th>
			                <th>МИН</th>
			                <th>З</th>
			                <th>ВнЗ</th>
			                <th>Г</th>
			                <th>ПЕН</th>
			                <th>П</th>
			                <th>Г+П</th>
			                <th>ЖК</th>
			                <th>КК</th>
			            </tr>
			            
			          </thead>
			          <tbody> 
			            <tr>  
			              <td>9</td>
			              <td>Гонсало Игуаин</td>
			              <td>30</td>
			              <td>2436</td>
			              <td>5</td>
			              <td>2</td>
			              <td>15</td>
			              <td>1</td>
			              <td>8</td>
			              <td>23</td>
			              <td>4</td>
			              <td>0</td>
			            </tr>
			          </tbody>
			        </table>
			      </div>
			     </div>
			    

			  </div>
			  <div id="team_panel" class="tab-pane fade">
			    <div class="row"> 
			          <div class="col-md-12"> 
			             <form class="form-inline from-horizontal select_form">
			         <div class="form-group">
			        <button type="submit" class="btn btn-default ">Показать</button>  
			        </div>  
			        <div class="form-group">
			        <label for="sezon_select" class="control-label">Сезон:</label>    
			          <select name="sezon_select" id="sezon_select">
			             <option value=" ">2017/2018</option>      
			          </select>    
			      </div>   

			      <div class="form-group">
			          <label for="turnir_select" class="control-label">Турнир:</label>      
			            <select name="turnir_select" id="turnir_select" >   
			               <option value=" ">Все турниры</option>    
			            </select>         
			      </div>
			      
			    </form>
			    </div>
			    </div>
			     <div class="row">
			       <div class="col-md-10 col-md-offset-1">
			        <table class="table table-striped group_table"> 
			          <thead> 
			            <tr>
			                <th>Номер</th>
			                <th>Игрок</th>              
			                <th>Возраст</th>
			                <th>Рост</th>
			                <th>Вес</th>
			                <th>Амплуа</th>
			            </tr>
			            
			          </thead>
			          <tbody> 
			            <tr>  
			              <td>9</td>
			              <td>Гонсало Игуаин</td>
			              <td>29</td>
			              <td>184</td>
			              <td>75</td>    
			              <td>Нап</td>    
			            </tr>
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