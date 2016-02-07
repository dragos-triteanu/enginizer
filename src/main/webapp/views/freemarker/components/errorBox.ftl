<#macro renderErrors errors=''>
    <#if errors?has_content>
        <#list errors as error>
            <div class="alert alert-danger">
                <strong>Danger!</strong> ${error}
            </div>
        </#list>
    </#if>
</#macro>