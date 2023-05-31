<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - liste des accès internet</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">liste des accès internet</a>
<hr>
</div>
<br>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/email.png" border="0" width="50px"></td>
					<td class="titre_tableau">Liste des accès internet</td>
					<td align="right">
						
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
					<th>Prénom</th>
					<th>Email</th>
					<th>mdp</th>
					<th>alias Email</th>
					<!-- <th>publilink</th>
					<th align="right">mdp</th>  -->
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listeInternet}" var="employe">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td style="font-size: 10pt"><a class="lien_tableau" href="gestionPersonnel.admin?nom=${employe.nom}&amp;prenom=${employe.prenom}">${employe.nom}</a></td>
						<td style="font-size: 10pt">${employe.prenom}</td>
						<td style="font-size: 10pt">${employe.email}</td>
						<td style="font-size: 10pt">${employe.mdpEmail}</td>
						<td style="font-size: 10pt">${employe.emailAlias}</td>
						<!-- <td style="font-size: 10pt">${employe.loginPublilink}</td>
						<td style="font-size: 10pt" align="right">${employe.mdpPublilink}</td>  -->
					</tr>
				</c:forEach>
				<c:forEach items="${listeEmailGenerique}" var="email">
					<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td style="font-size: 10pt"><a class="lien_tableau" href="gestionPersonnel.admin?nom=${employe.nom}&amp;prenom=${employe.prenom}">${employe.nom}</a></td>
						<td style="font-size: 10pt"></td>
						<td style="font-size: 10pt">${email.email}</td>
						<td style="font-size: 10pt">${email.mdp}</td>
						<td style="font-size: 10pt">${email.alias}</td>
						<!--  <td style="font-size: 10pt">${email.loginPublilink}</td> 
						<td style="font-size: 10pt" align="right">${email.mdpPublilink}</td> -->
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
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr height="25">
					<td>
						<form method="get" action="formulaireEmailGeneriqueAjouter.admin">
							<input type="submit" value="ajouter un email générique"/>
						</form>
					</td>
					<td align="right">
						<form method="get" action="supprimerEmailGenerique.admin">
							<select name=email size="1" />
							<option>selectionner l'email à supprimer</option>
							<c:forEach items="${listeEmailGenerique}" var="email">
								<option>${email.email}</option>
							</c:forEach>
							</select>
							<input type="submit" value="supprimer un email générique"/>
						</form>	
					</td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	
	
	
	
	
						
</table>
<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>