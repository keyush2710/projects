import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Signup() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [emailID, setEmailID] = useState('');
  const [password, setPassword] = useState('');
  const [securityAnswer, setSecurityAnswer] = useState('');
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/user/save', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          firstName: firstName,
          lastName:lastName,
          emailID: emailID,
          password: password,
          securityAnswer:securityAnswer,
        }),
      });

      if (response.ok) {
        // Signup successful
        console.log('Signup successful!');
        alert('Signup successful');
        navigate('/login');
        // Perform any additional actions or redirect the user
      }
      else if (response.status === 400){
        //Signup failed
        console.log('Invalid Password Format or Passwords do not match');
        alert('Invalid Password Format or Passwords do not match');
      }
      else {
        // Signup failed
        console.error('Signup failed!');
        alert('Signup failed!');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div className="container">
      <h1>Sign Up</h1>
      <form onSubmit={handleSignup}>
        <input
          type="text"
          placeholder="Firstname"
          required
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <input
          type="text"
          placeholder="Lastname"
          required
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
        <input
          type="email"
          placeholder="Email"
          required
          value={emailID}
          onChange={(e) => setEmailID(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          required
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <p>
        What is the name of your pet?
        </p>
        <input
          type="text"
          placeholder="Security Answer"
          required
          value={securityAnswer}
          onChange={(e) => setSecurityAnswer(e.target.value)}
        />
        <button type="submit">Sign Up</button>
      </form>
      <p>
        Already have an account? <Link to="/login">Log in</Link>
      </p>
    </div>
  );
}

export default Signup;
