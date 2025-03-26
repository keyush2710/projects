import React from 'react';
import './App.css';
import Navbar from './components/navbar/index';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Signup from './pages/signup';
import Login from './pages/login';
import Workspace from './pages/workspace';
import CreateWorkspace from './pages/createworkspace';
import Home from './pages/home';
import UpdateWorkspace from './pages/updateworkspacedetails';
import InviteMembers from './pages/invitemembers';
import BoardHome from './pages/boardhome';
import CreateBoard from './pages/createboard';
import Board from './pages/board';
import DeleteBoard from './pages/deleteboard';
import ForgotPassword from './pages/forgotpassword';

function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path='/signup' element={<Signup />} />
        <Route path='/login' element={<Login />} />
        <Route path='/home' element={<Home />} />
        {/* <Route path='/workspace/:workspaceId' element={<Workspace />} /> */}
        {/* <Route path='/workspace/:workspaceId/createboard' element={<CreateBoard />} /> */}
        <Route path='/workspaces/:workspaceId/workspace' element={<Workspace />} />
        {/* <Route path='/workspace/createboard' element={<CreateBoard />} /> */}
        <Route path='/workspaces/:workspaceId/board/:boardId' element={<Board />} />
        {/* <Route path='/board/:boardId'  element={<Board />}/> */}
        <Route path='/createworkspace' element={<CreateWorkspace />} />
        <Route path='/workspaces/:workspaceId/boardhome' element={<BoardHome />} />
        <Route path='/workspaces/:workspaceId/createboard' element={<CreateBoard />} />
        <Route path='/updateworkspacedetails' element={<UpdateWorkspace />} />
        <Route path='/workspaces/:workspaceId/invitemembers' element={<InviteMembers />} />
        <Route path='/deleteboard' element={<DeleteBoard />} />
        <Route path='/forgotpassword' element={<ForgotPassword />} />
      </Routes>
    </Router>
  );
}
export default App;

//<Route path='/board' element={<Board />} />