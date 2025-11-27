<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="autokBum37g.xml"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <osszes_elem>
      <xsl:value-of select="count(//*)"/>
    </osszes_elem>
  </xsl:template>

</xsl:stylesheet>
