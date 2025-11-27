<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="autokBum37g.xml"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/autok">
    <miskolc_autok>
      <xsl:for-each select="auto[tulaj/varos='Miskolc']">
        <rsz><xsl:value-of select="@rsz"/></rsz>
      </xsl:for-each>
    </miskolc_autok>
  </xsl:template>

</xsl:stylesheet>
