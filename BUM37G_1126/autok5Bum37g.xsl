<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="autokBum37g.xml"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:key name="tipusKey" match="auto" use="normalize-space(tipus)"/>

  <xsl:template match="/autok">
    <tipus_lista>
      <xsl:for-each select="auto[generate-id() = generate-id(key('tipusKey', normalize-space(tipus))[1])]">
        <xsl:sort select="count(key('tipusKey', normalize-space(tipus)))" data-type="number" order="descending"/>
        <tipus>
          <nev><xsl:value-of select="normalize-space(tipus)"/></nev>
          <db><xsl:value-of select="count(key('tipusKey', normalize-space(tipus)))"/></db>
        </tipus>
      </xsl:for-each>
    </tipus_lista>
  </xsl:template>

</xsl:stylesheet>
