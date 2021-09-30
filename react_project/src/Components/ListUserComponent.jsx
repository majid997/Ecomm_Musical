import React, { Component } from 'react'
import ApiProService from '../service/ApiProService';
import ApiService from "../service/ApiProService";

class ListProductComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            products: [],
            message: null
        }
        this.deleteProduct = this.deleteProduct.bind(this);
        this.editProduct = this.editProduct.bind(this);
        this.addProduct = this.addProduct.bind(this);
        this.reloadProList = this.reloadProList.bind(this);
    }

    componentDidMount() {
        this.reloadProList();
    }

    reloadProList() {
        ApiProService.fetchProducts()
            .then((resp) => {
                this.setState({products: resp.data})
                console.log(this.state.products);
            });
            // UserService.getUsers().then(resp => {
            //     this.setState({ users: resp.data });
            //     console.log(this.state.users);
            // })
    }

    deleteProduct(proId) {
        ApiService.deleteProduct(proId)
           .then(res => {
               this.setState({message : 'User deleted successfully.'});
               this.setState({products: this.state.products.filter(products => products.id !== proId)});
           })

    }

    editProduct(id) {
        window.localStorage.setItem("proId", id);
        this.props.history.push('/edit-product');
    }

    addProduct() {
        window.localStorage.removeItem("proId");
        this.props.history.push('/add-product');
    }

    render() {
        return (
            <div>
                <h2 className="text-center">User Details</h2>
                <button className="btn btn-danger" style={{width:'100px'}} onClick={() => this.addProduct()}> Add User</button>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th className="hidden">Id</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Unitprice</th>
                            <th>Status</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.products.map(
                        products =>
                                    <tr key={products.id}>
                                        <td>{products.title}</td>
                                        <td>{products.description}</td>
                                        <td>{products.unitprice}</td>
                                        <td>{products.status}</td>
                                       
                                        <td>
                                            <button className="btn btn-success" onClick={() => this.deleteProduct(products.id)}> Delete</button>
                                            <button className="btn btn-success" onClick={() => this.editProduct(products.id)} style={{marginLeft: '20px'}}> Edit</button>
                                        </td>
                                    </tr>
                            )
                        }
                    </tbody>
                </table>

            </div>
        );
    }

}

export default ListProductComponent;