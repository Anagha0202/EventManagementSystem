import React, { useState } from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import Login from './components/login.component'
import SignUp from './components/signup.component'
import 'bootstrap/dist/css/bootstrap.min.css';

import ViewEvent from './components/events.components'


import Body from './components/seats.components'
import Payment from './components/payment.component';

function App() {
  const [loginCredentialsIsAdmin, setLoginCredentialsIsAdmin] = useState();
  const [userEmail, setUserEmail] = useState('');
  const [reservationId, setReservationId] = useState(null);

  const handleLogin = (email,loginCredentialsIsAdmin) => {
    setUserEmail(email);
    setLoginCredentialsIsAdmin(loginCredentialsIsAdmin);
  };
  return (
    <Router>
      {/* <UserProvider> */}
      <div className="App">
        <nav className="navbar navbar-expand-lg navbar-light fixed-top">
          <div className="container">
            <Link className="navbar-brand" to={'/sign-in'}>
            </Link>
            <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
              <ul className="navbar-nav ml-auto">
                <li className="nav-item">
                  <Link className="nav-link" to={'/sign-in'}>
                    Login
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to={'/signup'}>
                    Sign up
                  </Link>
                </li>
                
                <li className="nav-item">
                  <Link className="nav-link" to={'/'}>
                    Logout
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <div className="auth-wrapper">
          <div className="auth-inner">
            <Routes>
              <Route exact path="/" element={<Login onLogin={handleLogin} />} />
              <Route path="/sign-in" element={<Login onLogin={handleLogin} />} />
              <Route path="/signup" element={<SignUp />} />
              <Route path="/events" element={<ViewEvent loginCredentialsIsAdmin={loginCredentialsIsAdmin}/>} />
              <Route path="/seats/:eventId" element={<Body userEmail={userEmail} setReservationId={setReservationId} />} />
               <Route path="/payment" element={<Payment reservationId={reservationId} />} />

              {/* <Route path="/seats" element={<Seatbooking />} /> */}
            </Routes>
          </div>
        </div>
      </div>
      {/* </UserProvider> */}
    </Router>
  )
}
export default App;