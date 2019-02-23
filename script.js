// Filter tournament matches by player
function filterByPlayer(matches) {
  const playerFilters = {
    equals: function(player) {
      return player.name === playerValue;
    },
    contains: function(player) {
      return player.name.search(playerValue) > -1;
    },
    none: function(player) {
      return true;
    }
  };

  var playerValue = $("#player").val();
  if (playerValue) {
    const playerFilter = playerFilters[$("#player-condition").val()];
    console.log("Filter by player: " + playerFilter);

    return matches.filter(match => match.player.find(playerFilter));
  }

  return matches;
}

// Loads the JSON file and generates a table with the results
function parseResultsFromJSON(tournament) {
  tournament += ".json";
  console.log("Requesting JSON from " + tournament);

  // Let's have some fun with jQuery!
  $.getJSON(tournament, function(data) {
    console.log("Generating table for " + data.name + " " + data.year);

    // Apply the filters requested
    matches = data.match;
    matches = filterByPlayer(matches);

    // Generate the table with the results
    var table = $("#results-table");
    table.find("tbody").remove();

    matches.forEach(function(match) {
      var tbody = $("<tbody>");

      match.player.forEach(function(player) {
        var row = $("<tr>").addClass(player.outcome);
        row.append($("<td>").html(match.round));
        row.append(
          $("<td>")
            .addClass("name")
            .html(player.name)
        );

        player.set.forEach(function(set) {
          row.append($("<td>").text(set));
        });

        tbody.append(row);
      });

      table.append(tbody);
    });
  });
}

// Don't submit the form to the server and handle the submit from the client.
function formQueryOnSubmit(e) {
  console.log("Form submit");

  // Get the current form fields values and use them for the filtering.
  parseResultsFromJSON($("#tournament").val());

  // This prevents the form from being submitted to the server.
  e.preventDefault();
}

// After the page loads, attach events for client side processing
window.addEventListener("load", function() {
  console.log("Window onload");

  // Using pure JS with getElementById() just to show how it works!
  // getElementById("form-query") is similar, but less useful than
  // $("#form-query") in jQuery
  var formQuery = document.getElementById("form-query");
  formQuery.addEventListener("submit", formQueryOnSubmit);
  formQuery.reset();

  document
    .getElementById("player-condition")
    .addEventListener("change", function(e) {
      document.getElementById("player").style.display =
        e.target.value === "none" ? "none" : "inline";
    });
});
