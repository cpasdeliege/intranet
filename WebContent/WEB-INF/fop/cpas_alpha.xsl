<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
	<xsl:template match="/">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" 
					xmlns:fox="http://xml.apache.org/fop/extensions">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="all"
										page-height="29.7cm" 
										page-width="21cm"
										margin-top="1cm" 
										margin-bottom="1cm" 
										margin-left="2.5cm" 
										margin-right="2.5cm">
					<fo:region-body margin-top="2cm" margin-bottom="2cm"/>
					<fo:region-before extent="2cm"/>
					<fo:region-after extent="2cm"/>
				</fo:simple-page-master>
			</fo:layout-master-set>
			
			<fo:page-sequence master-reference="all">
				<fo:static-content flow-name="xsl-region-before">
					<xsl:call-template name="entete"/>	
				</fo:static-content> 
				<fo:static-content flow-name="xsl-region-after">
					<xsl:call-template name="basDePage"/>
				</fo:static-content> 
				<fo:flow flow-name="xsl-region-body">
					<fo:block>
						<xsl:call-template name="miseEnPage"/>
					</fo:block>
        </fo:flow>
			</fo:page-sequence>
		</fo:root> 
	</xsl:template>
	
	<xsl:template name="entete">
		<fo:block text-align="center" 
					font-size="8pt" 
					line-height="10pt"
					border-bottom="#D1D7DC"
					border-bottom-width="1pt"
					padding-top="2pt"
					padding-right="2pt"
					padding-left="2pt"
					padding-bottom="2pt">
					CPAS de Liège - Annuaire Interne 
		</fo:block>
	</xsl:template>
	
	<xsl:template name="miseEnPage">
		<xsl:apply-templates select="cpas"/>
	</xsl:template>
	
	<xsl:template match="cpas">
		<xsl:apply-templates select="listeAlphabetique"/>
	</xsl:template>
	
	<xsl:template match="listeAlphabetique">	
		<fo:block space-after="50pt"></fo:block>
		<fo:external-graphic src="file:///c:/fop/cpas.jpg"/>
		<fo:block text-align="center" font-size="42pt" space-before="40pt">
			Annuaire Interne
		</fo:block>
		<fo:block text-align="center" font-size="26pt" space-before="40pt">
			- ordre alphabétique -
		</fo:block>
		<fo:block text-align="center" font-size="14pt" space-before="250pt" break-after="page">
			- généré le <xsl:value-of select="date"/> -
		</fo:block>
		<xsl:apply-templates select="lettre"/>
	</xsl:template>
		
	<xsl:template match="lettre">
		<fo:block font-size="48pt" text-decoration="underline" space-before="20pt">
			<xsl:value-of select="alpha"/>
		</fo:block>
		<fo:block space-before="5pt">
			<fo:table table-layout="fixed" width="80%">
				<fo:table-column column-width="proportional-column-width(3)"/>
				<fo:table-column column-width="proportional-column-width(3)"/>
				<fo:table-column column-width="proportional-column-width(2)"/>
				<fo:table-body>
					<xsl:apply-templates select="employe"/>
				</fo:table-body>
			</fo:table>	
		</fo:block>
	</xsl:template>
	
	<xsl:template match="employe">
		<fo:table-row height="14pt">
			<fo:table-cell font-size="12pt" border-width="1pt">
				<fo:block>
					<xsl:value-of select="nom"/><xsl:text> </xsl:text><xsl:value-of select="prenom"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell font-size="12pt"	border-width="1pt">
				<fo:block>
					<xsl:value-of select="service"/>
				</fo:block>
			</fo:table-cell>
			<fo:table-cell font-size="12pt" text-align="right"	border-width="1pt">
				<fo:block>
					<xsl:value-of select="tel"/>
				</fo:block>
			</fo:table-cell>
		</fo:table-row>	
	</xsl:template>

	
	<xsl:template name="basDePage">
		
		<fo:block text-align="right" 
					font-size="8pt" 
					line-height="10pt"
					border-top="#D1D7DC"
					border-top-width="1pt"
					padding-top="2pt"
					padding-right="2pt"
					padding-left="2pt"
					padding-bottom="2pt">
			<fo:block text-align="center">
			Ce répertoire est destiné à un usage strictement interne.
		</fo:block>
			<fo:block>
				page <fo:page-number/>
			</fo:block>
		</fo:block>
	</xsl:template>
</xsl:stylesheet>
