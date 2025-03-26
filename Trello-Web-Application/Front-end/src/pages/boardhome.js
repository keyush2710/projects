
// import React from 'react';
// import { Link, useParams } from 'react-router-dom';
// import { FaBars } from 'react-icons/fa';
// import '../components/navbar/navbar.css';
// import Sidebar from '../components/Sidebar/sidebar';
// import './boardhome.css';
// import { useState, useEffect } from 'react';

// const BoardHome = () => {
//   const workspaceName = "My Workspace";
//    const workspaceDesp= "Workspace Description";
//    const userName="User Name";
   
//   const { workspaceId } = useParams(); // Use destructuring to get the workspaceId value
//  // console.log(localStorage.getItem(workspaceId));
//   const [boards, setBoards] = useState([]);
//   const [boardId, setBoardId] = useState([]);
//   useEffect(() => {
//     // Fetch the list of workspaces from the backend on component mount
//     fetchBoards();
//   }, []);

//   const fetchBoards = async() => {
//     try{
//     const response = await fetch(`http://localhost:8080/boards/getBoards?workspaceId=${workspaceId}`,{
//       method: 'GET',
//     });
//       if(response.ok) {
//         const boardsData = await response.json(); // Parse the response body as JSON
//         setBoards(boardsData);
//         console.log(boardsData);
//       }
//     }
//       catch(error){
//         console.error('Error fetching workspaces:', error);
//       }
//     };
//     useEffect(() => {
//       // Fetch the list of workspaces from the backend on component mount
//       fetchBoardIds();
//     }, [workspaceId]); 
  
//     const fetchBoardIds = async() => {
//       try{
//       const response = await fetch(`http://localhost:8080/boards/getBoardIds?workspaceId=${workspaceId}`,{
//         method: 'GET',
//       });
//         if(response.ok) {
//           const boardsData = await response.text(); // Parse the response body as JSON
//           //setBoards(boardsData);
//           setBoardId(boardsData);
//           console.log(boardsData);
//           console.log(boardId,"hello");
//         }
//         console.log(boardId,"hello");
//       }
//         catch(error){
//           console.error('Error fetching workspaces:', error);
//         }
//       };
//   return (
//     <nav className="boardhome">
//       <Sidebar workspaceName={workspaceName} workspaceDesp={workspaceDesp}/>
//       <div className="boardhome-menu">
//         <Link to={`/workspaces/${workspaceId}/createboard`} className="nav-link" activeClassName="active">
//           Create Board
//         </Link>
//       </div>  
//       <ul>
//        {boards.map((item, index) => (
//           <Link to={`/workspaces/${workspaceId}/board/${boardId[index]}`}className='boardname'><li key={index}>
//           Board {item}
//         </li></Link>
          
//         ))}
//     </ul>
//     </nav>
//   );
// };

// export default BoardHome;

import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import '../components/navbar/navbar.css';
import Sidebar from '../components/Sidebar/sidebar';
import './boardhome.css';

const BoardHome = () => {
  const workspaceName = "My Workspace";
  const workspaceDesp = "Workspace Description";
  const userName = "User Name";

  const { workspaceId } = useParams(); // Use destructuring to get the workspaceId value

  const [boards, setBoards] = useState([]);
  const [boardIds, setBoardIds] = useState([]);

  useEffect(() => {
    // Fetch the list of boards from the backend on component mount
    fetchBoards();
    fetchBoardIds();
  }, [workspaceId]);

  const fetchBoards = async () => {
    try {
      const response = await fetch(`http://localhost:8080/boards/getBoards?workspaceId=${workspaceId}`, {
        method: 'GET',
      });

      if (response.ok) {
        const boardsData = await response.json();
        setBoards(boardsData);
        console.log(boardsData);
      }
    } catch (error) {
      console.error('Error fetching boards:', error);
    }
  };

  const fetchBoardIds = async () => {
    try {
      const response = await fetch(`http://localhost:8080/boards/getBoardIds?workspaceId=${workspaceId}`, {
        method: 'GET',
      });

      if (response.ok) {
        const boardIdsData = await response.json(); // Assuming the response is an array of strings
        setBoardIds(boardIdsData);
        console.log(boardIdsData);
      }
    } catch (error) {
      console.error('Error fetching board IDs:', error);
    }
  };

  return (
    <nav className="boardhome">
      <Sidebar workspaceName={workspaceName} workspaceDesp={workspaceDesp} />
      <div className="boardhome-menu">
        <Link to={`/workspaces/${workspaceId}/createboard`} className="nav-link" activeClassName="active">
          Create Board
        </Link>
      </div>
      <ul>
        {boards.map((item, index) => (
          <Link to={`/workspaces/${workspaceId}/board/${boardIds[index]}`} className='boardname' key={index}>
            <li>
              Board {item}
            </li>
          </Link>
        ))}
      </ul>
    </nav>
  );
};

export default BoardHome;