<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nouveau R�glement de travail</title>
<link rel="stylesheet" href="site.css" type="text/css">
<style type="text/css">
#gauche {
	float: left; /* Le cadre sort du flux */
	/* border: 2px solid #DAA520; */ //
	background-color: #DAA520;
	width: 595px;
}

#droite {
	float: right; /* ou margin-left:202px;  */
	/* border: 2px solid gray; */ //
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
		<img height="12px" src="images/fleche_droite.jpg" border="0" alt=" - "><a class="menu_contextuel">Nouveau r�glement de travail</a>
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
								<td><a class="texte_menu_principal">R�glement de travail du personnel<br></a></td>
								<td>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=rt" target="_blank">T�l�charger le nouveau R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe01" target="_blank">T�l�charger l'annexe 1 (Cong�s des agents) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe02" target="_blank">T�l�charger l'annexe 2 (Horaires flottant) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe03" target="_blank">T�l�charger l'annexe 3 (Horaires fixes) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe04" target="_blank">T�l�charger l'annexe 4 (Horaires variables) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe05" target="_blank">T�l�charger l'annexe 5 (Horaires � temps partiel) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe06" target="_blank">T�l�charger l'annexe 6 (Horaires d�cal� en cas de circonstances) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe07" target="_blank">T�l�charger l'annexe 7 (Contr�le du temps de travail) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe08" target="_blank">T�l�charger l'annexe 8 (T�l�travail) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe09" target="_blank">T�l�charger l'annexe 9 (Formations) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe10" target="_blank">T�l�charger l'annexe 10 (Evaluation) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe11" target="_blank">T�l�charger l'annexe 11 (Contr�le m�dical) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe12" target="_blank">T�l�charger l'annexe 12 (Surveillance de la sant�) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe13" target="_blank">T�l�charger l'annexe 13 (accident de travail et maladie) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe14" target="_blank">T�l�charger l'annexe 14 (pr�vention et protection au travail) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe14b" target="_blank">T�l�charger l'annexe 14bis (formulaire d�pendance) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe15" target="_blank">T�l�charger l'annexe 15 (droits et obligation des syst�mes d'information) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe16" target="_blank">T�l�charger l'annexe 16 (utilisation BCSS) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe17" target="_blank">T�l�charger l'annexe 17 (Charte Wallone diversit�) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=annexe18" target="_blank">T�l�charger l'annexe 18 (Contact utiles) au R�glement de Travail</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=statutadm" target="_blank">T�l�charger les statuts administratif</a>
									</p>
									<p class="texte_informatif_menu_principal">
										<img src="./images/pdf.png" width="30px"></img><a class="texte_menu_principal" href="indexrt.regTrav?param=statutpec" target="_blank">T�l�charger les statuts p�cuniaires</a>
									</p>
									
									<p>
										Si votre ordinateur n'est pas �quip� d'une version suffisamment r�cente d'acrobat reader, veuillez utiliser le lien suivant: <img src="./images/pdf.png" width="20px"></img><a style="color: black;" href="index.regTrav?param=normal" target="_blank">T�l�charger</a>
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