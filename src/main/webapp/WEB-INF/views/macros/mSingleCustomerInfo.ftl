<#macro СustomerInfo customer >
    <h3>id: ${customer.id} </h3>
    <h3>firstName: ${customer.firstName!"default value"} </h3>
    <h3>lastName: ${customer.lastName!"default value"} </h3>
    <h3>address: ${customer.address!"default value"} </h3>
    <h3>phone: ${customer.phone!"default value"} </h3>
    <h3>email: ${customer.email!"default value"} </h3>
    <h3>purchaseAmount: ${customer.purchaseAmount} </h3>
    <h3>login: ${customer.login!"default value"} </h3>
    <h3>password: ${customer.password!"default value"} </h3>
    <h3>created: ${customer.created?iso_utc} </h3>
    <h3>changed: ${customer.changed?iso_utc} </h3>
    <h3>isDeleted: ${customer.deleted?string} </h3>
</#macro>