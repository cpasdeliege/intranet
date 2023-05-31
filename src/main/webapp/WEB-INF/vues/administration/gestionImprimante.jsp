<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des imprimantes</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionImprimantes.admin">gestion des imprimantes</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel"> ${imprimante.numeroSerie } </a>
<hr>
</div>
<br>
<%
List listeOrdi = (List)request.getAttribute("listeOrdinateur");
%>
<%
int width = 40;
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="45px"><img src="images/administration/imprimante.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau">${imprimante.numeroSerie }</td>
					<td align="right">
						<form method="get" action="formulaireImprimanteModifier.admin">
							<input type="submit" value="modifier l'imprimante"/>
							<input type="hidden" name="numeroS" value="${imprimante.numeroSerie }"/>
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
				<tr>
					<td>
					<table>
							<tr>
								<td align="right">type : </td>
								<td class="texte_cadre_gestion">${imprimante.type }</td>
							</tr>
							<tr>
								<td align="right">service : </td>
								<td class="texte_cadre_gestion" ><a class="lien_tableau" href="gestionService.admin?service=${service}#imprimantesLocale">${service }</a></td>
							</tr>
							<tr>
								<td align="right">ordinateur local : </td>
								<td class="texte_cadre_gestion"><a class="lien_tableau" href="gestionOrdinateur.admin?ordinateur=${imprimante.ordinateurLocal }#imprimantesLocale">${imprimante.ordinateurLocal }</a></td>
							</tr>
						</table>
					</td>
					<td align="right">
						<table>
							<tr>
								<td>
									<%	if(listeOrdi.size() > 1) { %>														
											<%= listeOrdi.size() %> ordinateurs connectés
									<%	} else { %>
											<%= listeOrdi.size() %> ordinateur connecté
									<%	} %>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
		<td background="images/administration/cadre/area_right.gif"></td>
	</tr>
	<tr height="25">
		<td background="images/administration/cadre/area_bottom_left.gif"></td>
		<td background="images/administration/cadre/area_bottom.gif"></td>
		<td background="images/administration/cadre/area_bottom_right.gif"></td>
	</tr>
	<tr height="25">
		<td></td>
		<td>
			
		</td>
		<td></td>
	</tr>
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="45px"><img src="images/administration/ordinateur.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="listeOrdinateur">Liste des ordinateurs connectés</a></td>
					<td align="right">
						<form method="get" action="formulaireImprimanteOrdinateurAssigner.admin">
							<input type="submit" value="assigner à un ordinateur"/>
							<input type="hidden" name="numeroS" value="${imprimante.numeroSerie }"/>
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
					<th>Ordinateur</th>
					<th></th>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listeOrdinateur}" var="ordi">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionOrdinateur.admin?ordinateur=${ordi.ordinateur}#imprimantesReseau">${ordi.ordinateur}</a></td>
						<td width="30px" align="right" onclick=""><a href="desassignerImprimanteOrdinateur.admin?ordinateur=${ordi.ordinateur}&amp;numeroSerie=${imprimante.numeroSerie }&amp;ancre=listeOrdinateur"><img src="images/administration/supprimer.gif" border="0"></a></td>
					</tr>
				</c:forEach>
			</table>			
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