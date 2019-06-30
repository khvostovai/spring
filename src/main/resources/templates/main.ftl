<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>
<@p.page "main">
    <@l.logout/>

    <form action="/main" method="post" name="addMessage">
        сообщение:
        <input name="message" type="text"/>
        тег:
        <input name="tag" type="text"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" name="addBtn">добавить</button>
    </form>

    <#if messages??>
        <div>
            <p>список сообщений:</p>
            <ul>
                <#list messages as message>
                    <li>
                        <span>${message.text}</span>
                        <i>${message.tag}</i>
                        <strong>${message.authorName}</strong>
                    </li>
                </#list>
            </ul>
        </div>
    </#if>

</@p.page>