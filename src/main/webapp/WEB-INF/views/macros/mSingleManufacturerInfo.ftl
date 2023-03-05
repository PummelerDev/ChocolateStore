<#macro ManufacturerInfo manufacturer>
    <h3>id: ${manufacturer.id}</h3>
    <h3>name: ${manufacturer.name!"default value"}</h3>
    <h3>created: ${manufacturer.created?iso_utc}</h3>
    <h3>changed: ${manufacturer.changed?iso_utc}</h3>
</#macro>