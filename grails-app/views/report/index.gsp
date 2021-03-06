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
         <form method="GET">
             <input name="week" value="${week}" class="datepicker" data-date-format="mm/dd/yyyy">

             <select name="year">
                <option value="all" <g:if test="${currYear != yearS}">selected="selected"</g:if>>All</option>
                <option value="${yearS -2}">${yearS -2}</option>
                <option value="${yearS -1}">${yearS -1}</option>
                <option value="${yearS}" <g:if test="${currYear?.toString() == yearS?.toString()}">selected="selected"</g:if>>${yearS}</option>
                <option value="${yearS +1}">${yearS + 1}</option>
                <option value="${yearS +2}">${yearS + 2}</option>
            </select>
            <button id="goBtn" onclick="document.getElementsByTagName('form')[0].submit()">Submit</button>
         </form>
    <div class="table-responsive">
        <table class="table table-bordered table-striped">
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
    </div>
<g:javascript>
    $('.datepicker').datepicker({

        daysOfWeekDisabled: [1,2,3,4,5,6],
        daysOfWeekHighlighted: [0]
    });
</g:javascript>
</body>
</html>
