<#import "./parts/page.ftl" as p>

<@p.page "themes">
    <form method="post">
        add new Theme
        <input type="text" name="title">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="submit">
    </form>
    <#if themes??>
        <#list themes as theme>
            <div>
                <h5>${theme.title}</h5>
                <span>${theme.author.username}</span>
                <a href="/theme/${theme.id}">show</a>
            </div>
        </#list>
    </#if>
</@p.page>