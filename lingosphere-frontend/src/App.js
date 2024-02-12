import './App.css';
import Navbar from './components/Navbar'

console.log(sessionStorage.getItem('userId'));
console.log(sessionStorage.getItem('username'));

function App() {
  return (
    <Navbar />
  );
}

export default App;