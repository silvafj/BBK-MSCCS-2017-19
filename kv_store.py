from collections import defaultdict
from time import time

class TimedKVStore():

    def __init__(self):
        self.data = defaultdict(list)

    def put(self, key, value):
        self.data[key].append((time(), value))

    def get(self, key, time=None):
        if key not in self.data or len(self.data[key]) == 0:
            return None

        if not time:
            # The last added item in the list is the most recent
            _, value = self.data[key][-1]
            return value

        last_value = value = None
        for item in self.data[key]:
            last_value = value
            timestamp, value = item
            if timestamp >= time:
                break

        return last_value
