import './Layout.css';
import Navbar from '../components/Navbar'

console.log(sessionStorage.getItem('userId'));
console.log(sessionStorage.getItem('username'));

const Layout = ({children}) => (
  <>
    <Navbar />
    {children}
  </>
);

export default Layout;