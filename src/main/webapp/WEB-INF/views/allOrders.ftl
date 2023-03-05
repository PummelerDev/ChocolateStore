<#import "macros/mCommon.ftl" as com>
<#import "macros/mSingleOrderInfo.ftl" as soi>

<@com.common "all orders">
    <#list orders as order>
        <@soi.OrderInfo order/>
        <h4>________________________</h4>
    </#list>
</@com.common>