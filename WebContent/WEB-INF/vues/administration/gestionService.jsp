
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 

 <%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
 <!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  -->
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des services</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionServices.admin">gestion des services</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >${service.nom }</a>
<hr>
</div>
<br>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>
<%
List liste = (List)request.getAttribute("listePersonnel");
List listeOrdi = (List)request.getAttribute("listeOrdinateurs");
List listeImprimante = (List)request.getAttribute("listeImprimantes");
Service service = (Service)request.getAttribute("service");
Departement departement = (Departement)request.getAttribute("departement");
Pole pole = (Pole)request.getAttribute("pole");


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
					<td width="45px"><img src="images/administration/service.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><u style="font-weight: bold;">${service.nom}</u> <c:if test="${not empty departement}"> / ${departement.nom} </c:if> <c:if test="${not empty pole}"> / ${pole.nom} </c:if></td>
					<td align="right">
						<form method="get" action="formulaireServiceModifier.admin">
							<input type="submit" value="modifier le service"/>
							<input type="hidden" name="service" value="${ fn:escapeXml(service.nom) }"/>
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
								<td>adresse : </td>
								<td class="texte_cadre_gestion">${service.adresse}, ${service.numero}</td>
							</tr>
							<tr>
								<td></td>
								<td class="texte_cadre_gestion">${service.codePostal} ${service.localite}</td>
							</tr>
							<tr>
								<td>localisation : </td>
								<td class="texte_cadre_gestion">${service.localisation}</td>
							</tr>
							<tr>
								<td>fax : </td>
								<td class="texte_cadre_gestion">${service.fax}</td>
							</tr>
							<tr>
								<td>email service : </td>
								<td class="texte_cadre_gestion">${service.emailService}</td>
							</tr>
							<tr>
								<td valign="top">remarques : </td>
								<td class="texte_cadre_gestion"><%= service.getRemarque().replaceAll("\r\n", "<br>") %></td>
							</tr>
						</table>
					</td>
					<td align="right">
						<table>
							<tr>
								<td>
									<%	if(liste.size() > 1) { %>														
											<%= liste.size() %> employ&eacute;s
									<%	} else { %>
											<%= liste.size() %> employ&eacute;
									<%	} %>
								</td>
							</tr>
							<tr>
								<td>
									<%	if(listeOrdi.size() > 1) { %>														
											<%= listeOrdi.size() %> ordinateurs
									<%	} else { %>
											<%= listeOrdi.size() %> ordinateur
									<%	} %>
								</td>
							</tr>
							<tr>
								<td>
									<%	if(listeImprimante.size() > 1) { %>														
											<%= listeImprimante.size() %> imprimantes
									<%	} else { %>
											<%= listeImprimante.size() %> imprimante
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
					<td width="45px"><img src="images/administration/personnel.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="listePersonnel">Liste du personnel</a></td>
					<td align="right">
						<form method="get" action="formulaireServicePersonnelAssigner.admin">
							<input type="submit" value="assigner un employ&eacute;"/>
							<input type="hidden" name="service" value="${ fn:escapeXml(service.nom) }"/>
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
					<th>Fonction</th>
					<th></th>
					<th>T&eacute;l&eacute;phone</th>
					<th>Ext VOIP</th>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listePersonnel}" var="employe">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionPersonnel.admin?nom=${employe.nom}&amp;prenom=${employe.prenom}">${employe.nom}</a></td>
						<td>${employe.prenom}</td>
						<td>${employe.fonction}</td>
						<td>${employe.rem}</td>
						<td>${employe.telephone}</td>
						<td>${employe.extension}</td>
						<td width="30px" align="right"><a href="formulaireServicePersonnelModifier.admin?service=${ fn:escapeXml(employe.service) }&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listePersonnel"><img src="images/administration/modifier.png" border="0" alt="modifier"></a></td>
						<td width="30px" align="right" onclick=""><a href="desassignerServicePersonnel.admin?service=${ fn:escapeXml(employe.service) }&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listePersonnel"><img src="images/administration/supprimer.gif" border="0"></a></td>
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
					<td class="titre_tableau"><a name="listeOrdinateurs">Liste des ordinateurs</a></td>
					<!--<td align="right">
						<form method="get" action="formulaireOrdinateurAjouter.admin">
							<input type="submit" value="ajouter un ordinateur"/>
							<input type="hidden" name="serviceDefault" value="${service.nom }"> 
						</form>
					</td>-->
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
					<th>O.S.</th>
					<th>Carte m&egrave;re</th>
					<th>Ecran</th>
				</tr>
				<% i = 0; %>
				<c:forEach items="${listeOrdinateurs}" var="ordinateur">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>${ordinateur.nom}</td>
						<td>${ordinateur.systemeExploitation}</td>
						<td>${ordinateur.carteMere}</td>
						<td>${ordinateur.ecran}</td></tr>
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
		<td>
			
		</td>
		<td></td>
	</tr>
	
	<% /** %>
	<!-- 
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="45px"><img src="images/administration/imprimante.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="imprimantesLocale">Liste des imprimantes</a></td>
					<td align="right">
						<form method="get" action="formulaireImprimanteAjouter.admin">
							<input type="submit" value="ajouter une imprimante"/>
							<input type="hidden" name="serviceDefault" value="${service.nom }"> 
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
					<th>Num&eacute;ro de S&eacute;rie</th>
					<th>Type</th>
					<th>Ordinateur</th>
				</tr>
				<%  i = 0; %>
				<c:forEach items="${listeImprimantes}" var="imprimante">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionImprimante.admin?numeroSerie=${imprimante.numeroSerie}">${imprimante.numeroSerie}</a></td>
						<td>${imprimante.type}</td>
						<td><a class="lien_tableau" href="gestionOrdinateur.admin?ordinateur=${imprimante.ordinateurLocal}">${imprimante.ordinateurLocal}</a></td>
						<td width="30px" align="right"><a href="supprimerImprimanteConfirmation.admin?imprimante=${imprimante.numeroSerie}"><img alt="surpprimer" src="images/administration/supprimer.gif" border="0"></a></td>
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
	 -->
	
	<% */ %>
	
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
					<td width="45px"><img src="images/administration/tache.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="listeTache">Liste des t&acirc;ches &agrave; effectuer</a></td>
					<td align="right">
						<form method="get" action="formulaireTacheAjouter.admin">
							<input type="submit" value="ajouter une t&acirc;che"/>
							<input type="hidden" name="serviceFormulaire" value="${ fn:escapeXml(service.nom) }">
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
					<th align="center"><a href="gestionService.admin?service=${ fn:escapeXml(service.nom) }&amp;triEnCours=idPlanning#listeTache">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionService.admin?service=${ fn:escapeXml(service.nom) }&amp;triEnCours=ordinateur#listeTache">ordinateur</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionService.admin?service=${ fn:escapeXml(service.nom) }&amp;triEnCours=echeance#listeTache">ech&eacute;ance</a></th>
					<th align="center"><a href="gestionService.admin?service=${ fn:escapeXml(service.nom) }&amp;triEnCours=nomInfo#listeTache">assign&eacute; &agrave;</a></th>
				</tr>
				<% i = 0; %>
				<c:forEach items="${listeTacheEnCours}" var="tache">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
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
	
	<table cellpadding="0" cellspacing="0"  class="tableau_container_centre">
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/administration/tache.png" border="0" width="50px"></td>
					<td class="titre_tableau"><a name="enAttente">Liste des t&acirc;ches en attente</a></td>
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
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=idPlanning#enAttente">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=ordinateur#enAttente">ordinateur</a></th>
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=service#enAttente">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionPlanning.admin?triEnAttente=echeance#enAttente">ech&eacute;ance</a></th>
				</tr>
				<% int i3 = 0; %>
				<c:forEach items="${listeTacheEnAttente}" var="tache">
				<% i3++; %>
				<% if(i3%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
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
	
	
	<tr height="25">
		<td></td>
		<td>
			<table width="100%">
				<tr>
					<td width="45px"><img src="images/administration/tacheFinie.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="fini">Liste des t&acirc;ches effectu&eacute;es</a></td>
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
					<th align="center"><a href="gestionService.admin?service=${ fn:escapeXml(service.nom) }&amp;triEnFini=idPlanning#fini">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionService.admin?service=${ fn:escapeXml(service.nom) }&amp;triEnFini=ordinateur#fini">ordinateur</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionService.admin?service=${ fn:escapeXml(service.nom) }&amp;triEnFini=dateFin#fini">date fin</a></th>
				</tr>
				<% i = 0; %>
				<c:forEach items="${listeTachesFinies}" var="tache">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
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