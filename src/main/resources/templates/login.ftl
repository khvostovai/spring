<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>

<@p.page "login">
    <#if message??>
        <div>
            ${message}
        </div>
    </#if>
        <@l.login false/>
        Click <a href="/registration">here </a> to add new user.
</@p.page>