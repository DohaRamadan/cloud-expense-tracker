import React, { useState } from 'react';
import axios from 'axios';

const Login = () => {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
  });

  const { email, password } = formData;

  const onChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const onSubmit = async (e) => {
    e.preventDefault();

    try {
      // Make API request to login endpoint
      const res = await axios.post('http://localhost:8080/api/users/login', {
        email,
        password,
      });

      // Handle success, e.g., store token in state or localStorage
      console.log('Login Successful:', res.data);
    } catch (err) {
      // Handle error, e.g., display error message
      console.error('Login Failed:', err.response.data);
    }
  };

  return (
    <form onSubmit={onSubmit}>
      <input type="email" name="email" value={email} onChange={onChange} />
      <input
        type="password"
        name="password"
        value={password}
        onChange={onChange}
      />
      <button type="submit">Login</button>
    </form>
  );
};

export default Login;
