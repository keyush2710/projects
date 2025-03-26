import React,{useState} from "react";
import './sidebar.css';
import { Link, useParams } from 'react-router-dom';
import { useNavigate} from 'react-router-dom';
import { useEffect } from "react";


const Sidebar = ({ workspaceName, workspaceDesp, userName }) => {
  const [selectedMember, setSelectedMember] = useState('');
  const [ members, setMembers] = useState([]);
  
  const {workspaceId}=useParams();
  
  useEffect(() => {
    fetchMembers();
  }, []);

  const fetchMembers = async() => {
    try{
    const response = await fetch(`http://localhost:8080/workspaces/getAllMembers?id=${workspaceId}`,{
      method: 'GET',
    });
      if(response.ok) {
        const membersData = await response.json(); // Parse the response body as JSON
        setMembers(membersData);
      }
    }
      catch(error){
        console.error('Error fetching Memberss:', error);
      }
    };
 // const members = ['Member 1', 'Member 2', 'Member 3'];
  const navigate = useNavigate();

  const handleMemberChange = (event) => {
    setSelectedMember(event.target.value);
  };
  const handleAddMember = () => {
    // Redirect to the add member page
 
    navigate('/workspaces/'+workspaceId+'/invitemembers');
  };
    return (
      <div className="sidebar">
        <h3>{workspaceName}</h3>
        <p>{workspaceDesp}</p>
        <nav>
          <select value={selectedMember} onChange={handleMemberChange} className="dropdown">
          <option value="">Show Members</option>
          {members.map((member) => (
          <option value={member} key={member}>
            {member}
          </option>
        ))}
      </select>
      <a href={`/workspaces/${workspaceId}/invitemembers`} className="invitelink">Add Member</a>
        </nav>
        <div className="user-profile">
          <p>{userName}</p>
          {/* Additional user profile information */}
        </div>
        <div>
        </div>
      </div>
    );
  };
  export default Sidebar;  