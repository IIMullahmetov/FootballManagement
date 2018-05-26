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
  				<div className="container" >    
		    <div className="jumbotron">
		      <div className="row">
		        <div className="col-md-3 col-sm-3 col-lg-2 profileImage">
		          <img src="img/profile.png" alt="stack photo" className="img"/>
		        </div>

		        <div className="col-md-3 col-xs-4 col-sm-4 col-lg-3">
		          <div className="profile">
		            <ul>
		              <li className="FIO"><b>Федор Смолов</b></li>
		              <li className="state"><img src= "img/Russia.png"/> ФК Краснодар</li>
		              <li className="state">Нападающий</li>
		            </ul>
		          </div>
		        </div>
		        <div className="col-md-5 col-xs-4 col-sm-6 col-lg-3 profile">
		          <div className="pasport">
		            <ul>
		              <li><b>Дата Рождения: </b>9 февраля 1990</li>
		              <li><b>Возраст: </b>28 лет</li>
		              <li><b>Гражданство: </b><img src= "img/Russia.png"/> Россия</li>
		              <li><b>Рост: </b> 187 см. <b>Вес: </b> 80 кг.</li>
		            </ul>
		          </div>
		        </div>
		        <div className="col-md-4 col-xs-4 col-sm-5 col-lg-3 profile">
		          <div className="statistic container">
		            <div className ="col boldText" style="width: 50%; ">Игры</div><div class="col  statisticRight" style="width: 50%;">15</div>
		            <div className ="col boldText" style="width: 50%; border-bottom: 0.3px solid; border-top:0.3px solid;">Голы </div>
		            <div className="col  statisticRight" style="width: 50%; border-bottom:0.3px solid; border-top:0.3px solid; ">4</div>
		            <div className ="col boldText" style="width: 50%;">Передачи </div>
		            <div className="col  statisticRight" style="width: 50%;">10</div>
		          </div>
		        </div>
		      </div>
		    </div>
		  </div>

		  <div className="text-center">
		    <div className="btn-group">
		      <button type="button" className="btn btn-dark btn-lg">Лента</button>
		      <button type="button" className="btn btn-success btn-lg">Статистика</button>
		      <button type="button" className="btn btn-dark btn-lg">Карьера</button>
		    </div>
		  </div>

		  <form className="form-inline" action="#">
		    <div className="col-md-6 form-group" style="margin-right: 0; margin-left: auto;  width: auto; padding-bottom: 20px; ">
		      <label className="select-inline" for="tournir" style="padding-right: 10px;">Турнир: </label>
		      <select className="form-control" name="tournir">
		        <option value="t1" selected>Все турниры</option>
		        <option value="t2">Турнир1</option>
		        <option value="t3">Турнир2</option>
		        <option value="t4">Турнир3</option>
		      </select> 

		      <label for="season" style="padding-left: 20px;padding-right: 10px;">Сезон: </label> <select class="form-control" name="season">
		        <option value="t1" selected>2017/2018</option>
		        <option value="t2">2016/2017</option>
		        <option value="t3">2015/2016</option>
		        <option value="t4">2014/2015</option>
		      </select>
		      <div style="padding-left: 30px;"><button type="submit" className="btn btn-dark btn-lg">Показать</button></div>   
		    </div>
		  </form>
		  <div className="col-md-11" style="margin: auto;">
		    <table className="table">
		      <thead className="thead-dark">
		        <tr>
		          <th scope="col">Дата</th>
		          <th scope="col">Турнир</th>
		          <th scope="col">Хозяева</th>
		          <th scope="col">Счет</th>
		          <th scope="col">Гости</th>
		          <th scope="col">В</th>
		          <th scope="col">У</th>
		          <th scope="col">МИН</th>
		          <th scope="col">Г</th>
		          <th scope="col">ПЕН</th>
		          <th scope="col">П</th>
		          <th scope="col">Г+П</th>
		          <th scope="col">ЖК</th>
		          <th scope="col">КК</th>
		        </tr>
		      </thead>
		      <tbody>
		        <tr>
		          <th>23.08.2017</th>
		            <td>Товарищеские матчи(сборные)</td>
		            <td><img src= "img/Russia.png"/> Царевна Звезда</td>
		            <td>2 : 0</td>
		            <td><img src= "img/Russia.png"/> Краснодар</td>
		            <td>1</td>
		            <td>111</td>
		            <td>90</td>
		            <td>3</td>
		            <td>0</td>
		            <td>2</td>
		            <td>2</td>
		            <td>0</td>
		            <td>0</td>
		          </tr>
		          <tr>
		            <th>23.08.2017</th>
		              <td>Товарищеские матчи(сборные)</td>
		              <td><img src= "img/Russia.png"/> Царевна Звезда</td>
		              <td>2 : 0</td>
		              <td><img src= "img/Russia.png"/> Краснодар</td>
		              <td>1</td>
		              <td>111</td>
		              <td>90</td>
		              <td>3</td>
		              <td>0</td>
		              <td>2</td>
		              <td>2</td>
		              <td>0</td>
		              <td>0</td>
		            </tr>
		            <tr>
		              <th>23.08.2017</th>
		                <td>Товарищеские матчи(сборные)</td>
		                <td><img src= "img/Russia.png"/> Царевна Звезда</td>
		                <td>2 : 0</td>
		                <td><img src= "img/Russia.png"/> Краснодар</td>
		                <td>1</td>
		                <td>111</td>
		                <td>90</td>
		                <td>3</td>
		                <td>0</td>
		                <td>2</td>
		                <td>2</td>
		                <td>0</td>
		                <td>0</td>
		              </tr>
		              <tr>
		                <th>23.08.2017</th>
		                  <td>Товарищеские матчи(сборные)</td>
		                  <td><img src= "img/Russia.png"/> Царевна Звезда</td>
		                  <td>2 : 0</td>
		                  <td><img src= "img/Russia.png"/> Краснодар</td>
		                  <td>1</td>
		                  <td>111</td>
		                  <td>90</td>
		                  <td>3</td>
		                  <td>0</td>
		                  <td>2</td>
		                  <td>2</td>
		                  <td>0</td>
		                  <td>0</td>
		                </tr>
		                <tr>
		                  <th>23.08.2017</th>
		                    <td>Товарищеские матчи(сборные)</td>
		                    <td><img src= "img/Russia.png"/> Царевна Звезда</td>
		                    <td>2 : 0</td>
		                    <td><img src= "img/Russia.png"/> Краснодар</td>
		                    <td>1</td>
		                    <td>111</td>
		                    <td>90</td>
		                    <td>3</td>
		                    <td>0</td>
		                    <td>2</td>
		                    <td>2</td>
		                    <td>0</td>
		                    <td>0</td>
		                  </tr>
		                  <tr>
		                    <th>23.08.2017</th>
		                      <td>Товарищеские матчи(сборные)</td>
		                      <td><img src= "img/Russia.png"/> Царевна Звезда</td>
		                      <td>2 : 0</td>
		                      <td><img src= "img/Russia.png"/> Краснодар</td>
		                      <td>1</td>
		                      <td>111</td>
		                      <td>90</td>
		                      <td>3</td>
		                      <td>0</td>
		                      <td>2</td>
		                      <td>2</td>
		                      <td>0</td>
		                      <td>0</td>
		                    </tr>

		                  </tbody>
		                </table>

		              </div>

		              <div style="text-align: center;">В - вышел на поле, У - ушел с поля, МИН - сыграл минут, Г - забил голов, ПЕН - забил голов с пенальти, П - сделал голевых передач, Г+П - гол + пас, ЖК - желтые карточки, КК - красные карточки</div>

		              <nav aria-label="Page navigation example">
		                <ul className="pagination justify-content-center">
		                  <li className="page-item">
		                    <a className="page-link" href="#" tabindex="-1"> Предыдущая </a>
		                  </li>
		                  <li className="page-item"><a className="page-link" href="#">1</a></li>
		                  <li className="page-item"><a className="page-link" href="#">2</a></li>
		                  <li className="page-item"><a className="page-link" href="#">3</a></li>
		                  <li className="page-item">
		                    <a className="page-link" href="#">Следущая >></a>
		                  </li>
		                </ul>
		              </nav>
					 

					    
					 
			 
  	

	</main>
	);

}

return <main></main>
  	
  	
  		
  	

  	
  }
}

export default connect(mapStateToProps, mapDispatchToProp)(Player);
