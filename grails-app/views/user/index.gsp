<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 22/03/2022
  Time: 16:27
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="backoffice"/>
    <title></title>
</head>

<body>
<div class="container-fluid">
    <div class="new">
        <p>users</p>
        <g:link class="create" action="create">Add New Users</g:link>
    </div>
    <section class="list-container">
        <div class="content">
            <table border="1">
                <thead>
                <tr>
                    <th># Users</th>
                    <th>Username</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${userList}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>
                            <g:if test="${user?.image}">
                                <div class="img-container">
                                    <img src="${resource(dir: "contact-image", file: "/${user.id}-${user.image}")}" class="img-thumbnail" style="margin-top: 10px; height: 100px; width: 100px;"/>
                                </div>
                            </g:if>
                            <span>${user.username}</span>
                        </td>
                        <td><div class="wrapper">
                            <span class="mbtn Edit">Edit</span>
                            <span><g:link class="mbtn Cart" action="validateCart" params="[id: user.id]">Validate Cart</g:link></span>
                            <span class="mbtn Delete">Delete</span>
                        </div></td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </section>
</div>
</body>
</html>