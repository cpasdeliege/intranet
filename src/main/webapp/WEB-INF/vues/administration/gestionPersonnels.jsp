<%@page import="java.util.Base64"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%--   <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> --%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion du personnel</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<!-- ********************************************************************************* -->
	<!--  Théo : 03/05/2021 -->
	<%!
	public static String urlEncode(String value, String charset) throws UnsupportedEncodingException {
	    return URLEncoder.encode(value, charset);
	}
	%> 
	<!-- ********************************************************************************** -->
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">gestion du personnel</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/personnel.png" border="0" width="50px"></td>
					<td class="titre_tableau">Liste du personnel (${statistiques.personnels})</td>
					<td align="right">
						<form method="get" action="formulairePersonnelAjouter.admin">
							<input type="submit" value="ajouter un employ&eacute;"/>
						</form>
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td width="22" background="images/administration/cadre/area_top_left.gif"></td>
		<td background="images/administration/cadre/area_top.gif"></td>
		<td width="22" background="images/administration/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/administration/cadre/area_left.gif"></td>
		<td>
		

			<table class="tableau_liste" cellspacing="0">
				<tr class="titre_colonne_tableau_liste">
					<th>Nom</th>
					<th>Pr&eacute;nom</th>
				</tr>
				<% int i = 0; %>
				<% String p=""; %>
				<c:forEach items="${listeEmployes}" var="employe">
				<% i++; %>
				<!-- ------------------------------------  -->
				<%-- <% String p <%= urlEncode(${employe.prenom} %>, "UTF-8")%>   --%>
			<%-- 	<jsp:useBean id="perso" class="be.cpasdeliege.intranet.informatique.model.Personnel"/>
					
			  		<jsp:setProperty name="perso" property="prenom" value="${employe.prenom}"/>
			  
			 		 <jsp:getProperty property="prenom" name="perso"/>
				 --%>
				
				
					<%-- 				p <%= Base64.encodeBase64(${employe.prenom}.getBytes()) %> --%>
					<%-- <c:set p="${employe.prenom}"></c:set>
					<c:out value="p"></c:out> --%>
				
				<!-- ---------------------------------------------  -->
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<!-- <td><a class="lien_tableau" href="gestionPersonnel.admin?nom=${employe.nom}&amp;prenom=${employe.prenom}">${employe.nom}</a></td>
						<td>${employe.prenom}   </td> -->
					
						
						 <td><a class="lien_tableau" href="gestionPersonnel.admin?nom=${employe.nom}&amp;prenom=${employe.prenom}">${employe.nom}</a></td>
						<!--<td><a class="lien_tableau" href="gestionPersonnel.admin?idPersonnel=${employe.nom}">${employe.nom}</a></td>-->
						<td>${employe.prenom} </td> 
								
						<td width="30px" align="right"><a href="supprimerPersonnelConfirmation.admin?nomPersonnel=${employe.nom}&amp;prenomPersonnel=${employe.prenom}"><img alt="supprimer" src="images/administration/supprimer.gif" border="0"></a></td>
					</tr>
				</c:forEach>
			</table>
			
			<!--  ******************************************************************************** -->
		 	<hr>
		 	<!-- *************************************
			<jsp:useBean id="perso"  scope="request" class="be.cpasdeliege.intranet.informatique.model.Personnel"/>
			
			<c:forEach items="${listeEmployes}" var="employe">
			   <jsp:setProperty name="perso" property="prenom" value="${employe.prenom}"/> 
		
		  	 <jsp:getProperty property="prenom" name="perso"/>  
		  	 
			</c:forEach>
			-->
			<!-- ************************************************************************************** -->
			
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<jsp:include page="../basDePage.jsp" ></jsp:include>

	
</body>
</html>