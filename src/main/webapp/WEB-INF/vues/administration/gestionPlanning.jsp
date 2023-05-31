<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion du planning</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">gestion du planning</a>
<hr>
</div>
<br>



<%
	List tmpMesTaches = (List)request.getAttribute("listeMesTaches");
	int nbreMesTache = tmpMesTaches.size();
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td colspan="3" align="right"><a href="gestionPlanning.admin?action=incidents">voir les incidents</a></td>
					<td colspan="3" align="right"><a href="gestionPlanning.admin?csv=1" href="#">CSV</a></td>
				</tr>
				<tr>
					<td width="60px"><img src="images/katuberling.png" border="0" width="50px"></td>
					<td class="titre_tableau">${titreMesTaches} (<%= nbreMesTache %>)
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span style="color: black;font-size: small;">
					<c:forEach items="${listePersInfo}" var="pers">
						<a href="gestionPlanning.admin?tachePerso=${pers.nom}">${pers.nom}</a> - 
					</c:forEach>
					</span>
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="lien_tableau" href="index.dsi"> - DSI - </a> </td>
					<td align="right">
						<form method="get" action="formulaireTacheAjouter.admin">
							<input type="submit" value="ajouter une tâche"/>
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
					<th></th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=idPlanning">num&nbsp;</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=ordinateur">ordinateur</a></th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=service">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=echeance">echéance</a></th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=nomInfo">assigné à</a></th>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listeMesTaches}" var="tache">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>
							<c:choose>
								<c:when test="${tache.incident == '1'}">
									<img src="images/administration/incident.png" border="0" width="20px">
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose>						
						</td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}" name="${tache.idPlanning}">${tache.idPlanning}</a></td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}">${tache.titre}</a></td>
						<td>
							<c:choose>
								<c:when test="${tache.ordinateur == '-'}">
									
								</c:when>
								<c:otherwise>
									${tache.ordinateur}
								</c:otherwise>
							</c:choose>
						
						</td>
						<td><span>${tache.service}</span></td>
						<td>${tache.nom}</td>
						<td>${tache.echeance}</td>
						<c:choose>
                            <c:when test="${tache.dsi == true}">
                                <td align="right" style="color: red;">${tache.nomInfo}</td>
                            </c:when>
                            <c:otherwise>
                                <td align="right">${tache.nomInfo}</td>
                            </c:otherwise>
                        </c:choose>
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	<%
		List tmp = (List)request.getAttribute("listeTacheEnCours");
		int nbreTache = tmp.size();
	%>
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/tache.png" border="0" width="50px"></td>
					<td class="titre_tableau">Liste des tâches à effectuer (<%= nbreTache %>)</td>
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
					<th></th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=idPlanning">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=ordinateur">ordinateur</a></th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=service">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=echeance">echéance</a></th>
					<th align="center"><a href="gestionPlanning.admin?triEnCours=nomInfo">assigné à</a></th>
				</tr>
				<% i = 0; %>
				<c:forEach items="${listeTacheEnCours}" var="tache">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>
							<c:choose>
								<c:when test="${tache.incident == '1'}">
									<img src="images/administration/incident.png" border="0" width="20px">
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose>						
						</td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}" name="${tache.idPlanning}">${tache.idPlanning}</a></td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}">${tache.titre}</a></td>
						<td>
							<c:choose>
								<c:when test="${tache.ordinateur == '-'}">
									
								</c:when>
								<c:otherwise>
									${tache.ordinateur}
								</c:otherwise>
							</c:choose>
						
						</td>
						<td><span>${tache.service}</span></td>
						<td>${tache.nom}</td>
						<td>${tache.echeance}</td>
						<c:choose>
                            <c:when test="${tache.dsi == true}">
                                <td align="right" style="color: red;">${tache.nomInfo}</td>
                            </c:when>
                            <c:otherwise>
                                <td align="right">${tache.nomInfo}</td>
                            </c:otherwise>
                        </c:choose>
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
	
	
	
	
	
	
	
	
	
	
	
	
	<%
	List tmp3 = (List)request.getAttribute("listeTacheEnAttente");
	int nbreTache3 = tmp3.size();
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/kthememgr.png" border="0" width="50px"></td>
					<td class="titre_tableau"><a name="enAttente">Liste des tâches en attente (<%= nbreTache3 %>)</a></td>
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
					<th></th>
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=idPlanning#enAttente">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=ordinateur#enAttente">ordinateur</a></th>
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=service#enAttente">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=echeance#enAttente">echéance</a></th>
				</tr>
				<% int i3 = 0; %>
				<c:forEach items="${listeTacheEnAttente}" var="tache">
				<% i3++; %>
				<% if(i3%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>
							<c:choose>
								<c:when test="${tache.incident == '1'}">
									<img src="images/administration/incident.png" border="0" width="20px">
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose>						
						</td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}" name="${tache.idPlanning}">${tache.idPlanning}</a></td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}">${tache.titre}</a></td>
						<td>
							<c:choose>
								<c:when test="${tache.ordinateur == '-'}">
									
								</c:when>
								<c:otherwise>
									${tache.ordinateur}
								</c:otherwise>
							</c:choose>
						
						</td>
						<td>${tache.service}</td>
						<td>${tache.nom}</td>
						<td>${tache.echeance}</td>
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
	
<%
	List tmp2 = (List)request.getAttribute("listeTachesFinies");
	int nbreTFinie= tmp2.size();
%>
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/tacheFinie.png" border="0" width="50px"></td>
					<td class="titre_tableau"><a name="fini">Liste des tâches effectuées (<%= nbreTFinie %>)</a></td>
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
					<th></th>
					<th align="center"><a href="gestionPlanning.admin?triEnFini=idPlanning#fini">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPlanning.admin?triEnFini=ordinateur#fini">ordinateur</a></th>
					<th align="center"><a href="gestionPlanning.admin?triEnFini=service#fini">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionPlanning.admin?triEnFini=dateFin#fini">date fin</a></th>
				</tr>
				<% i = 0; %>
				<c:forEach items="${listeTachesFinies}" var="tache">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>
							<c:choose>
								<c:when test="${tache.incident == '1'}">
									<img src="images/administration/incident.png" border="0" width="20px">
								</c:when>
								<c:otherwise>
									
								</c:otherwise>
							</c:choose>						
						</td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}"  name="${tache.idPlanning}">${tache.idPlanning}</a></td>
						<td><a class="lien_tableau" href="gestionTache.admin?idPlanning=${tache.idPlanning}">${tache.titre}</a></td>
						<td>
							<c:choose>
								<c:when test="${tache.ordinateur == '-'}">
									
								</c:when>
								<c:otherwise>
									${tache.ordinateur}
								</c:otherwise>
							</c:choose>
						
						</td>
						<td>${tache.service}</td>
						<td>${tache.nom}</td>
						<td>${tache.dateFin}</td>
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