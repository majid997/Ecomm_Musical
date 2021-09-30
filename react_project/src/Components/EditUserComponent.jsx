import React, { Component } from 'react'
import ApiProService from '../service/ApiProService';
import ApiService from '../service/ApiService';
class EditUserComponent extends Component {

    constructor(props){
        super(props);
        this.state ={
            id: '',
            title: '',
            description: '',
            unitprice: '',
            status: '',
        }
        this.saveProduct = this.saveProduct.bind(this);
        this.loadProduct = this.loadProduct.bind(this);
    }

    componentDidMount() {
        this.loadProduct();
    }

    loadProduct() {
        ApiProService.fetchProductById(window.localStorage.getItem("proId"))
            .then((res) => {
                let product = res.data.result;
                this.setState({
                id: product.id,
                title: product.title,
                description: product.description,
                unitprice: product.unitprice,
                status: product.status,
                
                })
            });
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    saveProduct = (e) => {
        e.preventDefault();
        let user = {id: this.state.id, title: this.state.title, description: this.state.description, unitprice: this.state.unitprice, status: this.state.status};
        ApiProService.editProduct(user)
            .then(res => {
                this.setState({message : 'User details updated successfully.'});
                this.props.history.push('/AddProduct');
            });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Edit Product</h2>
                <form>

                    <div className="form-group">
                        <label>title:</label>
                        <input type="text" placeholder="title" name="title" className="form-control" readonly="true" defaultValue={this.state.title}/>
                    </div>

                    <div className="form-group">
                        <label>description:</label>
                        <input placeholder="description" name="description" className="form-control" value={this.state.description} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>unitprice:</label>
                        <input placeholder="unitprice" name="unitprice" className="form-control" value={this.state.unitprice} onChange={this.onChange}/>
                    </div>

                    <div className="form-group">
                        <label>status:</label>
                        <input type="status" placeholder="status" name="status" className="form-control" value={this.state.status} onChange={this.onChange}/>
                    </div>

                    

                    <button className="btn btn-success" onClick={this.saveProduct}>Update</button>
                </form>
            </div>
        );
    }
}

export default EditUserComponent;