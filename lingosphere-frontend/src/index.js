import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Layout from './pages/Layout';
import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import SignUp from "./pages/SignUp";
import SignIn from "./pages/SignIn";
import LangSelect from "./pages/LangSelect";
import Profile from "./pages/Profile";
import Dashboard from "./pages/Dashboard";

const ProtectedRoute = ({ children }) => {
    if (!sessionStorage.getItem("userId")) {
        // Redirect to the sign-in page
        return <Navigate to="/signin" />;
    }
    return children;
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Router>
            <Routes>
                <Route path="/signup" element={<SignUp />} />
                <Route path="/signin" element={<SignIn />} />
                <Route element={<ProtectedRoute />} />
                    <Route element={<Layout />}>
                        <Route path="/profile" element={<Profile />} />
                        <Route path="/" element={<Dashboard />} />
                        <Route path="/language-select" element={<LangSelect />} />
                    </Route>
            </Routes>
        </Router>
    </React.StrictMode>
);