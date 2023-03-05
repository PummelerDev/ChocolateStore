<#macro OrderInfo order>
    <h3>id: ${order.id}</h3>
    <h3>name: ${order.orderNumber!"default value"}</h3>
    <h3>productId: ${order.productId}</h3>
    <h3>customerId: ${order.customerId}</h3>
    <h3>quantity: ${order.quantity}</h3>
    <h3>created: ${order.created?iso_utc}</h3>
    <h3>changed: ${order.changed?iso_utc}</h3>
    <h3>cancelled: ${order.cancelled?string}</h3>
    <h3>finished: ${order.finished?string}</h3>
</#macro>