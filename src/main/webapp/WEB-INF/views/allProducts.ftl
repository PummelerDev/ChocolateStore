<#import "macros/mCommon.ftl" as com>
<#import  "macros/mSingleProductInfo.ftl" as spi>

<@com.common "all products">
    <#list products as product>
        <@spi.ProductInfo product/>
        <h4>________________________</h4>
    </#list>
</@com.common>