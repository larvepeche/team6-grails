<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Backoffice"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="backoffice.css"/>
    <g:layoutHead/>
</head>

<body>
    <nav class="left-nav">
        <div class="trademark">
            <asset:image class="logo" src="SVGs/Fill/apple-logo-fill.svg" />
            <div class="title">Lamba™</div>
        </div>
        <div class="section ${session.active == 'User' ? 'active': ''}"><a href="${createLink(uri: '/backoffice/users')}" class="img-container"><asset:image src="SVGs/Regular/users.svg" /></a><span><g:message code="backoffice.users" /></span></div>
        <div class="section ${session.active == 'Product' ? 'active': ''}"><a href="${createLink(uri: '/backoffice/products')}" class="img-container"><asset:image src="SVGs/Regular/t-shirt.svg" /></a><span><g:message code="backoffice.products" /></span></div>
        <div class="section ${session.active == 'Banner' ? 'active': ''}"><a href="${createLink(uri: '/backoffice/banners')}" class="img-container"><asset:image src="SVGs/Regular/image.svg" /></a><span><g:message code="backoffice.banners" /></span></div>
    </nav>

    <div>
        <div class="prof-search">
                <form class="searchbar smooth-shadow">
                    <g:textField name="searchInput" placeholder="Search" />
                    <div class="searchBtnContainer">
                        <asset:image class="searchBtn" src="SVGs/Regular/magnifying-glass.svg" />
                    </div>
                </form>

            <sec:ifLoggedIn>
                <div class="profile smooth-shadow">
                    <g:if test="${curUser.image != null}">
                        <div class="img-container">
                            <img src="${resource(dir: "contact-image", file: "/${curUser.id}-${curUser.image}")}" class="pic"/>
                        </div>
                    </g:if>
%{--                    <asset:image class="pic" src="Michael.JPG" />--}%
                    <span>${sec.loggedInUserInfo(field: 'username')}</span>
                    <asset:image class="caret-down" src="SVGs/Regular/caret-down.svg" />
                </div>
            </sec:ifLoggedIn>

        </div>

        <g:layoutBody/>
    </div>




<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
