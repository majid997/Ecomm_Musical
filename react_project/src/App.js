import logo from './logo.svg';
import './App.css';
import Login from './Membership/Login';
import Register from './Membership/Register';
import About from './Layout/About';
import Contact from './Layout/Contact';

import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom'
import './index.css';
import List from './ProductCatalog/list';
import Orders from './OrderProcessing/orders';
import Hello from './Layout/hello';
import Goodbye from './Layout/Goodbye';
import Bag from './ProductCatalog/bag';
import Admin from './Membership/Admin'
//import Cart from "./components/Cart";

 import Cart from './ShoppingCart/Cart';
import AddProduct from './Membership/AddProduct';
import ListProductComponent from './Components/ListUserComponent';
import EditUserComponent from './Components/EditUserComponent';

 function App() {
  return (
    <div className="App">
     <BasicRouting></BasicRouting>
     </div>
  );
}


function BasicRouting()
{
return(
  <div >
    <h1 >Audiophile Store</h1>
    <Router>
      <Link to="/">Home</Link> |<Link to="/about">about</Link> 
      |<Link to="/Items">Items</Link>
       |<Link to="/hello">Hello</Link>

      |<Link to="/login">Login</Link> 
      |<Link to="/register">Register</Link>
      |<Link to="/Admin">Admin</Link> 
     
      <hr/>
      <Switch> 
        <Route  exact path="/"><Home/></Route>
        <Route exact path="/about"><About/></Route>
        <Route exact path="/Items"><List/></Route>
        <Route exact path="/login"><Login/></Route>
        <Route exact path="/Register"><Register/></Route>
        <Route exact path="/bag"><Bag/></Route>
        <Route exact path="/Admin"><Admin/></Route>
        <Route exact path="/Addproduct"><AddProduct/></Route>
        <Route exact path="/ListUserComponent" component={ListProductComponent}/>
        <Route exact path="/hello"component={Hello}/>
        <Route exact path="/Goodbye"component={Goodbye}/>
        <Route path="/edit-product" component={EditUserComponent} />
      </Switch>
    </Router>
  </div>
)
}

function Home()
{
  return(
  <div sclass="container"  >
  <div className="jumbotron">
    <h1 >Audiophile Store</h1>
   
    <p>Taking your studio to the next level</p>
  </div>
  <div className="container">
  <div className="row">
    <div className="col-sm-4">
     <p>Hardware</p>
    </div>
    <div className="col-sm-4">
    <p>Software</p>
    </div>
    <div className="col-sm-4">
      <p>Acessories</p>
      
    </div>
  </div>
</div>
  
</div>

  )

}


export default App;
