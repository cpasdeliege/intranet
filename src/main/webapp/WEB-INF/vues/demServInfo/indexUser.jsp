<%@page import="be.cpasdeliege.intranet.DemServInfo.model.DemServInf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.*" %>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.model.*" %>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">
<style type="text/css">
#stat td {
	border : 1px solid black;
	padding-left : 5px;
	height: 40px;
	text-align: center;
}
</style>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">demande service informatique</a>
<hr>
</div>
<%

//List<DemServInf> listeDsi = (List<DemServInf>)request.getAttribute("listeDsi");
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
List<DemServInf> nouvelles = (List<DemServInf> ) request.getAttribute("listeDsiNouvelles");
List<DemServInf> validUser = (List<DemServInf>) request.getAttribute("listeDsiValidUser");

List<DemServInf> validUserEnAttente = (List<DemServInf>) request.getAttribute("listeDsiValidUserEnAttente");

List<DemServInf> validInfo = (List<DemServInf>) request.getAttribute("listeDsiValidInfo");
List<DemServInf> conf = (List<DemServInf>) request.getAttribute("listeDsiConf");
List<DemServInf> acceptees = (List<DemServInf>) request.getAttribute("listeDsiAcceptees");
List<DemServInf> executees = (List<DemServInf>) request.getAttribute("listeDsiExecutees");
List<DemServInf> attente = (List<DemServInf>) request.getAttribute("listeDsiAttente");
List<DemServInf> refusees = (List<DemServInf>) request.getAttribute("listeDsiRefusees");

StatDem stat = (StatDem) request.getAttribute("StatDem");
%>
<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">


	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td align="center"><a class="lien_tableau" href="index.dsi">Demandes en cours</a> - <a class="lien_tableau" href="index.dsi?action=fini">Demandes traitées</a></td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	
	<tr height="25">
			<td></td>
			<td><br></td>
			<td></td>
		</tr>

		<tr height="25">
		<td></td>
		<td>
			<table id="stat" width="100%" cellspacing="0">
				<tr>
					<td>En cours de traitement : <%= stat.getNbrAcceptee() %></td>
					<td>Attente validation Direction : <%= stat.getNbrAttenteDG() %></td>
					<td>Attente validation Informatique : <%= stat.getNbrAttenteInfo() %></td>
					<td>Nombre total de demandes depuis le 01/01/<%= stat.getAnnee() %> : <%= stat.getNbrAnneeEnCours() %></td>
				</tr>
			</table>
		</td>
		<td></td>
	</tr>
	
	<tr height="25">
		<td></td>
		<td>
			<BR>
		</td>
		<td></td>
	</tr>
	
	<% if(nouvelles.size() != 0) {%>
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="30px"><img src="images/dsi.png" border="0" width="35px"></td>
					<td class="titre_tableau"><a name="nouvelles">Liste des nouvelles demandes (<%= nouvelles.size() %>)</a></td>
					<td align="right">
						<form method="get" action="addDemande.dsi">
							<input type="submit" value="ajouter une demande"/>
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
					<th><a href="index.dsi?orderby=idDemandes#nouvelles">n°</a></th>
					<th><a href="index.dsi?orderby=typeDemande#nouvelles">Type</a></th>
					<th>Titre</th>
					<th><a href="index.dsi?orderby=nom#nouvelles">Auteur Demande</a></th>
					<th><a href="index.dsi?orderby=service#nouvelles">Service</a></th>
					<th><a href="index.dsi?orderby=nomChef#nouvelles">Responsable</a></th>
					<th><a href="index.dsi?orderby=dateDemande#nouvelles">Date Demande</a></th>
					<% if(privilege.isDsiChef()) { %><th></th><% } %>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listeDsiNouvelles}" var="dsi">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>${dsi.idDemandes}</td>
						<td>${dsi.typeDemandeAffichage}</td>
						<td><a class="lien_tableau" href="afficherDemande.dsi?idDemandes=${dsi.idDemandes}">${dsi.titre}</a></td>
						<td>${dsi.nom} ${dsi.prenom}</td>
						<td>${dsi.service}</td>
						<td>${dsi.nomChef} ${dsi.prenomChef}</td>
						<td>${dsi.dateDemande}</td>
						<% if(privilege.isDsiChef()) { %><td><a href="index.dsi?action=supprimer&amp;idDemandes=${dsi.idDemandes}"><img alt="supprimer" src="images/administration/supprimer.gif" border="0"></a></td><% } %>
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
	<% } else { %>
			<tr>
				<td></td>
				<td>
					<table width="100%">
						<tr>
							<td width="30px"></td>
							<td class="titre_tableau"></td>
							<td align="right">
								<form method="get" action="addDemande.dsi">
									<input type="submit" value="ajouter une demande"/>
								</form>
							</td>
						</tr>
					</table>
				</td>
				<td></td>
			</tr>
	<% } %>
	<%
	if(privilege.isDsiUser()) {
	%>
	<% if(validUser.size() != 0) {%>
	<tr height="25">
		<td colspan="3"><br></td>
		
	</tr>
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="30px"><img src="images/confirmation.png" border="0" width="35px"></td>
					<td class="titre_tableau"><a name="validUser">Liste des demandes en attente de validation (<%= validUser.size() %>)</a></td>
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
					<th><a href="index.dsi?orderby=idDemandes#validUser">n°</a></th>
					<th><a href="index.dsi?orderby=typeDemande#validUser">Type</a></th>
					<th>Titre</th>
					<th><a href="index.dsi?orderby=nom#validUser">Auteur Demande</a></th>
					<th><a href="index.dsi?orderby=service#validUser">Service</a></th>
					<th><a href="index.dsi?orderby=nomChef#validUser">Responsable</a></th>
					<th><a href="index.dsi?orderby=dateDemande#validUser">Date Demande</a></th>
				</tr>
				<% int j = 0; %>
				<c:forEach items="${listeDsiValidUser}" var="dsi">
				<% j++; %>
				<% if(j%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>${dsi.idDemandes}</td>
						<td>${dsi.typeDemandeAffichage}</td>
						<td><a class="lien_tableau" href="afficherDemande.dsi?idDemandes=${dsi.idDemandes}">${dsi.titre}</a></td>
						<td>${dsi.nom} ${dsi.prenom}</td>
						<td>${dsi.service}</td>
						<td>${dsi.nomChef} ${dsi.prenomChef}</td>
						<td>${dsi.dateDemande}</td>
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
	<% } %>
	
	
	<%
	}
	%>
	
	
	
	
	
	
	
	
	
	
	
	
	<%
			if (validUserEnAttente.size() != 0) {
		%>
		<tr height="25">
			<td colspan="3"><br>
			</td>

		</tr>

		<tr height="25">
			<td></td>
			<td>
				<table width="100%">
					<tr>
						<td width="30px"><img src="images/enAttente.png"
							border="0" width="35px">
						</td>
						<td class="titre_tableau"><a name="validInfo">Liste des
								demandes en attente d'une action extérieure (<%=validUserEnAttente.size()%>)</a>
						</td>
						<td align="right"></td>
					</tr>
				</table></td>
			<td></td>
		</tr>
		<tr height="25">
			<td width="22"
				background="images/administration/cadre/area_top_left.gif"></td>
			<td background="images/administration/cadre/area_top.gif"></td>
			<td width="22"
				background="images/administration/cadre/area_top_right.gif"></td>
		</tr>
		<tr>
			<td background="images/administration/cadre/area_left.gif"></td>
			<td>
				<table class="tableau_liste" cellspacing="0">
					<tr class="titre_colonne_tableau_liste">
						<th><a href="index.dsi?orderby=idDemandes#validInfo">n°</a>
						</th>
						<th><a href="index.dsi?orderby=typeDemande#validInfo">Type</a>
						</th>
						<th>Titre</th>
						<th><a href="index.dsi?orderby=nom#validInfo">Auteur
								Demande</a>
						</th>
						<th><a href="index.dsi?orderby=service#validInfo">Service</a>
						</th>
						<th><a href="index.dsi?orderby=nomChef#validInfo">Responsable</a>
						</th>
						<th><a href="index.dsi?orderby=dateDemande#validInfo">Date
								Demande</a>
						</th>
					</tr>
					<%
						int j = 0;
					%>
					<c:forEach items="${listeDsiValidUserEnAttente}" var="dsi">
						<%
							j++;
						%>
						<%
							if (j % 2 == 0) {
						%>
						<tr class="ligne_paire"
							onmouseover="this.className='ligne_survol'"
							onmouseout="this.className='ligne_paire'">
							<%
								} else {
							%>
						
						<tr class="ligne_impaire"
							onmouseover="this.className='ligne_survol'"
							onmouseout="this.className='ligne_impaire'">
							<%
								}
							%>
							<td>${dsi.idDemandes}</td>
							<td>${dsi.typeDemandeAffichage}</td>
							<td><a class="lien_tableau"
								href="afficherDemande.dsi?idDemandes=${dsi.idDemandes}">${dsi.titre}</a>
							</td>
							<td>${dsi.nom} ${dsi.prenom}</td>
							<td>${dsi.service}</td>
							<td>${dsi.nomChef} ${dsi.prenomChef}</td>
							<td>${dsi.dateDemande}</td>
						</tr>
					</c:forEach>
				</table></td>
			<td background="images/administration/cadre/area_right.gif"></td>
		</tr>
		<tr height="25">
			<td background="images/administration/cadre/area_bottom_left.gif"></td>
			<td background="images/administration/cadre/area_bottom.gif"></td>
			<td background="images/administration/cadre/area_bottom_right.gif"></td>
		</tr>
		<%
			}
		%>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<% if(acceptees.size() != 0) {%>
	
	<tr height="25">
		<td colspan="3"><br></td>
		
	</tr>
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="30px"><img src="images/accepte.png" border="0" width="35px"></td>
					<td class="titre_tableau"><a name="acceptees">Liste des demandes acceptées (<%= acceptees.size() %>)</a></td>
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
					<th><a href="index.dsi?orderby=idDemandes#acceptees">n°</a></th>
					<!-- <th><a href="index.dsi?orderby=typeDemande#acceptees">Type</a></th>  -->
					<th>Titre</th>
					<th><a href="index.dsi?orderby=nom#acceptees">Auteur Demande</a></th>
					<th><a href="index.dsi?orderby=service#acceptees">Service</a></th>
					<!-- <th><a href="index.dsi?orderby=nomChef#acceptees">Responsable</a></th> -->
					<th><a href="index.dsi?orderby=dateExecSouhaiteeSecrConf#acceptees">Date Echéance</a></th>
				</tr>
				<% int j = 0; %>
				<c:forEach items="${listeDsiAcceptees}" var="dsi">
				<% j++; %>
				<% if(j%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>${dsi.idDemandes}</td>
						<!-- <td>${dsi.typeDemandeAffichage}</td> -->
						<td><a class="lien_tableau" href="afficherDemande.dsi?idDemandes=${dsi.idDemandes}">${dsi.titre}</a></td>
						<td>${dsi.nom} ${dsi.prenom}</td>
						<td>${dsi.service}</td>
						<!-- <td>${dsi.nomChef} ${dsi.prenomChef}</td> -->
						<td>${dsi.dateExecSouhaiteeSecrConf}</td>
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
	<% } %>
	
</table>

<jsp:include page="../basDePage.jsp" ></jsp:include>
</body>
</html>