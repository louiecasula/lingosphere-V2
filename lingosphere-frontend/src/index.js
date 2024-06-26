import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Layout from './pages/Layout';
import {BrowserRouter as Router, Routes, Route, Navigate, Outlet} from "react-router-dom";
import SignUp from "./pages/SignUp";
import SignIn from "./pages/SignIn";
import Settings from "./pages/Settings";
import Profile from "./pages/Profile";
import Dashboard from "./pages/Dashboard";
import WordInfo from "./pages/WordInfo";

// Redirect user if they aren't signed in
const ProtectedRoute = () => {
    return sessionStorage.getItem("userId")? <Outlet />: <Navigate to="/signin" />;
};

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
    <React.StrictMode>
        <Router>
            <Routes>
                <Route path="/signup" element={<SignUp />} />
                <Route path="/signin" element={<SignIn />} />
                <Route element={<ProtectedRoute />}>
                    <Route element={<Layout />}>
                        <Route path="/word-history" element={<Profile />} />
                        <Route path="/" element={<Dashboard />} />
                        <Route path="/settings" element={<Settings />} />
                        <Route path="/words/:wordId" element={<WordInfo />} />
                    </Route>
                </Route>
            </Routes>
        </Router>
    </React.StrictMode>
);