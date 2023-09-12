<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="be.cpasdeliege.intranet.informatique.model.*"%>
<%
PrivilegeInformatique privilege = (PrivilegeInformatique)request.getSession().getAttribute("privilegeInformatique");
if(privilege == null) {
	privilege = new PrivilegeInformatique();
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intranet CPAS de Liège</title>
<link rel="stylesheet" href="site.css" type="text/css">
<style type="text/css">
#gauche {
	float: left; /* Le cadre sort du flux */
	/* border: 2px solid #DAA520; */
	width: 595px;
}

#droite {
	float: right; /* ou margin-left:202px;  */
	/* border: 2px solid gray; */
	width: 595px;
}

#principal {
	width: 1200px;
	/* border: 2px solid gray; background-color : green; */
	margin-left: auto;
	margin-right: auto;
}

.tabIndex {
	width: 95%;
}
</style>
</head>
<body class="body">
	<jsp:include page="entete.jsp"></jsp:include>
	<div>
		<hr>
		&nbsp;<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm">accueil</a>
<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">Personnel du CPAS</a>
		<hr>
	</div>
	<div id="principal">
<div id="gauche">

<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><a href="index.regTrav?param=lots"><img src="images/strategique.png" border="0" width="60px"></a></td>
					<td><a class="texte_menu_principal">Plan Stratégique</a></td>
					<td width="70%">
						<!-- <p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=etat" target="_blank">Etat des lieux (mai 2013)</a></p>-->
						<!--  <p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=tableau" target="_blank">Tableau de bord (juillet 2013)</a></p>-->
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=presentation" target="_blank">Plan stratégique 2019-24</a></p>
						<p class="texte_informatif_menu_principal"></p>
						
					</td>
					<td align="right" valign="bottom"></td>
				</tr>
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>



<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><a href="index.regTrav?param=lots"><img src="images/travail.png" border="0" width="60px"></a></td>
					<td><a class="texte_menu_principal">Règlement de travail du personnel<br></a></td>
					<td>
						<p class="texte_informatif_menu_principal">
							<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="index.regTrav?param=lots" target="_blank">Télécharger le Règlement de Travail</a>
						</p>
						<!-- <p class="texte_informatif_menu_principal">
							<a class="texte_menu_principal" href="index.htm?rep=rtcite">Nouveau Règlement de travail (entre en application le 01/01/2023)</a>
						</p> -->
						<p>
							Si votre ordinateur n'est pas équipé d'une version suffisamment récente d'acrobat reader, veuillez utiliser le lien suivant: <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=normal" target="_blank">Télécharger</a>
						</p>
						<p class="texte_informatif_menu_principal">
							
						</p>
					</td>
					<td align="right" valign="bottom"></td>
				</tr>
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>
<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><a href="index.regTrav?param=lots"><img src="images/RGPD.jpg" border="0" width="60px"></a></td>
					<td><a class="texte_menu_principal">Règlement général sur la protection des données<br></a></td>
					<td>
						<p class="texte_informatif_menu_principal">
							<img src="./images/pdf.png" width="30px"></img>
							<a class="texte_menu_principal" href="index.regTrav?param=rgpdPPDCP" target="_blank">Politique de protection de vos données personnelles</a>
						</p>
						<p class="texte_informatif_menu_principal">
							<img src="./images/pdf.png" width="30px"></img>
							<a class="texte_menu_principal" href="index.regTrav?param=rgpdPVDCP" target="_blank">Procédure à suivre en cas de violation de vos données personnelles</a>
						</p>
						<p class="texte_informatif_menu_principal">
							<img src="./images/pdf.png" width="30px"></img>
							<a class="texte_menu_principal" href="index.regTrav?param=rgpdFED" target="_blank">Formulaire de demande d'exercice des droits sur vos données personnelles</a>
						</p>
						
						<p class="texte_informatif_menu_principal"></p>
					</td>
					<td align="right" valign="bottom"></td>
				</tr>
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>



<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/SSC.png" border="0" width="60px"></td>
					<td><a class="texte_menu_principal">Service social collectif</a></td>
					<td  width="70%">
					<!-- <p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=sscNS" target="_blank">Note de service n°776</a></p> -->
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=sscNSC" target="_blank">Note de service n°900: changement de personne de contact</a></p>
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=sscPSSC" target="_blank">Les primes du Service social collectif</a></p>
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=sscPAR" target="_blank">�Mon SSC � : la plateforme d''avantages et de réductions</a></p>
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="https://www.sscgsd.fgov.be/fr/beneficiary/Document%20Management/Handicap.pdf" target="blank">Intervention en faveur d'un enfant handicapé</a></p>
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="https://www.sscgsd.fgov.be/fr/beneficiary/Document%20Management/MariageCohabitation.pdf" target="_blank">Prime de mariage et de cohabitation</a></p>
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="https://www.sscgsd.fgov.be/fr/beneficiary/Document%20Management/NaissanceAdoption.pdf" target="_blank">Prime de naissance et adoption</a></p>
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="https://www.sscgsd.fgov.be/fr/beneficiary/Document%20Management/Pension.pdf" target="_blank">Prime de mise à la pension</a></p>
						
						<p class="texte_informatif_menu_principal">
							
						</p>
					</td>
					<td align="right" valign="bottom"></td>
				</tr>
				
												
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>







</div>

<div id="droite">

<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/certificat.png" border="0" width="60px"></td>
					<td><a class="texte_menu_principal">Ressources</a></td>
					<td  width="70%">
						<p>
							- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=certificat" target="_blank">Certificat médical</a>
						</p>
						<p>
							- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=vmIAS" target="_blank">Vade-Mecum : Action Sociale</a>
							<% if(privilege.isAdmVM()) { %>
							<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
								<input type="hidden" name="action" value="modifierVMAction">
								<input type="file" name="fichier" accept=".pdf"/>
								<input type="submit" value="Modifier"/>
							</form>
							<% } %>
						</p>
						<p>
							- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=memoCite" target="_blank">Mémo utilisateur Cité Administrative</a>
						</p>
						<!--
						<p>
							- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=vmAideSociale" target="_blank">Vade-Mecum : Aide Sociale</a>
							<% if(privilege.isAdmVM()) { %>
							<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
								<input type="hidden" name="action" value="modifierVMAide">
								<input type="file" name="fichier" accept=".pdf"/>
								<input type="submit" value="Modifier"/>
							</form>
							<% } %>
						</p>
						<p>
							- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=vmMado" target="_blank">Vade-Mecum : Maintien à Domicile</a>
							<% if(privilege.isAdmVM()) { %>
							<form method="post" enctype="multipart/form-data" action="utilsTache.admin">
								<input type="hidden" name="action" value="modifierVMMaintien">
								<input type="file" name="fichier" accept=".pdf"/>
								<input type="submit" value="Modifier"/>
							</form>
							<% } %>
						</p>
						-->
						<p class="texte_informatif_menu_principal">
							
						</p>
					</td>
					<td align="right" valign="bottom"></td>
				</tr>
				
												
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>

<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td>
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><a href="file:///P:/Infor%20Jeunes/info.html"><img src="images/infoJ.png" border="0" width="60px"></a></td>
					<td><a class="texte_menu_principal" target="_blank" href="file:///P:/Infor%20Jeunes/info.html">Les Fiches Infor Jeunes</a> (nécéssite Internet Explorer)</td>
					<td><p class="texte_informatif_menu_principal"></p></td>
					<td align="right" valign="bottom"></td>
				</tr>
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>




<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td> 
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<td width="60px"><img src="images/amicale.png" border="0" width="60px"></td>
					<td><a class="texte_menu_principal">Amicale du Personnel</a></td>
					<td width="70%">
						<p>
							- <img src="./images/pdf.png" width="20px"></img>
							<a style="color: black;" href="index.regTrav?param=ethias" target="_blank">Avantages Ethias 2021</a>
						</p>
						<p>- <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=ethiasPromo" target="_blank">Promotions Ethias 2022</a></p>
						<p class="texte_informatif_menu_principal">
							
						</p>
					</td>
					<td align="right" valign="bottom"></td>
				</tr>
				
												
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>

<!--  ********************************************* -->
<!-- ajoute par Théo le 2021-02-19 -->

<table class="tabIndex" align="center" cellpadding="0" cellspacing="0">
	<tr height="25">
		<td width="22" background="images/cadre/area_top_left.gif"></td>
		<td background="images/cadre/area_top.gif"></td>
		<td width="22" background="images/cadre/area_top_right.gif"></td> 
	</tr>
	<tr>
		<td background="images/cadre/area_left.gif"></td>
		<td>
			<table width="100%">
				<tr>
					<!-- <td width="60px"><img src="images/formulaire.png" border="0" width="60px"></td> -->
					<td width="80px"><a href="https://tinyurl.com/formulaire-archives-cpas-liege"  target="_blank"><img src="images/formulaire.png" border="0" width="80px"></a></td>
					 <td><a   href="https://tinyurl.com/formulaire-archives-cpas-liege" target="_blank" class="texte_menu_principal">Formulaire de demandes aux services Archives</a></td> 
					<!-- <td width="30%">
						<p>- <img src="./images/formulaire.png" width="20px"></img><a style="color: black;" href="https://tinyurl.com/formulaire-archives-cpas-liege" target="_blank">Formulaire de demandes aux services Archives</a></p>-
						<p class="texte_informatif_menu_principal">
							
						</p>
					</td> -->
					<td align="right" valign="bottom"></td>
				</tr>
				
												
			</table>
		</td>
		<td background="images/cadre/area_right.gif"></td>
	</tr>
	
	<tr height="25">
		<td background="images/cadre/area_bottom_left.gif"></td>
		<td background="images/cadre/area_bottom.gif"></td>
		<td background="images/cadre/area_bottom_right.gif"></td>
	</tr>
</table>
<br>

<!-- ********************************************* -->




</div>



</div>





	<jsp:include page="basDePage.jsp"></jsp:include>
</body>
</html>