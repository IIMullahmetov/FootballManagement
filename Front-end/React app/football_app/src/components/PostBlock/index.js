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
const Post_block = ({
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
  <ul>
  <h3>Главные новости</h3>
          <li>
          <p>
            22:12 <br/>
            Сол Кэмпбелл сравнил решение<br/> министров Великобритании<br/> бойкотировать ЧМ-2018 с режимами<br/>
            Гитлера и Муссолини 20
          </p>
           </li>
           <li><p>
            22:12 <br/>
            Сол Кэмпбелл сравнил решение<br/> министров Великобритании<br/> бойкотировать ЧМ-2018 с режимами<br/>
            Гитлера и Муссолини 20
          </p>
           </li>
           <li><p>
            22:12 <br/>
            Сол Кэмпбелл сравнил решение<br/> министров Великобритании<br/> бойкотировать ЧМ-2018 с режимами<br/>
            Гитлера и Муссолини 20
          </p>
           </li>
           <li>
           <p>
            22:12 <br/>
            Сол Кэмпбелл сравнил решение<br/> министров Великобритании<br/> бойкотировать ЧМ-2018 с режимами<br/>
            Гитлера и Муссолини 20
          </p>
           </li>
    </ul>
);

export default connect()(Post_block);
