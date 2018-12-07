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
        <form method="POST">
            <div>
                <label for="month">Select Month</label><g:select name="month" optionKey="id" optionValue="test"
                from="${monthList}" value="${month}" />
                <label for="day">Select Day</label><g:select name="day" from="${1..31}" value="${day}" />
                <label for="month">Select Year</label><g:select name="year" from="${2017..2019}" value="${year}" />
                <input type="submit">
            </div>
        </form>
        <div id="empdList">
            <g:each in="${empList}" var='empd'>
                <div>
                    <label id="${empd.id}" for="workType_${empd.id}">Select Attendance for ${empd.emp.toString()}:</label>
                    <g:select from="${WorkType}" name="workType_${empd.id}" value="${empd.type}" />

                </div>
            </g:each>
        </div>
        <button id="saveBtn">Save</button>
    </body>
</html>
