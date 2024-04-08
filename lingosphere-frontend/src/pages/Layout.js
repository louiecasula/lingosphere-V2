import './Layout.css';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import { Outlet } from 'react-router-dom';

console.log(sessionStorage.getItem('userId'));
console.log(sessionStorage.getItem('username'));

const Layout = () => {
  return (
    <div>
      <Navbar />
      <Outlet />
      <Footer />
    </div>
  )
};

export default Layout;