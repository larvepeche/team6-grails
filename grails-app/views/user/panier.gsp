<%--
  Created by IntelliJ IDEA.
  User: micha
  Date: 22/03/2022
  Time: 16:27
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="backoffice" />
    <title></title>
</head>

<body>
    <div class="container-fluid">
        <div class="new">
            <p>users</p>
            <button>+ Add new</button>
        </div>
        <section class="list-container">
            <div class="content">
                <table border = "1">
                    <thead>
                        <tr>
                            <th># Users</th>
                            <th>Username</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>
                                <div class="img-container">
                                    <asset:image class="pic" src="Michael.JPG" />
                                </div>
                                <span>Mariano</span>
                            </td>
                            <td><div class="wrapper">
                                <span class="mbtn Edit">Edit</span>
                                <span class="mbtn Delete">Delete</span>
                            </div></td>
                        </tr>
                    <tr>
                            <td>1</td>
                            <td>
                                <div class="img-container">
                                    <asset:image class="pic" src="Michael.JPG" />
                                </div>
                                <span>Mariano</span>
                            </td>
                            <td><div class="wrapper">
                                <span class="mbtn Edit">Edit</span>
                                <span class="mbtn Delete">Delete</span>
                            </div></td>
                        </tr>
                    <tr>
                        <td>1</td>
                        <td>
                            <div class="img-container">
                                <asset:image class="pic" src="Michael.JPG" />
                            </div>
                            <span>Mariano</span>
                        </td>
                        <td><div class="wrapper">
                            <span class="mbtn Edit">Edit</span>
                            <span class="mbtn Delete">Delete</span>
                        </div></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </section>
    </div>
</body>
</html>