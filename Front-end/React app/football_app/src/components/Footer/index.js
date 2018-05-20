import React from 'react';

import './style.css';

const Footer = () => (
  <footer className="footer" >
      <div className="container-fluid inner-footer">
          <span className="footer-groups group-list">Product developed by</span>
          <div className="row footer-groups ">
              <div className="col-md-3 group-list">
                Anatoliy Khlopunov<br/>
                Artyom Pashkin<br/>
                Bulat Motygullin
              </div>
              <div className="col-md-3 group-list">
                Ilvir Dimukhametov<br/>
                Ilyas Mullahmetov <br/>
                Iskander Khakimzhanov<br/>
              </div>
              <div className="col-md-3 group-list">
                Rinaz Siraziev<br/>
                Stepan Lukyanov<br/>
                Timur Timerkhanov
              </div>
              <div className="col-md-3 group-list">
                Gulshat Nurieva<br/>
                Kirill Kislov<br/>
                PM: Yasina Zalyalova 
              </div>
          </div>
       <hr/>
        <span className="info-line">&copy; 2018 | Kazan Federal University, ITIS 11-503</span>
      </div>
    </footer>
);

export default Footer;
