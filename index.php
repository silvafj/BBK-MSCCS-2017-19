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
    <form id="form-query">
      <fieldset><legend>Search Oscars nominations</legend>
      <div class="form-item">
        <label for="year">Year</label>
        <select name="year-condition">
          <option value="0">Not restricted</option>
          <option value="1">Contains</option>
        </select>
        <input type="text" name="year">
      </div>
      <div class="form-item">
        <label for="category">Category</label>
        <select name="category-condition">
          <option value="0">Not restricted</option>
          <option value="1">Contains</option>
        </select>
        <input type="text" name="category">
      </div>
      <div class="form-item">
        <label for="info">Info</label>
        <select name="info-condition">
          <option value="0">Not restricted</option>
          <option value="1">Contains</option>
        </select>
        <input type="text" name="info">
      </div>
      <div class="form-item">
        <label for="nominee">Nominee</label>
        <select name="nominee-condition">
          <option value="0">Not restricted</option>
          <option value="1">Contains</option>
        </select>
        <input type="text" name="nominee">
      </div>
      <div class="form-item">
        <label for="won">Won?</label>
        <select name="won">
          <option value="all">All</option>
          <option value="yes">yes</option>
          <option value="no">No</option>
        </select>
      </div>
      <button type="submit">Query</button>
      </fieldset>
    </form>
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
