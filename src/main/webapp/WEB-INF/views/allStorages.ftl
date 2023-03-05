<#import "macros/mCommon.ftl" as com>
<#import "macros/mSingleStorageInfo.ftl" as ssi>

<@com.common "storage">
    <#list storages as storage>
        <@ssi.StorageInfo storage/>
        <h4>________________________</h4>
    </#list>
</@com.common>