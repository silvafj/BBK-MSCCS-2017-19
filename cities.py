"""
Author: Fernando Silva <fdealm02>

Implementation of the Travelling Salesman problem: given a list of cities and
the distances between each pair of cities, calculates the shortest possible
route that visits each city exactly once and returns to the origin city.
"""
import copy

from math import *

def read_cities(file_name):
    """
    Parse the data from the given `file_name` and return as a list.

    :param str file_name: File containing the cities data
    :return: List of four-tuples `[(state, city, latitude, longitude), ...]`
    :rtype: list<tuple>
    """
    cities = []
    with open(file_name) as f:
        for line in f:
            state, city, latitude, longitude = line.strip().split("\t")
            cities.append((state, city, float(latitude), float(longitude)))

    return cities


def print_cities(road_map):
    """
    Prints a list of cities, along with their locations.

    :param list road_map: List of four-tuples containing cities data
    """
    for item in road_map:
        _, city, lat, lon = item
        print("{} {:.2f} {:.2f}".format(city, lat, lon))


def distance(lat1degrees, long1degrees, lat2degrees, long2degrees):
    earth_radius = 3956  # miles
    lat1 = radians(lat1degrees)
    long1 = radians(long1degrees)
    lat2 = radians(lat2degrees)
    long2 = radians(long2degrees)
    lat_difference = lat2 - lat1
    long_difference = long2 - long1
    sin_half_lat = sin(lat_difference / 2)
    sin_half_long = sin(long_difference / 2)
    a = sin_half_lat ** 2 + cos(lat1) * cos(lat2) * sin_half_long ** 2
    c = 2 * atan2(sqrt(a), sqrt(1.0 - a))
    return earth_radius * c


def compute_total_distance(road_map):
    """
    Calculates the sum of the distances of all the connections in
    the `road_map`. Note that `road_map` is a cycle and the last connection
    returns to the starting point.

    :param list road_map: List of four-tuples containing cities data
    :return: Sum of the distances of all connections in miles
    :rtype: float
    """
    total_distance = 0.0

    if len(road_map) < 2:
        return total_distance

    for i, item in enumerate(road_map):
        _, _, lat1degrees, long1degrees = item
        _, _, lat2degrees, long2degrees = road_map[(i + 1) % len(road_map)]

        total_distance += distance(lat1degrees, long1degrees,
                                   lat2degrees, long2degrees)

    return total_distance


def swap_adjacent_cities(road_map, index):
    """
    Swaps two adjacent cities based on the given `index`. City at the location
    `index` will be swapped with the city at the location `index + 1` (or at
    `0`, if `index` refers to the last element in the list).

    :param list road_map: List of four-tuples containing cities data
    :param int index: City to be swaped
    :return: Tuple with new `road_map` and `total_distance`
    :rtype: tuple
    """
    new_road_map = copy.copy(road_map)

    if len(road_map) < 2:
        return (new_road_map, 0.0)

    new_road_map[index], new_road_map[(index + 1) % len(new_road_map)] = \
        new_road_map[(index + 1) % len(new_road_map)], new_road_map[index]

    return (new_road_map, compute_total_distance(new_road_map))


def swap_cities(road_map, index1, index2):
    """
    Take the city at location `index` in the `road_map`, and the
    city at location `index2`, swap their positions in the `road_map`,
    compute the new total distance, and return the tuple

        (new_road_map, new_total_distance)

    Allow for the possibility that `index1=index2`,
    and handle this case correctly.
    """
    pass


def find_best_cycle(road_map):
    """
    Using a combination of `swap_cities` and `swap_adjacent_cities`,
    try `10000` swaps, and each time keep the best cycle found so far.
    After `10000` swaps, return the best cycle found so far.
    """
    pass


def print_map(road_map):
    """
    Prints, in an easily understandable format, the cities and
    their connections, along with the cost for each connection
    and the total cost.
    """
    pass


def main():
    """
    Reads in, and prints out, the city data, then creates the "best"
    cycle and prints it out.
    """
    road_map = read_cities('city-data.txt')
    print_cities(road_map)


if __name__ == "__main__":
    main()
