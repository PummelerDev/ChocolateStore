<#macro StorageInfo storage>
    <h3>id: ${storage.id}</h3>
    <h3>name: ${storage.name!"default value"}</h3>
    <h3>created: ${storage.created?iso_utc}</h3>
    <h3>changed: ${storage.changed?iso_utc}</h3>
</#macro>