<#import "./parts/page.ftl" as p>

<@p.page "themes">
    <form method="post">
        add new Theme
        <input type="text" name="title"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="submit"/>
    </form>
    <#if themes??>
        <#list themes as theme>
            <div>
                <h5>${theme.title}</h5>
                <span>${theme.author.username} ${theme.date}</span>
                <a href="/theme/${theme.id}">show</a>
                <form method="post" action="/delTheme">
                    <input type="submit" value="delete"/>
                    <input type="hidden" name="theme_id" value="${theme.id}"/>
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </form>
            </div>
        </#list>
    </#if>

    <#if countMessages??>
        <h6>count of messages=${countMessages}</h6>
    </#if>
</@p.page>