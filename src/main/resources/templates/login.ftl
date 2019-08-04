<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>

<@p.page "login">
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION}
        </div>
    </#if>
        <@l.login false/>
</@p.page>