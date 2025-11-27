<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="autokBum37g.xml"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:output method="xml" indent="yes"/>

  <xsl:template match="/autok">
    <lista>
      <xsl:for-each select="auto">
        <xsl:sort select="ar" data-type="number" order="ascending"/>
        <auto>
          <rendszam><xsl:value-of select="@rsz"/></rendszam>
          <ar><xsl:value-of select="ar"/></ar>
        </auto>
      </xsl:for-each>
    </lista>
  </xsl:template>

</xsl:stylesheet>
