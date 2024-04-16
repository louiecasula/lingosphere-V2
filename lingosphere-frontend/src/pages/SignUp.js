import React, { useState } from 'react';
import { TextField, Button, Avatar, CssBaseline, Link, Grid, Box, Typography, Container } from '@mui/material';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { registerUser } from '../api/userApi';
import { useNavigate } from 'react-router-dom';

const defaultTheme = createTheme();

export default function SignUp() {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [emailError, setEmailError] = useState('');
  const [username, setUsername] = useState('');
  const [usernameError, setUsernameError] = useState('');

  // Function to validate email
  const validateEmail = (email) => {
    return /\S+@\S+\.\S+/.test(email);
  };

  // Function to validate username
  const validateUsername = (username) => {
    return /^[a-zA-Z0-9]{6,20}$/.test(username);
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const email = data.get('email');
    const username = data.get('username');
    const password = data.get('password');
    const confirmPassword = data.get('confirm-password');

    // Reset errors
    setEmailError('');
    setUsernameError('');

    let isValid = true;

    // Email validation
    if (!validateEmail(email)) {
      setEmailError('Invalid email format');
      isValid = false;
    }

    // Username validation
    if (!validateUsername(username)) {
      setUsernameError('Username must be 6 - 20 alphanumeric characters');
      isValid = false;
    }

    // Password validation
    if (password !== confirmPassword) {
      console.log("Passwords don't match");
      isValid = false;
    }

    // API call to add user to database
    if (isValid) {
      registerUser({ username, email, password })
              .then(data => {
                  console.log('Success:', data);
                  if (data.userId) {
                      sessionStorage.setItem('userId', data.userId);
                      sessionStorage.setItem('username', data.username);
                      navigate('../settings');
                  }
              })
              .catch((error) => {
                  console.error('Error:', error);
              });
    };
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Sign up
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="email"
                  label="Email Address"
                  name="email"
                  autoComplete="email"
                  onChange={e => setEmail(e.target.value)}
                  error={!!emailError}
                  helperText={emailError}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="username"
                  label="Username"
                  name="username"
                  autoComplete="username"
                  onChange={e => setUsername(e.target.value)}
                  error={!!usernameError}
                  helperText={usernameError}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="password"
                  label="Password"
                  type="password"
                  id="password"
                  autoComplete="new-password"
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name="confirm-password"
                  label="Re-enter Password"
                  type="password"
                  id="confirm-password"
                  autoComplete="new-password"
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Sign Up
            </Button>
            <Grid container justifyContent="flex-end">
              <Grid item>
                <Link href="/signin" variant="body2">
                  Already have an account? Sign in
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}