<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>

<@p.page "Registration">
        Add new user
    <#if message??>
        <div>
            ${message}
        </div>
    </#if>
        <@l.login "/registration" "registration"/>
</@p.page>