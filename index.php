<?php

function is_select_selected($name, $value) {
  return $_GET[$name] == $value ? " selected" : "";
}

function build_form_fields() {
  $names = ['year', 'category', 'nominee', 'info'];
  foreach ($names as $name) {
    echo '<div class="form-item">
      <label for="'.$name.'">'.ucfirst($name).'</label>
      <select name="'.$name.'-condition">
        <option value="">Not restricted</option>
        <option value="1"'.is_select_selected("$name-condition", "1").'>Contains</option>
      </select>
      <input type="text" name="'.$name.'" value="'.$_GET[$name].'">
    </div>';
  }

  echo '<div class="form-item">
    <label for="won">Won?</label>
    <select name="won">
      <option value="">All</option>
      <option value="yes"'.is_select_selected("won", "yes").'>Yes</option>
      <option value="no"'.is_select_selected("won", "no").'>No</option>
    </select>
  </div>';
}

function apply_xslt_parameters($processor) {
  $names = ['year', 'category', 'nominee', 'info'];
  foreach ($names as $name) {
    $apply = $_GET["$name-condition"];
    if ($apply == "1") {
      $value = $_GET[$name];
      if ($value) {
        $processor->setParameter('', $name, $value);
      }
    }

    $value = $_GET["won"];
    if ($value == "yes" || $value == "no") {
      $processor->setParameter('', "no", $value);
    }
  }
}

function transform_xml_to_html() {
  $xml = new DOMDocument;
  $xml->load('oscars.xml');

  $xsl = new DOMDocument;
  $xsl->load('oscars.xsl');

  $processor = new XSLTProcessor;
  $processor->importStyleSheet($xsl);
  apply_xslt_parameters($processor);

  $html = $processor->transformToDoc($xml);
  echo $html->saveXML();
}
?>

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
      <?php build_form_fields() ?>
      <button type="submit">Query</button>
      </fieldset>
    </form>

    <?php transform_xml_to_html(); ?>

</body>
</html>
