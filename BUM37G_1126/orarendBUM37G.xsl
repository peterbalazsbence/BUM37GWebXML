<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" indent="yes" />

    <xsl:template match="/XY_orarend">
        <html>
            <head>
                <title>XY Órarend – 2025. I. félév</title>
            </head>

            <body>
                <h2>XY Órarend – 2025. I. félév</h2>

                <table border="1" cellpadding="5" style="border-collapse: collapse;">
                    <tr style="background-color:#d0f0c0; font-weight:bold;">
                        <th>ID</th>
                        <th>Tárgy</th>
                        <th>Nap</th>
                        <th>Időpont</th>
                        <th>Helyszín</th>
                        <th>Oktató</th>
                        <th>Szak</th>
                        <th>Típus</th>
                    </tr>

                    <xsl:for-each select="ora">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="targy"/></td>
                            <td><xsl:value-of select="idopont/@nap"/></td>
                            <td>
                                <xsl:value-of select="concat(idopont/@tol, ':00-', idopont/@ig, ':00')"/>
                            </td>
                            <td><xsl:value-of select="helyszin"/></td>
                            <td><xsl:value-of select="oktato"/></td>
                            <td><xsl:value-of select="szak"/></td>
                            <td><xsl:value-of select="@tipus"/></td>
                        </tr>
                    </xsl:for-each>

                </table>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
