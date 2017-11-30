import copy
import pytest
from cities import *


@pytest.mark.parametrize("road_map,expected_distance", [
    # No roadmap
    ([], 0.0),
    # Single city
    ([("Alabama", "Montgomery", 32.361538, -86.279118)], 0.0),
    # Two cities
    ([("Alabama", "Montgomery", 32.361538, -86.279118),
      ("Alaska", "Juneau", 58.301935, -134.41974)], 5699.255086025024),
    # Two cities (reverse direction) should be the same distance
    ([("Alaska", "Juneau", 58.301935, -134.41974),
      ("Alabama", "Montgomery", 32.361538, -86.279118)], 5699.255086025024),
    # Three cities
    ([("Alabama", "Montgomery", 32.361538, -86.279118),
      ("Alaska", "Juneau", 58.301935, -134.41974),
      ("Arizona", "Phoenix", 33.448457, -112.073844)], 6346.559636342563),
])
def test_compute_total_distance(road_map, expected_distance):
    original_road_map = copy.copy(road_map)
    assert compute_total_distance(road_map) == expected_distance
    assert road_map == original_road_map


@pytest.mark.parametrize("road_map,index,expected_road_map,expected_distance", [
    # No roadmap
    ([], 0, [], 0.0),
    # Single city
    ([("Alabama", "Montgomery", 32.361538, -86.279118)], 0,
     [("Alabama", "Montgomery", 32.361538, -86.279118)], 0.0),
    # Two cities
    ([("Alabama", "Montgomery", 32.361538, -86.279118),
      ("Alaska", "Juneau", 58.301935, -134.41974)], 0,
      [("Alaska", "Juneau", 58.301935, -134.41974),
       ("Alabama", "Montgomery", 32.361538, -86.279118)],
        5699.255086025024),
    # Three cities
    ([("Alabama", "Montgomery", 32.361538, -86.279118),
      ("Alaska", "Juneau", 58.301935, -134.41974),
      ("Arizona", "Phoenix", 33.448457, -112.073844)], 2,
      [("Arizona", "Phoenix", 33.448457, -112.073844),
        ("Alaska", "Juneau", 58.301935, -134.41974),
        ("Alabama", "Montgomery", 32.361538, -86.279118)], 6346.559636342563),
])
def test_swap_adjacent_cities(road_map, index, expected_road_map, expected_distance):
    original_road_map = copy.copy(road_map)
    new_road_map, total_distance = swap_adjacent_cities(road_map, index)
    assert new_road_map == expected_road_map
    assert total_distance == expected_distance
    assert road_map == original_road_map


def test_swap_cities():
    pass


def test_find_best_cycle():
    pass
