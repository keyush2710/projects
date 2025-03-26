import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import './createworkspace.css';
import { useNavigate, useParams } from 'react-router-dom';
import { useState } from 'react';
import { Link } from 'react-router-dom';

const CreateWorkspace = (workspaceData) => {
  const [workspaceName, setWorkspaceName] = useState('');
  const [workspaceDesc, setWorkspaceDesc] = useState('');

  const navigate = useNavigate();
  // Function to handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
   
    // Perform API call or other operations with form values
    try{
    const response = await fetch('http://localhost:8080/workspaces/save', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        workspaceName,
        workspaceDesc,
      }),
    });
        if (response.ok) {
          // Workspace creation successful
          // Handle the response or perform any additional actions
          const workspaceId = await response.text(); // Assuming the response contains the ID as a plain text
          const {workspaceID}=workspaceId;
          localStorage.setItem('workspaceId', workspaceId);
          console.log('Workspace created successfully');
          alert('Workspace created successfully');
          navigate('/workspaces/'+workspaceId+'/boardhome');
        } else {
          // Workspace creation failed
          // Handle the error or perform any necessary error handling
          console.error('Failed to create workspace');
          alert('Failed to create workspace');
        }
      }
    catch(error) {
        // Handle network errors or any other exceptions
        console.error('Error creating workspace:', error);
      };
  };

  const validationSchema = Yup.object({
    workspaceName: Yup.string().required('Workspace name is required'),
    workspaceDesc: Yup.string().required('Add a short description of your Workspace!'),
  });

  return (
    <div className="createworkspaceform" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50vh' }}>
      <div className='heading'>
      <h1>Create Workspace</h1>
      </div>
      <Formik
        //initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        <form className='form' onSubmit={handleSubmit}>
          <div>
            <label htmlFor="workspaceName">Workspace Name</label>
            <Field type="text" id="workspaceName" name="workspaceName" onChange={(e) => setWorkspaceName(e.target.value)}/>
            <ErrorMessage name="workspaceName" component="div" />
          </div>

          <div>
            <label htmlFor="workspaceDesc">Workspace Details</label>
            <Field type="text" id="workspaceDesc" name="workspaceDesc" onChange={(e) => setWorkspaceDesc(e.target.value)}/>
            <ErrorMessage name="workspaceDesc" component="div" />
          </div>

          <button onClick={handleSubmit} type="submit">Create Workspace</button>
        </form>
      </Formik>
    </div>
  );
};

export default CreateWorkspace;
