import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';


const PostIntro = ({
  postId,
  title,
  intro,  
}: {
  postId: number,
  title: string,
  intro: string 
 }) => (
  <div className="col-md-6 match_intro_div">
    <div className="row">
                <div className="col-md-12 match_intro_header">
                  <Link to={`/match/${matchId}`}>Статистика</Link>
                </div>
                <div className="col-md-3 match_intro_goals">                  
                  <h2>{homeTeam.goals} : {guestTeam.goals}</h2>
                </div>
                <div className="col-md-8 match_intro_info">
                   <p>
                      {homeTeam.name}   -   {guestTeam.name}<br/>
                      <span >{date}</span>
                  </p>
                </div>
              </div> 

  </div>
);



export default MatchShort;
