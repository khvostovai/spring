<#import "./parts/page.ftl" as p>
<#import "./parts/login.ftl" as l>
<#include "./parts/security.ftl">

<@p.page "messages">
    <h5>${title}</h5>
    <#if messages??>
        <div>
            <#list messages as message>
                <div class="card my-3">
                    <div class="card-header">
                        <strong>${message.authorName}</strong> <i>${message.date?datetime}</i>
                    </div>
                    <div class="card-body">
                        ${message.text}
                    </div>
                    <div class="card-footer">
                        <#if isAdmin || currentUserID == message.author.id>
                            <form action="/delMessage" method="post">
                                <input type="submit" value="delete"/>
                                <input type="hidden" name="theme_id" value="${message.theme.id}">
                                <input type="hidden" name="message_id" value="${message.id}"/>
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            </form>
                        </#if>
                    </div>
                </div>
            </#list>
        </div>
    </#if>

    <form action="/addMessage" method="post">

        <div class="form-group row">
            <label for="text" class="col-sm-2 col-form-label">Enter your message:</label>
            <div class="col-sm-6">
                <input id="text" type="text" name="text" placeholder="message"
                       class="form-control ${(textError??)?string('is-invalid','')}"
                        <#if message??> value="${message.text}" </#if>
                />
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
        </div>

        <input type="hidden" name="theme_id" value="${theme_id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" name="addBtn">add</button>
    </form>

</@p.page>