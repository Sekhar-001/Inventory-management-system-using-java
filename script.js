function loadProducts(){

fetch("http://localhost:8080/products")
.then(res => res.json())
.then(data => {

let table = document.getElementById("productTable");

table.innerHTML = "";

data.forEach(p => {

table.innerHTML += `
<tr>
<td>${p.productId}</td>
<td>${p.name}</td>
<td>${p.quantity}</td>
<td>${p.price}</td>

<td>
<button class="update-btn" onclick="updateProduct(${p.productId})">
Update
</button>

<button class="delete-btn" onclick="deleteProduct(${p.productId})">
Delete
</button>
</td>

</tr>
`;

});

})
.catch(err => console.log(err));

}
function addProduct(){

let id=document.getElementById("id").value;
let name=document.getElementById("name").value;
let qty=document.getElementById("qty").value;
let price=document.getElementById("price").value;

fetch(`http://localhost:8080/addProduct?id=${id}&name=${name}&qty=${qty}&price=${price}`,{
method:"POST"
})
.then(res=>res.text())
.then(data=>{

alert(data);
loadProducts();

document.getElementById("id").value="";
document.getElementById("name").value="";
document.getElementById("qty").value="";
document.getElementById("price").value="";

});
}

function deleteProduct(id){

fetch(`http://localhost:8080/deleteProduct/${id}`,{
method:"DELETE"
})
.then(res=>res.text())
.then(data=>{

alert(data);
loadProducts();

});

}

function updateProduct(id){

let name = prompt("Enter new product name");
let qty = prompt("Enter new quantity");
let price = prompt("Enter new price");

if(!name || !qty || !price){
alert("Update cancelled");
return;
}

fetch(`http://localhost:8080/updateProduct?id=${id}&name=${name}&qty=${qty}&price=${price}`,{
method:"PUT"
})
.then(res=>res.text())
.then(data=>{
alert(data);
loadProducts();
})
.catch(err=>{
console.log(err);
alert("Update failed");
});

}

function searchProduct(){

let id=document.getElementById("searchId").value;

fetch(`http://localhost:8080/search/${id}`)
.then(res=>res.json())
.then(p=>{

let table=document.getElementById("productTable");

table.innerHTML=`
<tr>
<td>${p.productId}</td>
<td>${p.name}</td>
<td>${p.quantity}</td>
<td>${p.price}</td>
<td>-</td>
</tr>
`;

});

}

window.onload = loadProducts;