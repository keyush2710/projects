import React from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import * as Yup from 'yup';
import './createboard.css';
import { useNavigate, useParams } from 'react-router-dom';
import { useState } from 'react';
import { useEffect } from 'react';

const CreateBoard = () => {
  const [boardName, setBoardName] = useState('');
  const navigate = useNavigate();
  const [storedValue, setStoredValue] = useState('');
  const {workspaceId}=useParams();
  useEffect(() => {
    // Get the value from local storage using its key (e.g., 'myValueKey')
    const storedValueFromLocalStorage = localStorage.getItem('workspaceId');

    // Update the state variable with the value from local storage
    if (storedValueFromLocalStorage) {
      setStoredValue(storedValueFromLocalStorage);
    }
  }, []);
  // Get workspaceId from local storage
  // const { workspaceId }=useState('') ;
  
  const handleSubmit = async (e) => {
    e.preventDefault();

    // Perform API call or other operations with form values
    try {
      console.log(workspaceId);
      const body = JSON.stringify({
        boardName,
        workspaceId:workspaceId, // Use the value from local storage
      });

      console.log(body);

      const response = await fetch(`http://localhost:8080/boards/save?workspaceId=${workspaceId}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          boardName,
          workspaceId:workspaceId,
        }),
      });

      if (response.ok) {
        // Board creation successful
        // Handle the response or perform any additional actions
        const boardId = await response.text(); // Assuming the response contains the ID as a plain text
        localStorage.setItem('boardId', boardId);
        console.log('Board created successfully');
        alert('Board created successfully');
        navigate('/workspaces/'+workspaceId+'/board/'+boardId);
      } else {
        // Board creation failed
        // Handle the error or perform any necessary error handling
        console.error('Failed to create board');
        alert('Failed to create board');
      }
    } catch (error) {
      // Handle network errors or any other exceptions
      console.error('Error creating board:', error);
    }
  };

  // Initial form values
  // const initialValues = {
  //   boardName: '',
  // };
  // Form validation schema
  const validationSchema = Yup.object({
    boardName: Yup.string().required('Board name is required'),
  });

  return (
    <div className="createboardform" style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '50vh' }}>
      <div className='heading'>
        <h1>Create Your Board!</h1>
      </div>
      <Formik
        // initialValues={initialValues}
        validationSchema={validationSchema}
        onSubmit={handleSubmit}
      >
        <form className='form' onSubmit={handleSubmit}>
          <div>
            <label htmlFor="boardName">Board Name</label>
            <Field type="text" id="boardName" name="boardName" onChange={(e) => setBoardName(e.target.value)} />
            <ErrorMessage name="boardName" component="div" />
          </div>
          <button type="submit">Create Board</button>
        </form>
      </Formik>
    </div>
  );
};

export default CreateBoard;
