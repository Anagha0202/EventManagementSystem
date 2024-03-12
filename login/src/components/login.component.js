import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


export default function Login({onLogin}) {
  const navigate = useNavigate();
   
  const [credentials, setCredentials] = useState({
    email: '',
    password: ''
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setCredentials({ ...credentials, [name]: value });
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/user/login', credentials);
      console.log('Login response:', response.data);
      if (response.data.message === 'Invalid credentials') {
        alert('Invalid credentials. Please try again.');
      } else {
        console.log('Login successful.');
       
        onLogin(response.data.email, response.data.loginCredentialsIsAdmin);
        
        navigate('/events');
      }
    } catch (error) {
      console.error('Login failed:', error);
      }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>Sign In</h3>
      <div className="mb-3">
        <label>Email address</label>
        <input
          type="email"
          name="email"
          value={credentials.email}
          onChange={handleInputChange}
          className="form-control"
          placeholder="Enter email"
          required
        />
      </div>
      <div className="mb-3">
        <label>Password</label>
        <input
          type="password"
          name="password"
          value={credentials.password}
          onChange={handleInputChange}
          className="form-control"
          placeholder="Enter password"
          required
        />
      </div>
      <div className="d-grid">
        <button type="submit" className="btn btn-primary">
          Submit
        </button>
      </div>
      
    </form>
  );
}
