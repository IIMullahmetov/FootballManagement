import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';


const MatchShort = ({
  matchId,
  date,
  status,
  homeTeam,
  guestTeam
}: {
  matchId: number,
  date: date,
  status: string,
  homeTeam: Object,
  guestTeam: Object,
 }) => (
  <div className="col-md-6">
    <div className="row">
                <div className="col-md-4">
                  <Link to={`/match/${matchId}`}>Статистика</Link>
                  <h4>{homeTeam.goals} : {guestTeam.goals}</h4>
                </div>
                <div className="col-md-8">
                   <p>
                      {homeTeam.name}-{guestTeam.name}<br/>
                      {date}
                  </p>
                </div>
              </div> 

  </div>
);



export default MatchShort;
