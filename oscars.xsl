<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:param name="year"/>
<xsl:param name="category"/>
<xsl:param name="nominee"/>
<xsl:param name="info"/>
<xsl:param name="won"/>

<xsl:template match="/Oscars">
  <table>
    <thead>
      <tr>
        <th>Year</th>
        <th>Category</th>
        <th>Nominee</th>
        <th>Info</th>
        <th>Won?</th>
      </tr>
    </thead>
    <tbody>

    <xsl:for-each select="Nomination">
      <tr>
        <td class="year"><xsl:value-of select="Year"/></td>
        <td class="category"><xsl:value-of select="Category"/></td>
        <td class="nominee"><xsl:value-of select="Nominee"/></td>
        <td class="info"><xsl:value-of select="Info"/></td>
        <td class="won"><xsl:value-of select="Won"/></td>
      </tr>
    </xsl:for-each>

    </tbody>
  </table>
</xsl:template>

</xsl:stylesheet>
