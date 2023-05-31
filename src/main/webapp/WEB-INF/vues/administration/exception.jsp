<%--><%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration </title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>

<hr>
</div>
<br>
		<div align="center">
		<p>${fn:escapeXml(exception.message)}</p>
		
		<a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a>
		</div>
		
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>