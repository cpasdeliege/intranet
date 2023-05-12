<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
  
  <%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>--%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.informatique.model.*" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="cpas.css" type="text/css">
<script type="text/javascript">


function reinitialiserMotDePasse(login) {
   var mdp = confirm('Confirmer la ré-initialisation du mot de passe ?');
   if(mdp) {
   	document.location.href='reinitialiserMotDePasse.admin?login=' + login;
   }
}

function supprimerUtilisateur(login) {
   var prout = confirm('Confirmer la suppression de l\'utilisateur ?');
   if(prout) {
   	document.location.href='supprimerUtilisateurIntranet.admin?login=' + login;
   }
}

function supprimerPhoto(nom, prenom) {
   var test = confirm('Confirmer la suppression de la photo ?');
   if(test) {
   	document.location.href='utilsTache.admin?action=supprimerPhoto&nom=' + nom + '&prenom=' + prenom;
   }
}


<!-- href="utilsTache.admin?action=supprimerPhoto&amp;nom=${personnel.nom}&amp;prenom=${personnel.prenom}" -->

//-->
</script>
<title>Intranet CPAS de Liège - administration - gestion des services</title>
</head>
<body class="body">
<jsp:include page="../entete.jsp" ></jsp:include>
<div>
<hr>
&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="gestionPersonnels.admin">gestion du personnel</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" >${personnel.nom } ${personnel.prenom }</a>
<hr>
</div>
<br>
<%
List listeSer = (List)request.getAttribute("listeService");
List listeOrdi = (List)request.getAttribute("listeOrdinateur");
Utilisateur utilisateur = (Utilisateur)request.getAttribute("comteUtilisateur");
PrivilegeInformatique privilegeInformatique = (PrivilegeInformatique)request.getAttribute("privilègeInformatique");
Personnel pers = (Personnel)request.getAttribute("personnel");
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
					<td width="45px"><img src="images/administration/personnel.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau">${personnel.nom } ${personnel.prenom }</td>
		 			<td align="right">
						<form method="get" action="formulairePersonnelModifier.admin" accept-charset="UTF-8">
							<input type="submit" value="modifier"/>
							<!-- <input type="hidden" name="nomPersonnel" value="${personnel.nom }"/> -->
							<input type="hidden" name="nomPersonnel" value="${personnel.nom }"/>
							<input type="hidden" name="prenomPersonnel" value="${personnel.prenom }"/>
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
					<td valign="top">
						<table>
							<tr>
								<td align="right">login Windows : </td>
								<td class="texte_cadre_gestion">${personnel.loginWindows }</td>
							</tr>
							<tr>
								<td align="right">login AS400 : </td>
								<td class="texte_cadre_gestion">${personnel.loginAS400 }</td>
							</tr>
							<tr>
								<td align="right">login CPAS 2000 : </td>
								<td class="texte_cadre_gestion">${personnel.loginCPAS2000 }</td>
							</tr>
							<tr>
								<td align="right">login GRH : </td>
								<td class="texte_cadre_gestion">${personnel.loginGRH }</td>
							</tr>
							<tr>
								<td align="right">login NCC : </td>
								<td class="texte_cadre_gestion">${personnel.loginNCC }</td>
							</tr>
						</table>
					</td>
					<td valign="top">
						<table>
							<tr>
								<td align="right">adresse email : </td>
								<td class="texte_cadre_gestion">${personnel.email }</td>
							</tr>
							<tr>
								<td align="right">alias email : </td>
								<td class="texte_cadre_gestion">${personnel.emailAlias }</td>
							</tr>
							<tr>
								<td align="right">code Agent : </td>
								<td class="texte_cadre_gestion">${personnel.codeAS }</td>
							</tr>
							<tr>
								<td align="right"><!-- mdp PubliLink :  -->&nbsp;</td>
								<td class="texte_cadre_gestion"><!-- ${personnel.mdpPublilink } --></td>
							</tr>
							<tr>
								<td align="right">accès wifi : </td>
								<td class="texte_cadre_gestion">
									<c:choose>
			                            <c:when test="${personnel.wifi == 0}">
			                                non
			                            </c:when>
			                            <c:otherwise>
			                               oui
			                            </c:otherwise>
			                        </c:choose>
								</td>
							</tr>
						</table>
					</td>
					<td><% if (!pers.getPathPhoto().equals("")) {%>
						<img src="http://10.210.117.21//photos/${personnel.pathPhoto }" border="0" width="120px">
						
						<% } %>
					</td>
					<td align="right">
						<table>
							<tr>
								<td>
									<%	if(listeSer.size() > 1) { %>														
											<%= listeSer.size() %> services
									<%	} else { %>
											<%= listeSer.size() %> service
									<%	} %>
								</td>
							</tr>
							<tr>
								<td>
									<%	if(listeOrdi.size()>1) { %>														
											<%= listeOrdi.size() %> ordinateurs
									<%	} else { %>
											<%= listeOrdi.size() %> ordinateur
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
					<td width="45px"><img src="images/administration/service.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="listeServices">Liste des services</a></td>
					<td align="right">
						<form method="get" action="formulairePersonnelServiceAssigner.admin">
							<input type="submit" value="assigner à un service"/>
							<input type="hidden" name="nom" value="${personnel.nom }"/>
							<input type="hidden" name="prenom" value="${personnel.prenom }"/>
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
					<th>Service</th>
					<th>Fonction</th>
					<th></th>
					<th>Téléphone</th>
					<th>Ext VOIP</th>
				</tr>
				<% int i = 0; %>
				<c:forEach items="${listeService}" var="employe">
				<%-- ******************************************************** --%>
				<%-- <%! String url ;
					String serv ; 
				%>
					 <% url = "donnée à  ù"; 
					 	
					 %> 
					
			
				<% 
					try{
							String encodedurl = URLEncoder.encode(url.toString(),"UTF-8"); 
							out.println(encodedurl);
							out.println("decode url ==>" + url + " === serviuce ==>"+serv);
							out.println(URLDecoder.decode(encodedurl,"UTF-8"));
							out.println("données service **********************"); 
						
						}catch(Exception e){
							out.println(e);
					}
				%> --%>
					
				<%--  ************************************************************* --%>
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<%-- <td><a class="lien_tableau" href="gestionService.admin?service=${employe.service}#listePersonnel">${employe.service}</a></td> --%>
						 <td><a class="lien_tableau" href="gestionService.admin?service=${employe.service}#listePersonnel">${employe.service}</a></td> 
						<td>${employe.fonction}</td>
						<td>${employe.rem}</td>
						<td>${employe.telephone}</td>
						<td>${employe.extension}</td>
						<td width="30px" align="right"><a href="formulairePersonnelServiceModifier.admin?service=${employe.service}&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listeServices"><img src="images/administration/modifier.png" border="0" alt="modifier"></a></td>
						<td width="30px" align="right"><a href="desassignerServicePersonnel.admin?service=${employe.service}&amp;nom=${employe.nom}&amp;prenom=${employe.prenom}&amp;ancre=listeServices"><img src="images/administration/supprimer.gif" border="0"></a></td>
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
					<td width="45px"><img src="images/administration/ordinateur.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="listeOrdinateurs">Liste des ordinateurs</a></td>
					<td align="right">
						<form method="get" action="formulairePersonnelOrdinateurAssigner.admin">
							<!-- <input type="submit" value="assigner un ordinateur"/> -->
							<input type="hidden" name="nom" value="${personnel.nom }"/>
							<input type="hidden" name="prenom" value="${personnel.prenom }"/>
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
				</tr>
				<%  i = 0; %>
				<c:forEach items="${listeOrdinateur}" var="ordinateur">
				<% i++; %>
				<% if(i%2 == 0) {  %>
					<tr class="ligne_paire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_paire'">
				<% } else { %>
					<tr class="ligne_impaire" onmouseover="this.className='ligne_survol'" onmouseout="this.className='ligne_impaire'">
				<% } %>
						<td>${ordinateur.ordinateur}</td>
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
					<td width="45px"><img src="images/administration/intranet.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="accesIntranet">Accès intranet</a></td>
					<td align="right">
						
							<%
							if(utilisateur == null) {
							%>
								
								<form method="get" action="ajouterUtilisateurIntranet.admin">
									<input type="submit" value="ajouter accès intranet"/>
									<input type="hidden" name="nom" value="${personnel.nom }"/>
									<input type="hidden" name="prenom" value="${personnel.prenom }"/>
								</form>
							<%
							} else {
							%>
								<input type="submit" value="supprimer accès intranet" onclick="supprimerUtilisateur('<%= utilisateur.getLogin() %>')"/>
							<%
							}
							%>
						
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
			<%
			if(utilisateur == null) {
			%>
				<p align="center">Pas d'accès privilégié</p>
			<%
			} else {
			%>
				<table width="100%">
					<tr>
						<td align="right">
							login : 
						</td>
						<td class="texte_cadre_gestion">
							${comteUtilisateur.login }
						</td>
						<td> 
							<input type="submit" value="réinitialiser mot de passe" onclick="reinitialiserMotDePasse('<%= utilisateur.getLogin() %>')"/>
						</td>
						<td width="5%" align="center" <% if(utilisateur.isActif()){%> bgcolor="green" <%} else {%> bgcolor="red" <%}%>>
							<form method="get" action="activationAccesIntranet.admin">
								<input type="submit" value="activer" <% if(utilisateur.isActif()){%> disabled="disabled" <%}%>/>
								<input type="hidden" name="login" value="<%= utilisateur.getLogin() %>"/>
								<input type="hidden" name="acces" value="true"/>
							</form>
							<form method="get" action="activationAccesIntranet.admin">
								<input type="submit" value="désactiver"  <% if(!utilisateur.isActif()){%> disabled="disabled" <%}%>/>
								<input type="hidden" name="login" value="<%= utilisateur.getLogin() %>"/>
								<input type="hidden" name="acces" value="false"/>
							</form>
						</td>
					</tr>
				</table>
				<hr/>
				<table width="100%">
					<tr>
						<td width="300px">
							Informatique : 
						</td>
						<td valign="top" class="texte_cadre_gestion" align="right">
							<table>
								<tr>
									<td align="right">administrateur : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isAdministrateur()){%> checked="checked" <%}%>/></td>
								</tr>
								<tr>
									<td align="right">téléphonie : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isTelephonie()){%> checked="checked" <%}%>/></td>
								</tr>
								<tr>
									<td align="right">formations : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isFormations()){%> checked="checked" <%}%>/></td>
								</tr>
							</table>
						</td>
						<td width="300px">
							
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<hr>
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							Demande Service Informatique : 
						</td>
						<td valign="top" class="texte_cadre_gestion" align="right">
							<table>
								<tr>
									<td align="right">utilisateur : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isDsiUser()){%> checked="checked" <%}%>/></td>
								</tr>
								<tr>
									<td align="right">chef dép. : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isDsiChef()){%> checked="checked" <%}%>/></td>
								</tr>
								<tr>
									<td align="right">informatique : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isDsiInfo()){%> checked="checked" <%}%>/></td>
								</tr>
								<tr>
									<td align="right">direction : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isDsiDirection()){%> checked="checked" <%}%>/></td>
								</tr>
							</table>
						</td>
						<td align="center">
							
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<hr>
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							Cadastre Marchés Publics : 
						</td>
						<td valign="top" class="texte_cadre_gestion" align="right">
							<table>
								<tr>
									<td align="right">administrateur : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isCadastreMP()){%> checked="checked" <%}%>/></td>
								</tr>
							</table>
						</td>
						<td align="center">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<hr>
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							Vade-Mecum : 
						</td>
						<td valign="top" class="texte_cadre_gestion" align="right">
							<table>
								<tr>
									<td align="right">administrateur : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isAdmVM()){%> checked="checked" <%}%>/></td>
								</tr>
							</table>
						</td>
						<td align="center">
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<hr>
						</td>
						<td>
							
						</td>
					</tr>
					<tr>
						<td>
							Code Wifi : 
						</td>
						<td valign="top" class="texte_cadre_gestion" align="right">
							<table>
								<tr>
									<td align="right">utilisateur : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isWifiUser()){%> checked="checked" <%}%>/></td>
								</tr>
								<tr>
									<td align="right">administrateur : </td>
									<td><input disabled type="checkbox" <% if(privilegeInformatique.isWifiAdmin()){%> checked="checked" <%}%>/></td>
								</tr>
							</table>
						</td>
						<td align="center">
							<form method="get" action="formulaireModifierPrivilegeInformatique.admin">
							<input type="submit" value="modifier"/>
							<input type="hidden" name="login" value="${privilègeInformatique.login }"/>
							</form>
						</td>
					</tr>
					
					
					
					
				</table>
			<%
			}
			%>
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
					<td width="60px"><img src="images/administration/tache.png" border="0" width="<%= width %>"></td>
					<td class="titre_tableau"><a name="listeTache">Liste des tâches à effectuer</a></td>
					<td align="right">
						<form method="get" action="formulaireTacheAjouter.admin">
							<input type="submit" value="ajouter une tâche"/>
							<input type="hidden" name="nomFormulaire" value="${personnel.nom }">
							<input type="hidden" name="prenomFormulaire" value="${personnel.prenom }">
							<input type="hidden" name="ordinateurFormulaire" value="${ordinateurDefaultTache.ordinateur }">
							<input type="hidden" name="serviceFormulaire" value="${serviceDefaultTache.service }">
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
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnCours=idPlanning#listeTache">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnCours=ordinateur#listeTache">ordinateur</a></th>
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnCours=service#listeTache">service</a></th>
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnCours=echeance#listeTache">echéance</a></th>
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnCours=nomInfo#listeTache">assigné à</a></th>
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
						<td>${tache.service}</td>
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
					<td width="60px"><img src="images/administration/tacheFinie.png" border="0" width="<%= width %>"></td>
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
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnFini=idPlanning#fini">num</a></th>
					<th align="center">titre</th>
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnFini=ordinateur#fini">ordinateur</a></th>
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnFini=service#fini">service</a></th>
					<th align="center"><a href="gestionPersonnel.admin?nom=${personnel.nom}&amp;prenom=${personnel.prenom}&amp;triEnFini=dateFin#fini">date fin</a></th>
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
						<td>${tache.service}</td>
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