<#macro login path btnText>
    <form action="${path}" method="post">

        <div class="form_group row">
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
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary">${btnText}</button>
    </form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-light">Sign Out</button>
</form>
</#macro>