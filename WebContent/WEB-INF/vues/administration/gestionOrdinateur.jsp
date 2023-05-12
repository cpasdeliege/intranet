<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
<!-- 

function reinitialiserMotDePasse(nomOrdi) {
   var nouvNomOrdi = prompt("Modifier le nom de l'ordinateur", nomOrdi);
   if(nouvNomOrdi) {
   	document.location.href='gestionOrdinateur.admin?nouvNomOrdi=' + nouvNomOrdi + '&ordinateur=' + nomOrdi;
   }
}
""
//-->
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<title>Intranet CPAS de Liège - administration - gestion des ordinateurs</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionOrdinateurs.admin">gestion des ordinateurs</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >${ordinateur.nom }</a>
<hr>
</div>
<br>
<%
List listePers = (List)request.getAttribute("listePersonnel");  
List listeImprLoc = (List)request.getAttribute("listeImprimantes");
List listeImprRes = (List)request.getAttribute("listeImprimantesReseau");
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
					<td width="45px"><img src="images/administration/ordinateur.png" border="0" width="<%= width %>"></td>
					
					<c:choose>
						<c:when test="${ordinateur.actif == 0}">
							<td class="titre_tableau" style="background-color: red;">${ordinateur.nom }</td>
						</c:when>
						<c:otherwise>
							<td class="titre_tableau">${ordinateur.nom }</td>
						</c:otherwise>
					</c:choose>
				
					<!-- <td align="right">
						<form method="get">
							<input type="submit" value="renomer l'ordinateur"/>
							<input type="hidden" name="ordinateur" value="${ordinateur.nom }"/>
						</form>
					</td>
					 -->
					<!--  <td align="right">
					 	<input type="submit" value="renommer l'ordinateur" onclick="reinitialiserMotDePasse('${ordinateur.nom }')"/>
					</td>
					²
					<td align="right" width="180px">
						<form method="get" action="formulaireOrdinateurModifier.admin">
							<input type="submit" value="modifier l'ordinateur"/>
							<input type="hidden" name="ordinateur" value="${ordinateur.nom }"/>
						</form>
					</td>
					-->
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
			<table class="tableau_liste" cellspacing="0" border="0">
				<tr>
					<td>
						<table>
							<tr>
								<td align="right">service : </td>
								<td class="texte_cadre_gestion" ><a class="lien_tableau" href="gestionService.admin?service=${ordinateur.service}#listeOrdinateurs">${ordinateur.service }</a></td>
							</tr>
							<tr>
								<td align="right">numéro de prise : </td>
								<td class="texte_cadre_gestion">${ordinateur.numeroPrise }</td>
							</tr>
							<tr>
								<td align="right">numéro CPAS2000 : </td>
								<td class="texte_cadre_gestion">${ordinateur.numCpas2000 }</td>
							</tr>
							<tr>
								<td align="right">système : </td>
								<td class="texte_cadre_gestion">${ordinateur.systemeExploitation }</td>
							</tr>
							<tr>
								<td align="right">carte mère : </td>
								<td class="texte_cadre_gestion">${ordinateur.carteMere }</td>
							</tr>
							<tr>
								<td align="right">processeur : </td>
								<td class="texte_cadre_gestion">${ordinateur.processeur }</td>
							</tr>
							<tr>
								<td align="right">mémoire ram : </td>
								<td class="texte_cadre_gestion">${ordinateur.memoireRam }</td>
							</tr>
							<tr>
								<td align="right">carte graphique : </td>
								<td class="texte_cadre_gestion">${ordinateur.carteGraphique }</td>
							</tr>
							<tr>
								<td align="right">disque dur : </td>
								<td class="texte_cadre_gestion">${ordinateur.disqueDur }</td>
							</tr>
							<tr>
								<td align="right">graveur : </td>
								<td class="texte_cadre_gestion">${ordinateur.graveur }</td>
							</tr>
							<tr>
								<td align="right">carte réseau : </td>
								<td class="texte_cadre_gestion">${ordinateur.carteReseau }</td>
							</tr>
							<tr>
								<td align="right">écran : </td>
								<td class="texte_cadre_gestion">${ordinateur.ecran }</td>
							</tr>
						</table>
					</td>
					<td>
						<img width="90px" alt="pas de photo" src="images/administration/ordi/${ordinateur.carteMere}.jpg" border="0">
					</td>
					<td align="right">
						<table>
							<tr>
								<td>
									<%	if(listePers.size() > 1) { %>														
											<%= listePers.size() %> employés
									<%	} else { %>
											<%= listePers.size() %> employé
									<%	} %>
								</td>
							</tr>
							<tr>
								<td>
									<%	if(listeImprLoc.size() > 1) { %>														
											<%= listeImprLoc.size() %> imprimantes locales
									<%	} else { %>
											<%= listeImprLoc.size() %> imprimante locale
									<%	} %>
								</td>
							</tr>
							<tr>
								<td>
									<%	if(listeImprRes.size() > 1) { %>														
											<%= listeImprRes.size() %> imprimantes réseau
									<%	} else { %>
											<%= listeImprRes.size() %> imprimante réseau
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
						<form method="get" action="formulaireOrdinateurPersonnelAssigner.admin">
							<input type="submit" value="assigner un employé"/>
							<input type="hidden" name="ordinateur" value="${ordinateur.nom }"/>
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
					<th>Prénom</th>
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
						<td width="30px" align="right" onclick=""><a href="desassignerOrdinateurPersonnel.admin?ordinateur=${employe.ordinateur}&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listePersonnel"><img src="images/administration/supprimer.gif" border="0"></a></td>
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
	
	<% /* %> <!--  
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
					<td width="45px"><img src="images/administration/imprimante.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="imprimantesLocale">Liste des imprimantes locales</a></td>
					<td align="right">
						<form method="get" action="formulaireImprimanteAjouter.admin">
							<input type="submit" value="ajouter une imprimante"/>
							<input type="hidden" name="ordinateurDefault" value="${ordinateur.nom }"> 
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
					<th>Numéro de Série</th>
					<th>Type</th>
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
					<td width="45px"><img src="images/administration/imprimanteReseau.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="imprimantesReseau">Liste des imprimantes en réseau</a></td>
					<td align="right">
						<form method="get" action="formulaireOrdinateurImprimanteAssigner.admin">
							<input type="submit" value="assigner une imprimante réseau"/>
							<input type="hidden" name="ordinateur" value="${ordinateur.nom }"/>
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
					<th>Numéro de Série</th>
					<th>Type</th>
					<th>Ordinateur distant</th>
				</tr>
				<%  i = 0; %>
				<c:forEach items="${listeImprimantesReseau}" var="imprimante">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td><a class="lien_tableau" href="gestionImprimante.admin?numeroSerie=${imprimante.numeroSerie}">${imprimante.numeroSerie}</a></td>
						<td>${imprimante.type}</td>
						<td>${imprimante.ordinateurLocal}</td>
						<td width="30px" align="right" onclick=""><a href="desassignerImprimanteOrdinateur.admin?ordinateur=${ordinateur.nom }&amp;numeroSerie=${imprimante.numeroSerie }&amp;ancre=imprimantesReseau"><img src="images/administration/supprimer.gif" border="0"></a></td>
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
	<% 
	
	 */
		
	%>
	
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
					<td class="titre_tableau"><a name="listeTache">Liste des tâches à effectuer</a></td>
					<td align="right">
						<form method="get" action="formulaireTacheAjouter.admin">
							<input type="submit" value="ajouter une tâche"/>
							<input type="hidden" name="ordinateurFormulaire" value="${ordinateur.nom }">
							<input type="hidden" name="serviceFormulaire" value="${ordinateur.service }">
							<input type="hidden" name="prenomFormulaire" value="${personnelDefaultTache.prenom }">
							<input type="hidden" name="nomFormulaire" value="${personnelDefaultTache.nom }">
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
					<th align="center"><a href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}&amp;triEnCours=idPlanning#listeTache">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}&amp;triEnCours=service#listeTache">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}&amp;triEnCours=echeance#listeTache">echéance</a></th>
					<th align="center"><a href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}&amp;triEnCours=nomInfo#listeTache">assigné à</a></th>
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
						<td>${tache.service}</td>
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
					<td class="titre_tableau"><a name="enAttente">Liste des tâches en attente</a></td>
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
					<td class="titre_tableau"><a name="fini">Liste des tâches effectuées</a></td>
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
					<th align="center"><a href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}&amp;triEnFini=idPlanning#fini">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}&amp;triEnFini=service#fini">service</a></th>
					<th align="center">contact</th>
					<th align="center"><a href="gestionOrdinateur.admin?ordinateur=${ordinateur.nom}&amp;triEnFini=dateFin#fini">date fin</a></th>
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