import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    //navigate from the login page to other pages
    const navigate = useNavigate();
    const handleLogIn = async (e) => {
        e.preventDefault();
        try{
            const response = await fetch('http://localhost:8080/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    emailID:email,
                    password: password
                }),
            });
            if(response.ok){
                console.log('Login successful');
                alert("Login Successful");
    
                localStorage.setItem('loggedIn', 'true');
                navigate('/home');
            }
            else if (response.status === 401){
                console.log("invalid email or password");
                alert("invalid email or password");
            }
            else{
                console.error('Login failed!');
                alert('Login failed!');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div className="container">
            <h1>Log In</h1>
            <form onSubmit={handleLogIn}>
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button type="submit">Log In</button>
            </form>
            <p>Don't have an account? <Link to="/signup">Sign up</Link></p>
            <p>Forgot your password? <Link to="/forgotpassword">Reset password</Link></p>
        </div>
    );
}

export default Login;
