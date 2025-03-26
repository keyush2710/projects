import React from 'react';
import { Link } from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import './navbar.css';

const Navbar = () => {
  return (
    <nav className="nav">
      <div className="nav-menu">
        <Link to="/signup" className="nav-link" activeClassName="active">
          Signup
        </Link>
        <Link to="/login" className="nav-link" activeClassName="active">
          Login
        </Link>
        <a style={{ position: 'absolute', top: '50px', left: '10px' }}>
          <img
            src="ByteBoardsLogo.png"
            alt="Logo"
            style={{ width: '250px', height: '100px' }}
          />
        </a>
      </div>
    </nav>
  );
};

export default Navbar;
