<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>
<@p.page "messages">
    <#if messages??>
        <div>
            <h5>список сообщений:</h5>
            <#list messages as message>
                <div class="card my-3">
                    <div class="card-header">
                        <strong>${message.authorName}</strong> <i>${message.date}</i>
                    </div>
                    <div class="card-body">
                        ${message.text}
                    </div>
                    <div class="card-footer">
                        <form action="/delMessage" method="post">
                            <input type="submit" value="delete"/>
                            <input type="hidden" name="theme_id" value="${message.theme.id}">
                            <input type="hidden" name="message_id" value="${message.id}"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
            </#list>
        </div>
    </#if>

    <form action="/addMessage" method="post">
        сообщение:
        <input name="message" type="text"/>
        тег:
        <input name="tag" type="text"/>
        <input type="hidden" name="theme_id" value="${theme_id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" name="addBtn">добавить</button>
    </form>

</@p.page>