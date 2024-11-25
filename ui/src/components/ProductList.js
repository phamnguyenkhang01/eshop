import { useState, useEffect } from "react";
import axios from "axios";


const ProductList = () => {
    const [products, setProducts] = useState([]);

    const url = process.env.REACT_APP_API_URL;

    useEffect(() => {
      axios.get(`${url}product/getall`).then((response) => {
        setProducts(response.data);
      });
    }, []);  

    const pList = products.map(product =>
      <li class="list-group-item d-flex justify-content-between align-items-center"
        key={product.id} style={{color: product.quantity === 0 ? 'red' : 'blue'}}>
        {product.description} ${product.price} {product.quantity === 0 ? 'Out of Stock' : product.quantity} 
        
        <div class="btn-group">
          
          <a class="btn btn-info" href={'product/'+product.id} role="button"><i class="bi bi-info-square"></i> Details</a>          
        </div>
      </li>
    );
    return (
      <>
        <div class="container">
          <ul class="list-group">
            <li class="list-group-teim d-flex justify-content-between align-items-center bg-success text-right"><h2>Product List</h2></li>
          {pList}
          </ul>
        </div>

      </>
  );  
}



export default ProductList;