<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Li�ge - administration </title>
</head>
<body class="body">
<jsp:include page="entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>

<hr>
</div>
<br>
		<div align="center">
		<p>${exception.message}</p>
		
		<a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
		</div>
		
<jsp:include page="basDePage.jsp" ></jsp:include>
</body>
</html>