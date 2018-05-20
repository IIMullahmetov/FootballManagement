import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';

/*const mapStateToProps = ( {home: { loginData } }) => ({
  chosens,
  loginData,
  push,
});
const mapDispatchToProps = dispatch => ({
  removeChoosen: address => dispatch(sidebarA.removeChoosen(address)),
  logout: push => dispatch(homeA.logoutPending(push)),
  confirm: () =>
    dispatch(modalsA.open('confirm', { text: 'Вы уверены что хотите выйти?', answer: { type: 'logout' } })),
});
*/
const Tourney_tour = ({
  /*removeChoosen,
  loginData,
  chosens,
  // logout,
  confirm,*/
}: // push,
{
  /*loginData: { communal: string, name: string },
  chosens: Array<Object>,
  // push: Function,
  // logout: Function,
  confirm: Function,
  removeChoosen: Function,*/
}) => (
  <div className="col-md-12 league_tour_div">
          <div className="row">
            <div className="col-md-12 league_tour_header_div">
              <h5>Российская премьер-лига</h5>
              <a href="#">Таблица</a>
              <a href="#">Календарь</a>
              <a href="#">Статистика</a>
            </div>

            <div className="col-md-3 match_div">
              <div className="row">
                <div className="col-md-4">
                   <h4>- : -</h4>
                </div>
                <div className="col-md-8">
                   <p>
                      Ска-Хабаровск-Урал<br/>
                      11:00
                  </p>
                </div>
              </div>          
            </div> 

            <div className="col-md-3 match_div">
              <div className="row">
                <div className="col-md-4">
                   <h4>- : -</h4>
                </div>
                <div className="col-md-8">
                   <p>
                      Ска-Хабаровск-Урал<br/>
                      11:00
                  </p>
                </div>
              </div>          
            </div> 

            <div className="col-md-3 match_div">
              <div className="row">
                <div className="col-md-4">
                   <h4>- : -</h4>
                </div>
                <div className="col-md-8">
                   <p>
                      Ска-Хабаровск-Урал<br/>
                      11:00
                  </p>
                </div>
              </div>          
            </div>     

            <div className="col-md-3 match_div">
              <div className="row">
                <div className="col-md-4">
                   <h4>- : -</h4>
                </div>
                <div className="col-md-8">
                   <p>
                      Ска-Хабаровск-Урал<br/>
                      11:00
                  </p>
                </div>
              </div>          
            </div> 

          </div>
        </div>
);

export default /*connect(mapStateToProps, mapDispatchToProps)*/(Tourney_tour);
