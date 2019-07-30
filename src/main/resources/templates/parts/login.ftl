<#macro login isRegistration>
    <form action="<#if isRegistration>/registration<#else>/login</#if>" method="post">

        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input id="username" class="form-control" type="text" name="username" placeholder="User Name"/>
            </div>
        </div>

        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input id="password" class="form-control" type="password" name="password" placeholder="Password"/>
            </div>
        </div>
        <#if isRegistration>
        <div class="form-group row">
            <label for="email" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input id="email" class="form-control" type="email" name="email" placeholder="some@some.com"/>
            </div>
        </div>
        </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary"><#if isRegistration>Registration<#else>Sign in</#if></button>
    </form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-light">Sign Out</button>
</form>
</#macro>