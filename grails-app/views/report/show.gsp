<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->
<%@ page import="com.attend.WorkType" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main_1"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show</title>
    </head>
    <body>
        <h1>${emp.toString()} </h1>
        <h3>Normal: ${nom_normal} Non Normal: ${non_normie}</h3>
        <g:each in="${wts}" var='item'>
            <ul>
                <li>${item.key} ${item?.value?.size()}
                    <ul>
                        <g:each in="${item.value}" var='itm'>
                            <li>
                                <g:formatDate format="MM-dd-yyyy" date="${itm}"/>
                            </li>
                        </g:each>
                    </ul></li>
            </ul>
        </g:each>
    </body>
</html>