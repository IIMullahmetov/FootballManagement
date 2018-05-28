import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

import { tourneyA } from '../../actions';
import {  Select } from 'components/elements';
import './style.css';




const mapStateToProps = ({ tourney: { tourneyItem, tourneyList, tourneyConfig}, ...state }) => ({
  tourneyItem,
  tourneyList,
  tourneyConfig
});

const mapDispatchToProps = dispatch => ({
  getTourney: tourneyId => dispatch(tourneyA.tourneyPending(tourneyId)),
  setTourneyConfig: ({ target }) =>
    dispatch(tourneyA.setTourneyConfig(target.options[target.selectedIndex].value))
});



class TourneyBlock extends React.Component<{
  tourneyConfig: { tourneys: Array<{ value: number, text: string }> },
  getTourney: Function,
  setTourneyConfig: Function,
  tourneyId: number, 
  tourneyItem : Object,
  tourneyList: Array<Object>,
        

}> {



   componentDidMount() {   
     
     

    }
  

  

  render() { 
    return(
        <div className="col-md-4 stat_div">
                <h3 style={{color: '#0fc272', fontWeight: 'bold'}}>Статистика по турниру</h3>
                <Select
                  width={260}
                  height={40}                        
                  options={this.props.tourneyConfig.tourneys} 
                  selected={this.props.tourneyConfig.tourney}
                  defaultValue={this.props.tourneyConfig.tourneys[0]}                 
                  handler={({ target }) => this.props.getTourney(target.options[target.selectedIndex].value)}
                                       
                />               
               
                <h4 className="h4_table">Таблица</h4>
                <table className="table group_table">
                <thead>
                  <tr>                   
                    <td>Название</td>
                    <td>Голы</td>                    
                  </tr>
                </thead>

                {this.props.tourneyItem.items && this.props.tourneyItem.items.map((tourney) => {
                  const imgUrl = 'http://footballmanagement.azurewebsites.net/file/download?guid=' + tourney.image;
                  const teamLink = '/team/' + tourney.id;
                  return( <tr>                    
                   
                    <td> <img src={imgUrl}  style={{width: '15px'}}/>  <Link to={teamLink} >{tourney.name}</Link></td>
                    <td>{tourney.goals}</td>
                  </tr>);
                })}

                
                  
                </table>

                


            


              </div>

);
}
}

export default connect(mapStateToProps, mapDispatchToProps)(TourneyBlock);
