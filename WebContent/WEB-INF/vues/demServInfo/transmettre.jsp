<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.DemServInf"%>
<%@page import="java.net.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% 

DemServInf dsi = (DemServInf)request.getAttribute("dsi");

response.sendRedirect("https://mail.cpasdeliege.be/?view=compose&body=" + URLEncoder.encode(dsi.getDescription(), "ISO-8859-1") + "&amp;subject=" + URLEncoder.encode(dsi.getTitre(), "ISO-8859-1")); 


%>

</body>
</html>