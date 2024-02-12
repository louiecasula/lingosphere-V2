import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import SignUp from "./components/SignUp";
import SignIn from "./components/SignIn";

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
                <Route
                    path="/"
                    element={
                        <ProtectedRoute>
                            <App />
                        </ProtectedRoute>
                    }
                />
            </Routes>
        </Router>
    </React.StrictMode>
);