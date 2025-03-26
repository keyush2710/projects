import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Formik, FormikProvider, useFormik } from 'formik';
import * as yup from 'yup';
import {
  Container,
  Typography,
  TextField,
  Button,
  Link,
  Box,
  CircularProgress,
} from '@mui/material';

export default function ForgotPassword() {
  const forgotPasswordSchema = yup.object().shape({
    userId: yup.string().required('User ID is required'),
    securityAnswer: yup.string().required('Security answer is required'),
    newPassword: yup.string().min(8).max(20).required('New password is required'),
    confirmPassword: yup
      .string()
      .required('Confirm password is required')
      .oneOf([yup.ref('newPassword'), null], 'Passwords must match'),
  });

  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      userId: '',
      securityAnswer: '',
      newPassword: '',
      confirmPassword: '',
    },
    validationSchema: forgotPasswordSchema,
    onSubmit: async (values) => {
      const { userId, securityAnswer, newPassword, confirmPassword } = values;
      console.log(userId, securityAnswer, newPassword, confirmPassword);
      // Reset password logic here

      // Redirect to login page
      navigate('/login');
    },
  });

  const { errors, touched, isSubmitting, handleSubmit, getFieldProps } = formik;

  return (
    <Container maxWidth="xs">
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          marginTop: 8,
        }}
      >
        <Typography component="h1" variant="h5">
          Forgot Password
        </Typography>
        <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <TextField
            fullWidth
            label="User ID"
            {...getFieldProps('userId')}
            error={Boolean(touched.userId && errors.userId)}
            helperText={touched.userId && errors.userId}
            margin="normal"
            required
          />
          <TextField
            fullWidth
            label="Security Answer"
            {...getFieldProps('securityAnswer')}
            error={Boolean(touched.securityAnswer && errors.securityAnswer)}
            helperText={touched.securityAnswer && errors.securityAnswer}
            margin="normal"
            required
          />
          <TextField
            fullWidth
            type="password"
            label="New Password"
            {...getFieldProps('newPassword')}
            error={Boolean(touched.newPassword && errors.newPassword)}
            helperText={touched.newPassword && errors.newPassword}
            margin="normal"
            required
          />
          <TextField
            fullWidth
            type="password"
            label="Confirm Password"
            {...getFieldProps('confirmPassword')}
            error={Boolean(touched.confirmPassword && errors.confirmPassword)}
            helperText={touched.confirmPassword && errors.confirmPassword}
            margin="normal"
            required
          />
          <Button
            fullWidth
            type="submit"
            variant="contained"
            disabled={isSubmitting}
            sx={{ mt: 3, mb: 2 }}
          >
            {isSubmitting ? (
              <CircularProgress size={24} color="inherit" />
            ) : (
              'Submit'
            )}
          </Button>
        </Box>
        <Box sx={{ mt: 2, textAlign: 'center' }}>
          <Link href="/login" variant="body2">
            Back to Login
          </Link>
        </Box>
      </Box>
    </Container>
  );
}
