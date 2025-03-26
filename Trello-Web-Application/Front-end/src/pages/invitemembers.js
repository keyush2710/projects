import React, { useState } from 'react';
import ReactDOM from 'react-dom';
import { Formik, Field, Form, ErrorMessage, FieldArray } from 'formik';
import './invitemembers.css';
import { Link, useParams } from 'react-router-dom';

const initialValues = {
  members: [
    {
      email: '',
    },
  ],
};
const {workspaceId}=useParams;
console.log(workspaceId);
const InviteMembers = () => (
  <div className='invitemembers'>
    <h1>Invite Members</h1>
    <Formik
      initialValues={initialValues}
      onSubmit={async (values, { setSubmitting }) => {
        let email=values.members[0].email;
        try {
          const response = await fetch(`http://localhost:8080/workspaces/addMember?workspaceID=${localStorage.getItem('workspaceId')}&email=${email}`, {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(values),
          });

          if (!response.ok) {
            const errorData = await response.text();
            alert(errorData);
            throw new Error(errorData.message || 'Something went wrong.');
          }

          const responseData = await response.text();
          alert(responseData);
          console.log('Invite successful:', responseData);
          // You can perform any additional actions after a successful invite here.
        } catch (error) {
          console.error('Invite failed:', error.message);
          // Handle errors or display error messages to the user.
        } finally {
          setSubmitting(false);
        }
      }}
    >
      {({ values }) => (
        <Form className='inviteform'>
          <FieldArray name="members">
            {({ insert, remove, push }) => (
              <div>
                {values.members.length > 0 &&
                  values.members.map((member, index) => (
                    <div className="row" key={index}>
                      <div className="col">
                        <label htmlFor={`members.${index}.email`} className='email'>Email ID</label>
                        <Field
                          name={`members.${index}.email`}
                          placeholder="abc@example.com"
                          type="email"
                          className='textfield'
                        />
                        <ErrorMessage
                          name={`members.${index}.name`}
                          component="div"
                          className="field-error"
                        />
                        <button type="submit">Invite</button>
                        
                        <Link to={`/workspaces/${localStorage.getItem('workspaceId')}/boardhome`} >
                        <button type="submit">Go Back To  My Workspace</button>
                        </Link>
                      </div>
                    
                    </div>
                  ))}
              </div>
            )}
          </FieldArray>
        </Form>
      )}
    </Formik>
  </div>
);

export default InviteMembers;