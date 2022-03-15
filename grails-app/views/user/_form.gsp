<div class="form-group">
    <label>Username *</label>
    <g:textField name="username" class="form-control" value="${user?.username}" placeholder="Please Enter Contact Name"/>
%{--    <UIHelper:renderErrorMessage fieldName="name" model="${user}" errorMessage="please.enter.name"/>--}%
</div>

<div class="form-group">
%{--    <label><g:message code="image"/></label>--}%
    <g:field name="contactImage" class="form-control" type="file" placeholder="Please Upload Image"/>
    <g:if test="${user?.image}">
        <img src="${resource(dir: "contact-image", file: "/${user.id}-${user.image}")}" class="img-thumbnail" style="margin-top: 10px; height: 100px; width: 100px;"/>
    </g:if>
</div>