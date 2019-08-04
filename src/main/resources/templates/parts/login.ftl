<#macro login isRegistration>
    <form action="<#if isRegistration>/registration<#else>/login</#if>" method="post">

        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input id="username" type="text" name="username" placeholder="User Name"
                    class="form-control ${(usernameError??)?string('is-invalid','')}"
                    value="<#if user??>${user.username}</#if>"
                />
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>

        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input id="password" type="password" name="password" placeholder="Password"
                    class="form-control ${(passwordError??)?string('is-invalid', '')}"
                    />
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegistration>
            <div class="form-group row">
                <label for="password2" class="col-sm-2 col-form-label"></label>
                <div class="col-sm-6">
                    <input id="password2" type="password" name="password2" placeholder="Retype password"
                           class="form-control ${(password2Error??)?string('is-invalid', '')}"
                    />
                    <#if password2Error??>
                        <div class="invalid-feedback">
                            ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-6">
                    <input id="email" type="email" name="email" placeholder="some@some.com"
                         class="form-control ${(emailError??)?string('is-invalid','')}"
                         value="<#if user??>${user.email}</#if>"
                    />
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary"><#if isRegistration>Registration<#else>Sign in</#if></button>
        <#if !isRegistration>
            <div>
                Click <a href="/registration">here </a> to add new user.
            </div>
        </#if>
    </form>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button class="btn btn-light">Sign Out</button>
</form>
</#macro>