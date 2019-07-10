<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>
<@p.page "messages">
    <@l.logout/>

    <form action="/addMessage" method="post">
        сообщение:
        <input name="message" type="text"/>
        тег:
        <input name="tag" type="text"/>
        <input type="hidden" name="theme_id" value="${theme_id}">
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
                        <p><strong>${message.authorName}</strong> <i>${message.date}</i></p>
                        <form action="/delMessage" method="post">
                            <input type="submit" value="delete"/>
                            <input type="hidden" name="theme_id" value="${message.theme.id}">
                            <input type="hidden" name="message_id" value="${message.id}"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </form>
                    </li>
                </#list>
            </ul>
        </div>
    </#if>

</@p.page>