<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouveau Règlement de travail</title>
<link rel="stylesheet" href="site.css" type="text/css">
<style type="text/css">
#gauche {
	float: left; /* Le cadre sort du flux */
	/* border: 2px solid #DAA520; */
	background-color: #DAA520;
	width: 595px;
}

#droite {
	float: right; /* ou margin-left:202px;  */
	/* border: 2px solid gray; */
	background-color: gray;
	width: 595px;
}

#principal {
	width: 1200px;
	/* border: 2px solid gray; background-color : green; */
	margin-left: auto;
	margin-right: auto;
	background-color: green;
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
		<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel" href="index.htm?rep=personnelCPAS">Personnel du CPAS</a>
		<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">Nouveau règlement de travail</a>
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
								<td width="60px"><a href="index.regTrav?param=lots"><img src="images/travail.png" border="0" width="60px"></a></td>
								<td><a class="texte_menu_principal">Règlement de travail du personnel<br></a></td>
								<td>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=rt" target="_blank">Télécharger le nouveau Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe01" target="_blank">Télécharger l'annexe 1 (Congés des agents) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe02" target="_blank">Télécharger l'annexe 2 (Horaires flottant) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe03" target="_blank">Télécharger l'annexe 3 (Horaires fixes) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe04" target="_blank">Télécharger l'annexe 4 (Horaires variables) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe05" target="_blank">Télécharger l'annexe 5 (Horaires à temps partiel) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe06" target="_blank">Télécharger l'annexe 6 (Horaires décalé en cas de circonstances) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe07" target="_blank">Télécharger l'annexe 7 (Contrôle du temps de travail) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe08" target="_blank">Télécharger l'annexe 8 (Télétravail) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe09" target="_blank">Télécharger l'annexe 9 (Formations) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe10" target="_blank">Télécharger l'annexe 10 (Evaluation) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe11" target="_blank">Télécharger l'annexe 11 (Contrôle médical) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe12" target="_blank">Télécharger l'annexe 12 (Surveillance de la santé) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe13" target="_blank">Télécharger l'annexe 13 (accident de travail et maladie) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe14" target="_blank">Télécharger l'annexe 14 (prévention et protection au travail) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe14b" target="_blank">Télécharger l'annexe 14bis (formulaire dépendance) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe15" target="_blank">Télécharger l'annexe 15 (droits et obligation des systèmes d'information) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe16" target="_blank">Télécharger l'annexe 16 (utilisation BCSS) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe17" target="_blank">Télécharger l'annexe 17 (Charte Wallone diversité) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe18" target="_blank">Télécharger l'annexe 18 (Contact utiles) au Règlement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=statutadm" target="_blank">Télécharger les statuts administratif</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=statutpec" target="_blank">Télécharger les statuts pécuniaires</a>
									</p>
									
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
		</div>
	</div>
	<jsp:include page="basDePage.jsp"></jsp:include>
</body>
</html>