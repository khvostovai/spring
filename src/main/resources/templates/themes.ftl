<#import "./parts/page.ftl" as p>
<#include "./parts/security.ftl">
<@p.page "themes">
    <div class="text-center mb-3">
        <button class="btn btn-primary" data-toggle="collapse" data-target="#formAddTheme">add new theme</button>
    </div>
    <div class="collapse ${(theme??)?string('show','')}" id="formAddTheme">
        <form method="post" action="/themes">

            <div class="form-group row">
                <label for="title" class="col-sm-3 col-form-label">Title of theme</label>
                <div class="col-sm-8">
                    <input id="title" type="text" name="title"
                        class="form-control ${(titleError??)?string('is-invalid','')}"
                        <#if theme?? >
                            value="${theme.title}"
                        </#if>
                />
                <#if titleError??>
                    <div class="invalid-feedback">
                        ${titleError}
                    </div>
                </#if>
                </div>
            </div>

            <div class="form-group row">
                <label for="description" class="col-sm-3 col-form-label">Description of theme</label>
                <div class="col-sm-8">
                    <input id="description" type="text" name="description"
                        class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                        <#if theme?? >
                            value="${theme.description}"
                        </#if>
                    />
                    <#if descriptionError??>
                        <div class="invalid-feedback">
                            ${descriptionError}
                        </div>
                    </#if>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit">Create</button>
        </form>
    </div>

    <div class="text-center mt-3"><h5>List of themes</h5></div>
    <#if themes??>
        <#list themes as theme>
            <div class="card mt-3" style="cursor: pointer;" onclick="window.location='/theme/${theme.id}'">
                <div class="card-header">
                    <h6 class="card-title">${theme.title}</h6>
                </div>
                <div class="card-body">
                    <p class="card-text">${theme.description}</p>
                    <h6 class="card-subtitle">Author: <i>${theme.author.username}</i></h6>
                    <h6 class="card-subtitle mt-1">Created: <i>${theme.date?datetime}</i></h6>
                </div>
                <div class="card-footer">
                    <#if isAdmin || currentUserID == theme.author.id>
                        <form method="post" action="/delTheme">
                            <input type="hidden" name="theme_id" value="${theme.id}"/>
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button class="btn btn-link">Delete</button>
                        </form>
                    </#if>
                </div>
            </div>
        </#list>
    </#if>
</@p.page>