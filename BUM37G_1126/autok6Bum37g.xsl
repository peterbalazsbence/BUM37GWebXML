<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="autokBum37g.xml"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:key name="varosKey" match="auto" use="normalize-space(tulaj/varos)"/>

  <xsl:template match="/autok">
    <varosok>
      <xsl:for-each select="auto[generate-id() = generate-id(key('varosKey', normalize-space(tulaj/varos))[1])]">
        <xsl:sort select="normalize-space(tulaj/varos)"/>
        <varos>
          <nev><xsl:value-of select="normalize-space(tulaj/varos)"/></nev>
          <db><xsl:value-of select="count(key('varosKey', normalize-space(tulaj/varos)))"/></db>
          <osszar>
            <xsl:value-of select="sum(key('varosKey', normalize-space(tulaj/varos))/ar)"/>
          </osszar>
        </varos>
      </xsl:for-each>
    </varosok>
  </xsl:template>

</xsl:stylesheet>
