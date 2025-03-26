import * as React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import Sidebar from '../components/Sidebar/sidebar';
import './workspace.css';

const Workspace = () => {
  const navigate= useNavigate();
  const handleLogout = () => {
    // Remove flag from local storage
    localStorage.removeItem('loggedIn');
    //Redirect the user to the login page or wherever you want to take them outside 
    navigate('/login');
    };
   // Sample workspace data
   const workspaceName = "My Workspace";
   const workspaceDesp= "Workspace Description";
   const userName="User Name";
   const boards = [
     { id: 1, name: "Board 1" },
     { id: 2, name: "Board 2" },
     // Add more boards
   ];
  return (
    <div className="workspace-page">
      <Sidebar workspaceName={workspaceName} workspaceDesp={workspaceDesp} userName={userName}/>
      <div className='boardslist'>
        {boards.map(board => (
        <Link to={`/board/${board.id}`} key={board.id} className='boardname'><li>{board.name}</li></Link>
        ))}
      </div>
      <div className='update-workspace'>
      <a href="./updateworkspacedetails">Update Workspace Details</a>
      </div>
      <div className='createboard'>
        <a href="./createboard">Create New Board</a>
      </div>
      <div className='createworkspace'>
        <a href="./createworkspace">Create New Workspace</a>
      </div>
      <div className='logout'>
      <button onClick={handleLogout} className='logout-button'>Logout</button>
      </div>
    </div>
  );
  }
  
export default Workspace;

