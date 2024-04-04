import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Layout from './Layout';
import {BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import SignUp from "./components/SignUp";
import SignIn from "./components/SignIn";
import LangSelect from "./components/LangSelect";

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
                        <Route path="/profile" />
                        <Route path="/"/>
                        <Route path="/language-select" element={<LangSelect />} />
                    </Route>
            </Routes>
        </Router>
    </React.StrictMode>
);