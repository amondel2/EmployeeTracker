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
        <title>Index</title>
    </head>
    <body>
        <table>
            <thead>
            <th>Name</th>
                <g:each in="${wts}" var='wt'>
                <th>${wt}</th>
                </g:each>
        </thead>
        <tbody>
            <g:each in="${data}" var='item'>
                <tr>
                    <td><g:link controller="Report" action="show" id="${item.key}">${idToName[item.key]}</g:link></td>
                    <g:each in="${item.value}" var='itm'>
                        <td>${itm}</td>
                    </g:each>
                </tr>
            </g:each>
        </tbody>
    </table>

</body>
</html>
