<#import "macros/mCommon.ftl" as com>
<#import "macros/mSingleManufacturerInfo.ftl" as smi>

<@com.common "all manufacturers">
    <#list manufacturers as manufacturer>
        <@smi.ManufacturerInfo manufacturer/>
        <h4>________________________</h4>
    </#list>
</@com.common>


