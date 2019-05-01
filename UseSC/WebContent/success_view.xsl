<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!-- TODO: Auto-generated template -->
		<html>
		<head>
		<title>a viewer in MVC</title>
		</head>
		<body>
		<form>
		<xsl:attribute name="name"><xsl:value-of select="view/body/form/name"/></xsl:attribute>
		<xsl:attribute name="action"><xsl:value-of select="view/body/form/action"/></xsl:attribute>
		<xsl:attribute name="value"><xsl:value-of select="view/body/form/value"/></xsl:attribute>
		<xsl:value-of select="view/body/form/textView/label"/>
		<input>
		<xsl:attribute name="type">text</xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="view/body/form/textView/name"/></xsl:attribute>
		<!--  <xsl:attribute name="label:"><xsl:value-of select="form/textView/label"/></xsl:attribute>-->
		<xsl:attribute name="value"><xsl:value-of select="view/body/form/textView/value"/></xsl:attribute>
		</input><br>
		</br>
		<xsl:value-of select="view/body/form/ageView/label"/>
		<input>
		<xsl:attribute name="type">text</xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="view/body/form/ageView/name"/></xsl:attribute>
		<!--  <xsl:attribute name="label"><xsl:value-of select="form/textView/label"/></xsl:attribute>-->
		<xsl:attribute name="value"><xsl:value-of select="view/body/form/ageView/value"/></xsl:attribute>
		</input><br>
		</br>
		<input>
		<xsl:attribute name="type">submit</xsl:attribute> 
		<xsl:attribute name="name"><xsl:value-of select="view/body/form/buttonView/name"/></xsl:attribute>
		<!--<xsl:attribute name="label"><xsl:value-of select="form/buttonView/label"/></xsl:attribute> -->
		<xsl:attribute name="value"><xsl:value-of select="view/body/form/buttonView/label"/></xsl:attribute>
		</input>
		</form>
		</body>
		</html>
	</xsl:template>
</xsl:stylesheet>