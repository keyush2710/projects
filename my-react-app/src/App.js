import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'; // Import Routes
import './App.css';
import Schedule from './schedule';

function App() {
  return (
    <Router>
      <div className="App">
        <header>
          <h1>Flamingo Tution Classes</h1>
          <div>
            <ul>
              <li id="navbar"><Link to="/"><strong>Home</strong></Link></li>
              <li id="navbar"><Link to="/schedule"><strong>Schedule</strong></Link></li>
            </ul>
          </div>
        </header>
        <main>
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route path="/schedule" element={<Schedule />} /> 
          </Routes>
        </main>
      </div>
    </Router>
  );
}
function Home() {
  return (
    <div className="App">
    <main>
        <section id="info">
            <h3>General Information About Classes:</h3>
            <div>
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
                <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                </p>
            </div>
        </section>
        <img id="img1" src="./tution.jpg" alt="image of tution"></img>
        {/* https://in.pinterest.com/pin/754704850037588890/ */}
    </main>
    </div>
  );
}

export default App;
