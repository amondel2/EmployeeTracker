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
        <style type="text/css">
            ul {
                padding-left: 40px;
            }
        </style>
    </head>
    <body>
    <form method="GET">
        <input name="week" value="${week}" class="datepicker" data-date-format="mm/dd/yyyy">
        <select name="year">
            <option value="" <g:if test="${currYear != yearS}">selected="selected"</g:if>>All</option>
            <option value="${yearS -2}">${yearS -2}</option>
            <option value="${yearS -1}">${yearS -1}</option>
            <option value="${yearS}" <g:if test="${currYear?.toString() == yearS?.toString()}">selected="selected"</g:if>>${yearS}</option>
            <option value="${yearS +1}">${yearS + 1}</option>
            <option value="${yearS +2}">${yearS + 2}</option>
        </select>
        <button id="goBtn" onclick="document.getElementsByTagName('form')[0].submit()">Submit</button>
    </form>
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
    <g:javascript>
        $('.datepicker').datepicker({

            daysOfWeekDisabled: [1,2,3,4,5,6],
            daysOfWeekHighlighted: [0]
        });
    </g:javascript>
    </body>
</html>