<#import "macros/mCommon.ftl" as com>
<#import "macros/mSingleCustomerInfo.ftl" as sci>

<@com.common "all customers">
    <#list customers as customer>
        <@sci.СustomerInfo customer/>
        <h4>________________________</h4>
    </#list>
</@com.common>
