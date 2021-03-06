<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}" type="image/x-icon">
        <link rel="apple-touch-icon" href="${assetPath(src: 'apple-touch-icon.png')}">
        <link rel="apple-touch-icon" sizes="114x114" href="${assetPath(src: 'apple-touch-icon-retina.png')}">
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="new_app.js"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/js/bootstrap-datepicker.min.js" type="application/javascript" ></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.8.0/css/bootstrap-datepicker3.standalone.css" type="text/css" />
    <g:layoutHead/>
</head>
<body>
<span id="logoutLink" style="display:none">
    <g:link elementId='_logout' controller='logout'/>
    <a href="${request.contextPath}${securityConfig.logout.afterLogoutUrl}" id="_afterLogout"></a>
</span>
<nav class="navbar navbar-expand-lg navbar-dark navbar-static-top" role="navigation">
    <a class="navbar-brand" href="${request.contextPath}/#">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContent" aria-controls="navbarContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" aria-expanded="false" style="height: 0.8px;" id="navbarContent">
        <ul class="nav navbar-nav ml-auto">
            <g:pageProperty name="page.nav"/>

        <sec:ifLoggedIn>
            <li class="nav-item dropdown-item"><g:link class="nav-link" elementId="logout" controller="logout"><g:message code='spring.security.ui.login.logout'/></g:link></li>

        </sec:ifLoggedIn>
        </ul>
    </div>

</nav>

<g:layoutBody/>

<div class="footer" role="contentinfo"></div>


<div id="spinner" class="spinner" style="display:none;">
    <g:message code="spinner.alt" default="Loading&hellip;"/>
</div>

<asset:javascript src="application.js"/>
<script>

        function logout(event) {
            event.preventDefault();
            $.ajax({
                url: $("#_logout").attr("href"),
                method: "post",
                success: function (data, textStatus, jqXHR) {
                    window.location = $("#_afterLogout").attr("href");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    console.log("Logout error, textStatus: " + textStatus + ", errorThrown: " + errorThrown);
                }
            });
        }
        $(document).ready(function() {
            $("#logout").click(logout);
        });
</script>
</body>
</html>
