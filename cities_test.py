import pytest
from cities import *


@pytest.mark.parametrize("road_map,distance", [
    # No roadmap
    ([], 0),
    # Single city
    ([("Alabama", "Montgomery", 32.361538, -86.279118)], 0),
    # Two cities
    ([("Alabama", "Montgomery", 32.361538, -86.279118),
      ("Alaska", "Juneau", 58.301935, -134.41974)], 5699.255086025024),
    # Two cities (reverse direction) should be the same distance
    ([("Alaska", "Juneau", 58.301935, -134.41974),
      ("Alabama", "Montgomery", 32.361538, -86.279118)], 5699.255086025024),
])
def test_compute_total_distance(road_map, distance):
    assert compute_total_distance(road_map) == distance


def test_swap_adjacent_cities():
    pass


def test_swap_cities():
    pass


def test_find_best_cycle():
    pass
