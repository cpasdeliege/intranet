<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
<%@ page import="be.cpasdeliege.intranet.DemServInfo.*" %>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.DemServInf"%>
<%@page import="be.cpasdeliege.intranet.DemServInfo.model.Remarque"%>
<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%@page import="java.net.*"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c" %>

<% 
DemServInf dsi = (DemServInf)request.getAttribute("dsi");
Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>DSI ${dsi.idDemandes} - Intranet CPAS de Liège</title>
		<link rel="stylesheet" href="site.css" type="text/css">
	
		<script language="JavaScript" src="javascript/calendar_db.js"></script>
		<link rel="stylesheet" href="calendar.css">
	
		<script language="JavaScript">
			function ajoutLien() {
				document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href=""></a>';
			}
			function ajoutLienDSI() {
				document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href="afficherDemande.dsi?idDemandes=">voir DSI</a>';
			}
			function ajoutLienTHI() {
				document.getElementById('zonedetexte').value = document.getElementById('zonedetexte').value+' <a href="gestionTache.admin?idPlanning=">voir THI</a>';
			}
			function associerTache() {
				var person = prompt("Entrer le numéro de tâche à associer : ", "");
				if (person != null) {
					window.location = "utilsTache.admin?action=associerGTI2DSI&idDemandes=<%= dsi.getIdDemandes() %>&idPlanning="+person;
				}
			}
			function supAssoTache() {
				var person = prompt("Entrer le numéro de tâche à dissocier : ", "");
				if (person != null) {
					window.location = "utilsTache.admin?action=supAssoGTI2DSI&idDemandes=<%= dsi.getIdDemandes() %>&idPlanning="+person;
				}
			}
		</script>
	</head>
	<body class="body">
		<balise id="hautdepage"></balise>
		<jsp:include page="../entete.jsp" ></jsp:include>
		<div>
			<hr>
			&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
			<% if(privilege.isAdministrateur()) { %>
			<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.admin">administration</a>
			<% } %>
			<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.dsi">demande service informatique</a>
			<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">afficher une demande</a>
			<hr>
		</div>
		<p align="center" style="color: red">
		${erreur}
		</p>
	
		<table cellpadding="0" cellspacing="0"  class="tableau_container" width="1120px">
			<tr height="25">
				<td width="25px"></td>
				<td>
					<table width="100%">
						<tr>
							<td class="titre_tableau">Afficher une demande</td>
							<td><a class="lien_tableau" href="gestionPlanning.admin">GTI</a></td>
							<td align="right">
								<form method="get">
									<INPUT width="20px" border="0" src="images/refuse.png" type="image" Value="submit" alt="Suppression demande">  
									<input type="hidden" name="idDemandes" value="${dsi.idDemandes}">
									<input type="hidden" name="action" value="supprimer">
								</form>
							</td>
							<td width="65px" align="right">
								<a href="#basdepage"><img width="50px" alt="retour" src="images/2dowarrow.png" border="0"></a>
							</td>
						</tr>
					</table>
				</td>
				<td></td>
			</tr>
		</table>
		<form method="post" name="formu">
			<table cellpadding="0" cellspacing="0"  class="tableau_container" >
				<tr height="25">
					<td width="22" background="images/administration/cadre/area_top_left.gif"></td>
					<td background="images/administration/cadre/area_top.gif"></td>
					<td width="22" background="images/administration/cadre/area_top_right.gif"></td>
				</tr>
				<tr>
					<td background="images/administration/cadre/area_left.gif"></td>
					<td>
						<table>
							<tr>
								<td align="right">n° Demande : </td>
								<td>${dsi.idDemandes}</td>
								<td rowspan="10">&nbsp;&nbsp;&nbsp;&nbsp;</td>
								<td rowspan="10">
									<table width="100%">
										<tr>
											<td align="center">
												<%
													if(dsi.getValidationSecrConf().equals("non")) {
												%>
													statut : REFUSEE
												<%
													} else if(dsi.getValidationSecrConf().equals("supp")) {
												%>
													statut : SUPPRIMEE
												<%
													} else if((dsi.getDateExecEffective() == null) && dsi.getValidationSecrConf().equals("oui")) {
												%>
												 	statut : ACCEPTEE
												<%
													} else if((dsi.getDateExecEffective() != null) && dsi.getValidationSecrConf().equals("oui")) {
												%>
												 	statut : EXECUTEE
												<%
													}
												%>
												
												<%
													if(dsi.getValidationInfor().equals("none") && !dsi.getValidationSecr().equals("none")) {
														if(dsi.getEnAttente().equals("0")) {
															if(privilege.isDsiInfo()) {
												%>
													En attente d'une action extérieure : <input type="checkbox" name="enAttente">
												<%
															}
														} else {
															if(privilege.isDsiInfo()) {
												%>
													En attente d'une action extérieure : <input type="checkbox" name="enAttente" checked>
												<%			
															} else {
												%>
													statut : En attente d'une action extérieure
												<%												
																
															}
														}
													}
												%>
												
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
										</tr>
										<tr>
											<td>
												&nbsp;
											</td>
										</tr>
									</table>
									<table>
										<tr>
											<th></th>
											<th>Validation</th>
											<th>Echéance</th>
										</tr>
										<tr>
											<td align="right">U : </td>
											<td>
												
											</td>
											<td>
												<input size="8"  disabled  type="text" name="xxx" value="${dsi.dateExecSouhaitee}"/>
												
											</td>
										</tr>
										<tr>
											<td colspan="3"><hr></td>
										</tr>
										<% // if(!privilege.isDsiUser()) { %>   
										<% if(true) { %>
											<tr>
												<td align="right">R : </td>
												<td>
													
													<input <% if(dsi.getValidationChef().equals("oui")) { %> checked <% } %> 
														<% if(!privilege.isDsiChef() || !dsi.getValidationChef().equals("none")) { %> disabled <% } %> 
														type="radio" name="validationChef" value="oui"> oui
													
													<input <% if(dsi.getValidationChef().equals("non")) { %> checked <% } %> 
														<% if(!privilege.isDsiChef() || !dsi.getValidationChef().equals("none")) { %> disabled <% } %>
														type="radio" name="validationChef" value="non"> non 
												</td>
												<td>
													<% if(dsi.getValidationChef().equals("non")) { %>
																
													<% } else { %>
														<input size="8" <% if(!privilege.isDsiChef() || !dsi.getValidationChef().equals("none")) { %> disabled <% } %>
																type="text" name="dateExecSouhaiteeChef" 
																value="${dsi.dateExecSouhaiteeChefAffichage}"/>
														<% } %>
													<% if(privilege.isDsiChef() && dsi.getValidationChef().equals("none")) { %> 
														<script language="JavaScript">
															new tcal ({
																'formname': 'formu',
																'controlname': 'dateExecSouhaiteeChef'
															});
														</script>
													<% } %> 
												</td>
											</tr>
											<tr>
												<td colspan="3"><hr></td>
											</tr>
										<% } %>
										<% if(!dsi.getValidationChef().equals("none")) { %>
											<% // if(!privilege.isDsiUser()) { %>   
											<% if(true) { %>   
												<tr>
													<td align="right">D : </td>
													<td>
														
														<input <% if(dsi.getValidationSecr().equals("oui")) { %> checked <% } %> 
															<% if(!privilege.isDsiDirection() || !dsi.getValidationSecr().equals("none")) { %> disabled <% } %> 
															type="radio" name="validationSecr" value="oui"> oui
														
														<input <% if(dsi.getValidationSecr().equals("non")) { %> checked <% } %> 
															<% if(!privilege.isDsiDirection() || !dsi.getValidationSecr().equals("none")) { %> disabled <% } %>
															type="radio" name="validationSecr" value="non"> non 
													</td>
													<td>
														
															 <% if(dsi.getValidationSecr().equals("non")) { %>
																	
															<% } else { %>
																	<input size="8" <% if(!privilege.isDsiDirection() || !dsi.getValidationSecr().equals("none")) { %> disabled <% } %>
															 			type="text" name="dateExecSouhaiteeSecr" 
																		value="${dsi.dateExecSouhaiteeSecrAffichage}"/>
															<% } %>
														<% if(privilege.isDsiDirection() && dsi.getValidationSecr().equals("none")) { %> 
															<script language="JavaScript">
																new tcal ({
																	'formname': 'formu',
																	'controlname': 'dateExecSouhaiteeSecr'
																});
															</script>
														<% } %> 
														 
													</td>
												</tr>
												<tr>
													<td colspan="3"><hr></td>
												</tr>
											<% } %>
										<% } %>
										<% if(!dsi.getValidationSecr().equals("none")) { %>
											<% // if(!privilege.isDsiUser()) { %>   
											<% if(true) { %>   
												<tr>
													<td align="right">I : </td>
													<td>
														<input <% if(dsi.getValidationInfor().equals("oui")) { %> checked <% } %> 
															<% if(!privilege.isDsiInfo() || !dsi.getValidationInfor().equals("none")) { %> disabled <% } %>
															type="radio" name="validationInfor" value="oui"> oui
														
														<input <% if(dsi.getValidationInfor().equals("non")) { %> checked <% } %> 
															<% if(!privilege.isDsiInfo() || !dsi.getValidationInfor().equals("none")) { %> disabled <% } %>
															type="radio" name="validationInfor" value="non"> non
													</td>
													<td>
														
														<% if(dsi.getValidationInfor().equals("non")) { %>
																
														<% } else { %>
																<input size="8" <% if(!privilege.isDsiInfo() || !dsi.getValidationInfor().equals("none")) { %> disabled <% } %>
																type="text" name="dateExecPrevue" 
																value="${dsi.dateExecPrevueAffichage}"/>
														<% } %>
														<% if(privilege.isDsiInfo() && dsi.getValidationInfor().equals("none")) { %> 
															<script language="JavaScript">
																new tcal ({
																	'formname': 'formu',
																	'controlname': 'dateExecPrevue'
																});
															</script>
														<% } %> 
													</td>
												</tr>
												<tr>
													<td colspan="3"><hr></td>
												</tr>
											<% } %>
											<% if(!dsi.getValidationInfor().equals("none")) { %>
												<tr>
													<td align="right">Finale : </td>
													<td>
														<input <% if(dsi.getValidationSecrConf().equals("oui")) { %> checked <% } %> 
															<% if(!privilege.isDsiDirection() || !dsi.getValidationSecrConf().equals("none")) { %> disabled <% } %>
															type="radio" name="validationSecrConf" value="oui"> oui
													
														<input <% if(dsi.getValidationSecrConf().equals("non")) { %> checked <% } %> 
															<% if(!privilege.isDsiDirection() || !dsi.getValidationSecrConf().equals("none")) { %> disabled <% } %>
															type="radio" name="validationSecrConf" value="non"> non
													</td>
													<td>
														
															<% if(dsi.getValidationSecrConf().equals("non")) { %>
																
															<% } else { %>
																	<input size="8" <% if(!privilege.isDsiDirection() || !dsi.getValidationSecrConf().equals("none")) { %> disabled <% } %>
																	type="text" name="dateExecSouhaiteeSecrConf" 
																	value="${dsi.dateExecSouhaiteeSecrConfAffichage}"/>
															<% } %>
														<% if(privilege.isDsiDirection() && dsi.getValidationSecrConf().equals("none")) { %> 
															<script language="JavaScript">
																new tcal ({
																	'formname': 'formu',
																	'controlname': 'dateExecSouhaiteeSecrConf'
																});
															</script>
														<% } %> 
													</td>
												</tr>
											<% } %>
											<% if(!dsi.getValidationInfor().equals("none")) { %>
												<tr>
													<td colspan="3"><hr></td>
												</tr>
												<tr>
													<td colspan="3" align="center">
														<% if(dsi.getValidationSecrConf().equals("oui") && dsi.getDateExecEffective() == null && (privilege.isDsiInfo() || privilege.isDsiDirection())) { %>
														<form method="post" name="modEcheance">
															<input size="8" type="text" name="dateModifEcheance" value=""/>
															<script language="JavaScript">
																new tcal ({
																	'formname': 'formu',
																	'controlname': 'dateModifEcheance'
																});
															</script>
															<input type="submit" name="action" value="Modifier échéance"/>
														</form>
														
														<%} %>
													</td>
												</tr>
												<% if(dsi.getValidationSecrConf().equals("oui")) { %>
													<% if(dsi.getDateExecEffective() != null || privilege.isDsiInfo()) { %>
														<tr>
															<td colspan="3"><hr></td>
														</tr>
														<tr>
															<td align="right">Exécution : </td>
															<td></td>
															<td>
																<input size="8" <% if(!privilege.isDsiInfo() || dsi.getDateExecEffective() != null) { %> disabled <% } %>
																	type="text" name="dateExecEffective" value="${dsi.dateExecEffective}"/>
																<% if(privilege.isDsiInfo() && dsi.getDateExecEffective() == null) { %> 
																	<script language="JavaScript">
																		new tcal ({
																			'formname': 'formu',
																			'controlname': 'dateExecEffective'
																		});
																	</script>
																<% } %> 
															</td>
														</tr>
														
														<tr>
															<td colspan="3"><hr></td>
														</tr>
													<% } %>
												<% } %>
											<% } %>
										<% } %>
									</table>
								</td>
							</tr>
							<% 
							if(privilege.isDsiInfo()) {
							%>
								<tr>
									<td align="right">n° GTI : </td>
									<td>
										<c:forEach items="${dsigti}" var="gti">
				                           > <a href="gestionTache.admin?idPlanning=${gti.idPlanning}">${gti.idPlanning}</a>
				                        </c:forEach>
										
									</td>
								</tr>
							<% 
							}
							%>
							<tr>
								<td align="right">date demande : </td>
								<td>
									<input disabled="disabled" type="text" name="dateDemande" value="${dsi.dateDemande}"/>
								</td>
							</tr>
							<tr>
								<td align="right">auteur demande : </td>
								<td>
									<input disabled="disabled" type="text" size="30" maxlength="200" name="titre" value="${dsi.nom} ${dsi.prenom}"/>
								</td>
							</tr>
							<tr>
								<td align="right">type de demande : </td>
								<td>
									<select disabled="disabled" name="typeDemande" size="1" />
											<option >${dsi.typeDemande}</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">responsable : </td>
								<td>
									<input disabled="disabled" type="text" size="30" maxlength="200" name="titre" value="${dsi.nomChef} ${dsi.prenomChef}"/>
								</td>
							</tr>
							<tr>
								<td align="right">service concerné : </td>
								<td>
									<select disabled="disabled" name="service" size="1" />
											<option >${dsi.service}</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right">échéance souhaitée: </td>
								<td>
									<input disabled="disabled" type="text" name="echeanceSouhaitee" value="${dsi.dateExecSouhaitee}"/>
								</td>
							</tr>
							<tr>
								<td align="right">Titre : </td>
								<td><input disabled="disabled" type="text" size="79" maxlength="200" name="titre" value="${dsi.titre}"/></td>
							</tr>
							<tr>
								<td align="right">description : </td>
								<td><textarea disabled="disabled" cols="80" rows="20" name="description"/>${dsi.description}</textarea></td>
							</tr>
							
							<%
								if(dsi.getRemarqueEcheance() != null) {
							%>
								<tr>
									<td align="right">remarques sur échéance : </td>
									<td><textarea disabled="disabled" cols="80" rows="5" name="remarqueEcheance"/>${dsi.remarqueEcheance}</textarea></td>
								</tr>
							<%
								}
							%>
							<tr>
								<td></td>
								<td align="right"></td>
							</tr>
						</table>
						<input type="hidden" name="idDemandes" value="${dsi.idDemandes}"/>
						</form>
					</td>
					<td background="images/administration/cadre/area_right.gif"></td>
				</tr>
				<tr>
					<td background="images/administration/cadre/area_left.gif"></td>
					<td>
						<table width="100%">
							<tr>
								<td><a href="${retour}"><img alt="retour" src="images/administration/retour.jpg" border="0"></a></td>
								<td align="right">
								<% if(!privilege.isDsiUser()) { %> <input type="submit"  name="action" value="Enregistrer"/></td> <%} %>
								<input type="hidden" name="action" value="modifier"/>
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
				<tr>
					<td colspan="2"><br></td>
				</tr>
			</table>
		</form>
		
		<% if( (privilege.isDsiInfo() || privilege.isDsiDirection())  ) { %>
			<table cellpadding="0" cellspacing="0"  class="tableau_container" width="1120px">
				<tr height="25">
					<td width="50%"></td>
					<% if(privilege.isDsiInfo()) { %>
						<td align="center">
							<form method="get" action="formulaireTacheAjouter.admin" target="_blank">
								<input type="submit" value="Créer une tâche"/>
								<input type="hidden" name="nomFormulaire" value="${dsi.nom}">
								<input type="hidden" name="prenomFormulaire" value="${dsi.prenom}">
								<input type="hidden" name="serviceFormulaire" value="${dsi.service}">
								<input type="hidden" name="echeanceFormulaire" value="${dsi.dateExecSouhaiteeSecrConfAffichage}">
								<input type="hidden" name="titreFormulaire" value="${dsi.titre}">
								<input type="hidden" name="descriptionFormulaire" value="<a href=&quot;afficherDemande.dsi?idDemandes=${dsi.idDemandes}&quot;>voir demande DSI n° ${dsi.idDemandes}</a><br><br><%= dsi.getDescription().replaceAll("\"", "&quot;") %>">
								<input type="hidden" name="idDemande" value="${dsi.idDemandes}">
								<input type="hidden" name="typeDemande" value="${dsi.typeDemande}">
								<%
								//System.out.println(dsi.getDescription().replaceAll("\"", "&quot;"));
								%>
							</form> 			
						</td>
						<% if(privilege.isDsiInfo()) { %> 
						<td>&nbsp;&nbsp;&nbsp;</td>
						<td>
							<input onclick="associerTache()" type="button" value="Associer tâche"/>
						</td>
					<% } %>
					<% if(privilege.isDsiInfo()) { %> 
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>
						<input onclick="supAssoTache()" type="button" value="Dissocier tâche"/>
					</td>
					<% } %>
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>
						<form method="get" action="splitDemande.dsi">
							<input type="submit" value="Split demande"/>
							<input type="hidden" name="idDemande" value="${dsi.idDemandes}">
						</form>
					</td>
					<!-- TI8608 -->
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>
						<form method="get" action="utilsTache.admin">
							<input type="submit" value="Notif au DG"/>
							<input type="hidden" name="idDemande" value="${dsi.idDemandes}"/>
							<input type="hidden" name="action" value="notifDG">
						</form>
					</td>
					
					<% if(privilege.isDsiInfo() && (dsi.getValidationSecr().equals("none"))) { %> 
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>
						<form method="get" action="utilsTache.admin">
							<input type="submit" value="Forcer accord DG"/>
							<input type="hidden" name="idDemande" value="${dsi.idDemandes}"/>
							<input type="hidden" name="action" value="accordDG">
						</form>
					</td>
					<% } %>
					
					<% } %>	
					<td>&nbsp;&nbsp;&nbsp;</td>		
					<td>
						<form method="get" action="transmettre.dsi"  target="_blank">
							<input type="submit" value="Transmettre à un tiers"/>
							<input type="hidden" name="idDemande" value="${dsi.idDemandes}">
							
						</form>
					</td>
					 
					<% if(privilege.isDsiDirection()) { %> 
					<td>&nbsp;&nbsp;&nbsp;</td>
					<td>
						<form method="get" action="utilsTache.admin">
							<input type="submit" value="Demande avis au Service Informatique"/>
							<input type="hidden" name="idDemande" value="${dsi.idDemandes}"/>
							<input type="hidden" name="action" value="avisInfo">
						</form>
					</td>
					<% } %>
					<td width="50%"></td>
				</tr>
			</table>
		<% } %>	
		<table cellpadding="0" cellspacing="0"  class="tableau_container" width="1120px">
			<tr height="25">
				<td></td>
				<td>
					<table>
						<tr>
							<td class="titre_tableau">Annexe(s) : </td>
							<td></td>
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
			<%
			String[] annexes = (String[])request.getAttribute("annexes");
			
			if(annexes.length == 0) {
			%>
				<tr>
					<td background="images/administration/cadre/area_left.gif"></td>
					<td>Pas d'annexe pour cette demande</td>
					<td background="images/administration/cadre/area_right.gif"></td>
				</tr>
			<% } else { 
				for (int i = 0; i < annexes.length; i++) { %>
				<tr>
					<td background="images/administration/cadre/area_left.gif"></td>
					<td>
						<a href="index.regTrav?param=DSI_annexe&fichier=<%= annexes[i] %>&idDemande=${dsi.idDemandes}"><%= i + 1 %>) <%= annexes[i] %></a>
						&nbsp;
						<a href="afficherDemande.dsi?idDemandes=${dsi.idDemandes}&action=supprimerAnnexe&numAnnexe=<%= i %>">
							<img width="15px" alt="supprimer" src="images/refuse.png" border="0">
						</a>		
					</td>
					<td background="images/administration/cadre/area_right.gif"></td>
				</tr>
				<% 	}
			} %>
			<tr>
				<td background="images/administration/cadre/area_left.gif"></td>
				<td>
					&nbsp;
				</td>
				<td background="images/administration/cadre/area_right.gif"></td>
			</tr>
			<tr>
				<td background="images/administration/cadre/area_left.gif"></td>
				<td>
					&nbsp;
				</td>
				<td background="images/administration/cadre/area_right.gif"></td>
			</tr>
			<tr>
				<td background="images/administration/cadre/area_left.gif"></td>
				<td>
					<form method="post" name="fannexe" enctype="multipart/form-data" >
						Ajouter une annexe : 
						<input type="file" name="annexe" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit" value="Ajouter"/>
						<input type="hidden" name="idDemandes" value="${dsi.idDemandes}">
						<input type="hidden" name="action" value="ajouterAnnexe">
					</form>
				</td>
				<td background="images/administration/cadre/area_right.gif"></td>
			</tr>
			<tr height="25">
				<td background="images/administration/cadre/area_bottom_left.gif"></td>
				<td background="images/administration/cadre/area_bottom.gif"></td>
				<td background="images/administration/cadre/area_bottom_right.gif"></td>
			</tr>
			<tr>
				<td colspan="2"><br></td>
			</tr>
		</table>
		<table cellpadding="0" cellspacing="0"  class="tableau_container"  width="1120px">
			<tr height="25">
				<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td>
					<table>
						<tr>
							<td class="titre_tableau">Remarques : </td>
							<td></td>
						</tr>
					</table>
				</td>
				<td></td>
			</tr>
			<%
				List<Remarque> liste = (List<Remarque>)request.getAttribute("remarques");
				for(int i = 0; i < liste.size(); i++) {
					Remarque item = liste.get(i);
			%>		
				<tr>
					<td colspan="3">
						<table width="100%">
							<tr></tr>
							<tr>
								<td width="100%">
									<table width="100%" cellpadding="0" cellspacing="0">
										<tr height="25">
											<td width="22" background="images/cadre/area_top_left.gif"></td>
											<td background="images/cadre/area_top.gif"></td>
											<td width="22" background="images/cadre/area_top_right.gif"></td>
										</tr>
										<tr>
											<td background="images/cadre/area_left.gif"></td>
											<td>
												<%= item.getTexte().replaceAll("\r\n", "<br>") %>
											</td>
											<td background="images/cadre/area_right.gif"></td>
										</tr>
										<tr height="25">
											<td background="images/cadre/area_bottom_left.gif"></td>
											<td background="images/cadre/area_bottom.gif"></td>
											<td background="images/cadre/area_bottom_right.gif"></td>
										</tr>
									</table>
								</td>
								<td align="right" style="font-size: 10pt">
									<NOBR>
										<%
											if(i == liste.size()-1) {
										%>
											<a name="fin">ajouté</a>
										<%
											} else {
										%>
											ajouté
										<%											
											}
										%>
											 le <%= item.getDate() %> par <%= item.getUser() %>
									</NOBR>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			<%		
				}
			%>
			<tr>
				<td></td>
				<td>
					<form method="get" action="afficherDemande.dsi">
						<textarea id="zonedetexte" cols="80" rows="10" name="remarque"/></textarea>
						<input type="submit" value="ajouter remarque"/>
						<input type="hidden" name="idDemandes" value="${dsi.idDemandes}"/>
					</form>
					<% if(privilege.isDsiInfo()) { %>
						<input onclick="ajoutLien()" type="button" value="ajouter lien"/>
						<input onclick="ajoutLienDSI()" type="button" value="ajouter lien DSI"/>
						<input onclick="ajoutLienTHI()" type="button" value="ajouter lien THI"/>
					<% } %>
				</td>
				<td valign="bottom" align="right">
					<a href="#hautdepage"><img width="50px" alt="retour" src="images/up.png" border="0"></a>
				</td>
			</tr>
		</table>
		<balise id="basdepage"></balise>
		<jsp:include page="../basDePage.jsp" ></jsp:include>
	</body>
</html>