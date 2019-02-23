function parseResultsFromJSON(tournament) {
  tournament += ".json";
  console.log("Requesting JSON from " + tournament);

  $.getJSON(tournament, function (data) {
    console.log("Generating table for " + data.name + " " + data.year);

    var table = $("#results-table")
    console.log(table.find("tbody").remove());
    data.match.forEach(function (match) {
      var tbody = $("<tbody>");

      match.player.forEach(function (player) {
        var row = $("<tr>").addClass(player.outcome);
        row.append($("<td>").html(match.round));
        row.append($("<td>").addClass("name").html(player.name));

        player.set.forEach(function (set) {
          row.append($("<td>").text(set));
        });

        tbody.append(row);
      });

      table.append(tbody);
    });
  });
}

function formQueryOnSubmit(e) {
  console.log("Form submit");

  var eleTour = document.getElementById("tournament");
  parseResultsFromJSON(eleTour.options[eleTour.selectedIndex].value);

  e.preventDefault();
}

window.addEventListener('load', function () {
  console.log("Window onload");
  document.getElementById("form-query").addEventListener("submit", formQueryOnSubmit);
});
