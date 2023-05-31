<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.codesRues.model.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="annuaire.css" type="text/css">
<script type="text/javascript">

</script>
</head>
<%
List<Addenda> listeRues = (List<Addenda>)request.getAttribute("listeRues");
%>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">répartition et code des rues de Liège</a>
<hr>
</div>
<h1 align="center">Répartition et code des rues de Liège</h1>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr>
		<td>
			<table align="center">
				<tr>
					<td class="menu_rues">
						<a href="index.rues">Toutes les antennes</a>
					</td>
					<td>
						&diams;
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=A07">Angleur</a>
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=A05">Bressoux</a>
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=A08">Grivegnée</a>
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=A02">Laveu</a>
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=A04">Nord</a>
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=A06">Outremeuse</a>
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=A01">Ste Marguerite</a>
					</td>
					<td>
						&diams;
					</td>
					<td class="menu_rues">
						<a href="index.rues?param=addenda">Addenda</a>
					</td>
				</tr>
			</table>	
			<table align="center" cellpadding="0" cellspacing="0">
				<tr height="25">
					<td width="22" background="images/annuaire/cadre/area_top_left.gif"></td>
					<td background="images/annuaire/cadre/area_top.gif"></td>
					<td width="22" background="images/annuaire/cadre/area_top_right.gif"></td>
				</tr>
				<tr>
					<td background="images/annuaire/cadre/area_left.gif"></td>
					<td>
						<table cellspacing="0">
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th>antenne</th>
							</tr>
							<%
								int i = 0;
								for(Addenda rue : listeRues) {
									i++;
							%>
							<% if(i%2 == 0) {  %>
								<tr class="ligne_paire">
							<% } else { %>
								<tr class="ligne_impaire">
							<% } %>
								<td class="liste_rues"><%= rue.getAbrevRue() %></td>
								<td class="liste_rues"><%= rue.getTypeRue() %></td>
								<td class="liste_rues">voir</td>
								<td class="liste_rues"><%= rue.getNomRue() %></td>
								<td class="liste_rues"><%= rue.getAntenne() %></td>
							</tr>
							<%
								}
							%>
						</table>					
					</td>
					<td background="images/annuaire/cadre/area_right.gif"></td>
				</tr>
				
				<tr height="25">
					<td background="images/annuaire/cadre/area_bottom_left.gif"></td>
					<td background="images/annuaire/cadre/area_bottom.gif"></td>
					<td background="images/annuaire/cadre/area_bottom_right.gif"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>