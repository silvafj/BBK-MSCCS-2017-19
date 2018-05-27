Very good attempt.

Some repetition of code structure where appropriate *helper* functions may have helped.

```
 earth_radius = 3956  # miles
    lat1 = math.radians(lat1degrees)
    long1 = math.radians(long1degrees)
    lat2 = math.radians(lat2degrees)
    long2 = math.radians(long2degrees)
    lat_difference = lat2 - lat1
    long_difference = long2 - long1
    sin_half_lat = math.sin(lat_difference / 2)
    sin_half_long = math.sin(long_difference / 2)
    a = sin_half_lat**2 + math.cos(lat1) * math.cos(lat2) * sin_half_long**2
    c = 2 * math.atan2(math.sqrt(a), math.sqrt(1.0 - a))
```
For related entities consider using tuples rather than several variables.
