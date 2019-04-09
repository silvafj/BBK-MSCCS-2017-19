<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Oscars nominations</title>
    <!-- I confirm that this coursework submission is entirely my own work, except where explicitly stated otherwise. -->
    <meta name="author" content="Fernando de Almeida da Silva (fdealm02)">
    <link rel="stylesheet" href="style.css">
  </head>
  <body>
<?php

$xml = new DOMDocument;
$xml->load('oscars.xml');

$xsl = new DOMDocument;
$xsl->load('oscars.xsl');

$processor = new XSLTProcessor;
$processor->importStyleSheet($xsl);

$html = $processor->transformToDoc($xml);
print $html->saveXML();

?>
</body>
</html>
