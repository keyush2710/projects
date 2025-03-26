// import React, { useEffect, useState } from 'react';
// import { useParams } from 'react-router-dom';
// import { useNavigate} from 'react-router-dom';

// const DeleteBoard = () => {
//     const [boardId,setBoardId]=useState('') ;
//     const [message, setMessage] = useState('');
//     const navigate = useNavigate();
//     const {workspaceId}=useParams();
//     useEffect(() => {
//       // Get the value from local storage using its key (e.g., 'myValueKey')
//       const storedValueFromLocalStorage = localStorage.getItem('workspaceId');
  
//       // Update the state variable with the value from local storage
//       if (storedValueFromLocalStorage) {
//         setBoardId(storedValueFromLocalStorage);
//       }
//     }, []);
    
//     const deleteBoard = async () => {
//       try {
//         const response = await fetch(`http://localhost:8080/board/delete?boardID=${boardId}`, {
//           method: 'DELETE',
//           headers: {
//             'Content-Type': 'application/json',
//           },
//           body: JSON.stringify({
//             boardID:boardId,
//           }),

//         });
  
//         if (response.ok) {
//           alert('Board deleted successfully');
//           navigate('/workspaces/'+workspaceId+'/workspace');
//         } else {
//           const error = await response.text();
//           alert(`Failed to delete board: ${error}`);
//         }
//       } catch (error) {
//         console.error('Error deleting board:', error);
//         setMessage('An error occurred while deleting the board');
//       }
//     };
//     return (
//       <div>
//         <h1>Delete Board</h1>
//         <p>{message}</p>
//       </div>
//     );
//   };
  
//   export default DeleteBoard;