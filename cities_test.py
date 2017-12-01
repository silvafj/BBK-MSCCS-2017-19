import copy
import pytest

from cities import compute_total_distance, find_best_cycle, \
                   swap_adjacent_cities, swap_cities


@pytest.mark.parametrize("road_map,expected_distance", [
    # No roadmap
    ([], 0.0),
    # Single city
    ([("state", "city", 0.0, 0.0)], 0.0),
    # Two cities
    ([("state", "city1", 0.0, 0.0),
      ("state", "city2", 1.0, 1.0)], 195.28443017165606),
    # Two cities (reverse direction) should be the same distance
    ([("state", "city2", 1.0, 1.0),
      ("state", "city1", 0.0, 0.0)], 195.28443017165606),
    # Three cities
    ([("state", "city1", 0.0, 0.0),
      ("state", "city2", 1.0, 1.0),
      ("state", "city3", 2.0, 2.0)], 390.5391132370841),
])
def test_compute_total_distance(road_map, expected_distance):
    original_road_map = copy.copy(road_map)
    assert compute_total_distance(road_map) == expected_distance
    assert road_map == original_road_map


@pytest.mark.parametrize("road_map,index,expected_road_map,expected_distance", [
    # No roadmap
    ([], 0, [], 0.0),
    # Single city
    ([("state", "city", 0.0, 0.0)], 0,
     [("state", "city", 0.0, 0.0)], 0.0),
    # Two cities
    ([("state", "city1", 0.0, 0.0),
      ("state", "city2", 1.0, 1.0)], 0,
     [("state", "city2", 1.0, 1.0),
      ("state", "city1", 0.0, 0.0)], 195.28443017165606),
])
def test_swap_adjacent_cities(road_map, index, expected_road_map, expected_distance):
    original_road_map = copy.copy(road_map)
    new_road_map, total_distance = swap_adjacent_cities(road_map, index)
    assert new_road_map == expected_road_map
    assert total_distance == expected_distance
    assert road_map == original_road_map


@pytest.mark.parametrize("road_map,index1,index2,expected_road_map,expected_distance", [
    # No roadmap
    ([], 0, 1, [], 0.0),
    # Single city
    ([("state", "city1", 0.0, 0.0)], 0, 1,
     [("state", "city1", 0.0, 0.0)], 0.0),
    # Two cities
    ([("state", "city1", 0.0, 0.0),
      ("state", "city2", 1.0, 1.0)], 1, 0,
     [("state", "city2", 1.0, 1.0),
      ("state", "city1", 0.0, 0.0)], 195.28443017165606),
])
def test_swap_cities(road_map, index1, index2, expected_road_map, expected_distance):
    original_road_map = copy.copy(road_map)
    new_road_map, total_distance = swap_cities(road_map, index1, index2)
    assert new_road_map == expected_road_map
    assert total_distance == expected_distance
    assert road_map == original_road_map


@pytest.mark.parametrize("road_map,expected_road_map", [
    # No roadmap
    ([], []),
    # Single city
    ([("state", "city1", 0.0, 0.0)],
     [("state", "city1", 0.0, 0.0)]),
    # Two cities
    ([("state", "city1", 0.0, 0.0),
      ("state", "city2", 1.0, 1.0)],
     [("state", "city1", 0.0, 0.0),
      ("state", "city2", 1.0, 1.0)]),
    # Three cities
    ([("state", "city3", 2.0, 2.0),
      ("state", "city1", 10.0, 10.0),
      ("state", "city2", 1.0, 1.0)],
     [("state", "city1", 10.0, 10.0),
      ("state", "city3", 2.0, 2.0),
      ("state", "city2", 1.0, 1.0)]),
])
def test_find_best_cycle(road_map, expected_road_map):
    assert find_best_cycle(road_map) == expected_road_map
