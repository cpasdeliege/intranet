<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="be.cpasdeliege.intranet.formations.model.Formation"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="cpas.css" type="text/css">
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="indexAdmin.formations">administration des formations</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">${ formation.intitule }</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="45px"><img src="images/formation.png" border="0" width="35px"></td>
					<td class="titre_tableau">${ formation.intitule }</td>
					<td align="right">
						<form method="get" action="formulaireModifier.formations">
							<input type="submit" value="modifier"/>
							<input type="hidden" name="idFormation" value="${ formation.idFormation }"/>
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
					<td align="right" style="font-weight: bold" valign="top"><nobr>typologie :&nbsp;&nbsp;</nobr></td>
					<td>${formation.typologie }</td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>objectif :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getObjectif().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>synthèse :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getSynthese().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>méthodologie :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getMethodologie().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>opérateur :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getOperateur().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>personne visée :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getPersonneVisee().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>rapport formation :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getRapportFormation().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>département :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getDepartement().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>service :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getService().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>durée :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getDuree().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>lieu :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getLieu().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>année :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getAnnee().replaceAll("\n", "<br>") %></td>
				</tr>
				<tr>
					<td align="right" style="font-weight: bold" valign="top"><nobr>rapport :&nbsp;&nbsp;</nobr></td>
					<td><%= ((Formation)request.getAttribute("formation")).getRapport().replaceAll("\n", "<br>") %></td>
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
		<td><a name="listeService"></a></td>
		<td>
			<table width="100%">
				<tr>
					<td class="titre_tableau"><a name="listeServices">Liste des services concernés par la formation</a></td>
					<td align="right">
						<form method="get" action="ajouterServiceFormation.formations">
							<select name="serviceAjouter" size="1" />
							<option> - choisir un service</option>
							<c:forEach items="${listeServices}" var="service">
								<option>${service.nom}</option>
							</c:forEach>
							</select>
							<input type="submit" value="ajouter"/>
							<input type="hidden" name="idFormation" value="${formation.idFormation }"/>
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
				
				<% int i = 0; %>
				<c:forEach items="${listeServicesFormation}" var="serviceFormation">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>${serviceFormation.service}</td>
						<td width="30px" align="right"><a href="supprimerServiceFormation.formations?service=${serviceFormation.service}&amp;idFormation=${serviceFormation.idFormation}"><img src="images/administration/supprimer.gif" border="0"></a></td>
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
	
	<tr height="25">
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>

<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>
