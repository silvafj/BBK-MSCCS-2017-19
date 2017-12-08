from collections import defaultdict
from time import time

class TimedKVStore():

    def __init__(self):
        self.data = defaultdict(list)

    def put(self, key, value):
        self.data[key].append([time(), value])

    def get(self, key, time=None):
        """
        Returns the most recent value before `time`. If no `time` is given,
        returns the last added value.
        """
        
        if key not in self.data or len(self.data[key]) == 0:
            return None

        if not time:
            # The last added item in the list is the most recent
            return self.data[key][-1][1]

        last_value = value = None
        for item in self.data[key]:
            last_value = value
            timestamp, value = item
            if timestamp >= time:
                break

        return last_value

    def remove(self, key, time=None):
        """
        Removes all values where the timestamp is lower than `time`.
        If no `time` is given, it removes all the values of `key`.
        """

        if key not in self.data:
            return

        if not time:
            del self.data[key]
            return

        while len(self.data[key]) > 0 and self.data[key][0][0] < time:
            del self.data[key][0]
