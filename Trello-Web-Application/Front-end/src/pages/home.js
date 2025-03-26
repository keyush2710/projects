import React from 'react';
import { Link } from 'react-router-dom';
import { FaBars } from 'react-icons/fa';
import '../components/navbar/navbar.css';
import "./home.css";
import { useEffect,useState } from 'react';

const Home = () => {
  // const workspaces = [
  //   { id: 1, name: "Workspace 1" },
  //   { id: 2, name: "Workspace 2" },
  //   // Add more woprkspaces
  // ];const WorkspacesList = () => {
  const [workspaces, setWorkspaces] = useState([]);
  //const [work,setWork]=useState([]);

  useEffect(() => {
    // Fetch the list of workspaces from the backend on component mount
    fetchWorkspaces();
  }, []);

  const fetchWorkspaces = async() => {
    try{
    const response = await fetch('http://localhost:8080/workspaces/getWorkspaces',{
      method: 'GET',
    });
      if(response.ok) {
        const workspacesData = await response.json(); // Parse the response body as JSON
        setWorkspaces(workspacesData);
        // const newWork = workspacesData.map((workspace) => {
        //   return {
        //     id: workspace.id,
        //     name: workspace.name,
        //     // Add other properties as needed from the workspaceData
        //   };
        // });
        // setWork(newWork);
        // console.log(workspacesData);
      }
    }
      catch(error){
        console.error('Error fetching workspaces:', error);
      }
    };
    console.log(workspaces);
    return (
      <nav className="home">
        <div className="home-menu">
        <div className='workspacelist'>
        {/* {workspaces.map(workspace => (   
        <Link to={`/workspaces/${workspace.id}`} key={workspace.id} className='workspacename'><li>{workspace.name}</li></Link>
        ))} */}
        <ul>
       {workspaces.map((item, index) => (
          <Link to={`/workspaces/${index+1}/boardhome`}className='workspacename'><li key={index}>
          Workspace {item}
        </li></Link>
          
        ))}
    </ul>
      </div>
        <Link to="/createworkspace" className="nav-link" activeClassName="active">
            Create Workspace
        </Link>
        </div>
        
      </nav>
    );
  };
  
  export default Home;