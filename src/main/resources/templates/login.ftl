<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>

<@p.page "login">
    <#if (param.error)??>
        <div>
            Invalid username and password.
        </div>
    <#elseif (param.logout)??>
        <div>
            You have been logged out.
        </div>
    </#if>
        <@l.login "/login" "Sign in"/>
        Click <a href="/registration">here </a> to add new user.
</@p.page>