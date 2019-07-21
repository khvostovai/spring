<#import "./parts/page.ftl" as p>
<#include "./parts/security.ftl">
<@p.page "themes">
    <div class="text-center mb-3">
        <button class="btn btn-primary" data-toggle="collapse" data-target="#formAddTheme">add new theme</button>
    </div>
    <dim class="collapse" id="formAddTheme">
        <form method="post">
            <div class="form-group row">
                <label for="title" class="col-sm-3 col-form-label">Title of theme</label>
                <div class="col-sm-8">
                <input id="title" class="form-control" type="text" name="title"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="description" class="col-sm-3 col-form-label">Description of theme</label>
                <div class="col-sm-8">
                    <input id="description" class="form-control" type="text" name="description"/>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-primary" type="submit">Create</button>
        </form>
    </dim>

    <div class="text-center mt-3"><h5>List of themes</h5></div>
    <#if themes??>
        <#list themes as theme>
            <div class="card mt-3">
                <div class="card-header">
                    <h6 class="card-title">${theme.title}</h6>
                </div>
                <div class="card-body">
                    <p class="card-text">${theme.description}</p>
                    <h6 class="card-subtitle">Author: <i>${theme.author.username}</i></h6>
                    <h6 class="card-subtitle mt-1">Created: <i>${theme.date}</i></h6>
                </div>
                <div class="card-footer">
                    <a href="/theme/${theme.id}" class="btn-link">Read</a>
                    <#if isAdmin>
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