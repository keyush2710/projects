import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import './updateworkspace.css';
import { Link } from 'react-router-dom';
import { useNavigate, useParams } from 'react-router-dom';
import { useState } from 'react';

const UpdateWorkspace = () => {
  const [workspaceName, setWorkspaceName] = useState('');
  const [workspaceDesc, setWorkspaceDesc] = useState('');
  const navigate = useNavigate();
  const { workspaceId } = useParams();

const handleSubmit = async (e) => {
  e.preventDefault();
 
  // Perform API call or other operations with form values
  try{
  const response = await fetch('http://localhost:8080/workspaces/update', {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      workspaceName,
      workspaceDesc,
      workspaceId: workspaceId,
    }),
  });
      if (response.ok) {
        // Workspace updation successful
        // Handle the response or perform any additional actions
        console.log('Workspace updated successfully');
        alert('Workspace updated successfully');
        navigate('/workspace');
      } else {
        // Workspace updation failed
        // Handle the error or perform any necessary error handling
        console.error('Failed to update workspace');
        alert('Failed to update workspace');
      }
    }
  catch(error) {
      // Handle network errors or any other exceptions
      console.error('Error updating workspace:', error);
    };
};

  // Initial form values
  // const initialValues = {
  //   workspaceName: '',
  //   workspaceDetails: '',
  // };

  // Form validation schema
  const validationSchema = Yup.object({
    workspaceName: Yup.string().required('Add new name'),
    workspaceDetails: Yup.string().required('Add new description'),
  });

  return (
    <div className="updateworkspaceform" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50vh' }}>

      <div className='heading'>
      <h1>Update Workspace</h1>
      </div>
      
      <Formik
        //initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        <form className='form' onSubmit={handleSubmit}>
          <div>
            <label htmlFor="newWorkspaceName">Workspace Name</label>
            <Field type="text" id="newWorkspaceName" name="newWorkspaceName" onChange={(e) => setWorkspaceName(e.target.value)}/>
            <ErrorMessage name="newWorkspaceName" component="div" />
          </div>

          <div>
            <label htmlFor="newWorkspaceDetails">Workspace Details</label>
            <Field type="text" id="newWorkspaceDesc" name="newWorkspaceDesc" onChange={(e) => setWorkspaceDesc(e.target.value)}/>
            <ErrorMessage name="newWorkspaceDesc" component="div" />
          </div>

          <button onSubmit={handleSubmit} type="submit">Update Workspace</button>
        </form>
      </Formik>
    </div>
  );
};

export default UpdateWorkspace;
