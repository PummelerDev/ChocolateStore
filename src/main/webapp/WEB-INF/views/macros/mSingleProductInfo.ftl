<#macro ProductInfo product>
    <h3>id: ${product.id}</h3>
    <h3>kind: ${product.kind!"default value"}</h3>
    <h3>topping: ${product.topping!"default value"}</h3>
    <h3>manufacturerId: ${product.manufacturerId}</h3>
    <h3>name: ${product.name!"default value"}</h3>
    <h3>description: ${product.description!"default value"}</h3>
    <h3>weight: ${product.weight}</h3>
    <h3>price: ${product.price}</h3>
    <h3>created: ${product.created?iso_utc}</h3>
    <h3>changed: ${product.changed?iso_utc}</h3>
</#macro>