import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';


import MatchShort from 'components/MatchShort';

import {matchA} from 'actions'

//import { sidebarA, homeA, modalsA } from '../../actions';
import './style.css';

const mapDispatchToProps = dispatch => ({
  getLastMatches: () => dispatch(matchA.matchListPending()),
});

const mapStateToProps = ({ match: { lastMatchesList } }) => ({
  lastMatchesList,
});


class LastMatches extends  React.Component<{
  getLastMatches: Function,
  lastMatchesList: Array<Object>,
  
}>  {

  componentDidMount() {      
    this.props.getLastMatches();   
  }

  render() {

  const matches = this.props.lastMatchesList;  


    {/*if(typeof matches != "undefined") {
         
         return (
          <div className="col-md-12">
            <div className="row">
              { this.props.lastMatches.map((match) => 
                <MatchShort
                date={match.date}
                status={match.status}
                homeTeam={match.homeTeam} 
                guestTeam={match.guestTeam}
              />
          )
        }
          
        </div>
      </div>
      )
  }*/}

  return (
          <div className="col-md-12">
            <div className="row">             
              { this.props.lastMatchesList.map((match) => 
                <MatchShort
                  matchId={match.id}
                  date={match.startDt}
                  status={match.status}
                  homeTeam={match.home} 
                  guestTeam={match.guest}
                />
                  )
              }
        </div>
      </div>
      );


  }
}

export default connect(mapStateToProps, mapDispatchToProps)(LastMatches);
