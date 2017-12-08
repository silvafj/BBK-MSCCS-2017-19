from kv_store import TimedKVStore
from time import time

def test_kv_store():
    d = TimedKVStore()
    t0 = time()
    d.put("1", 1)

    t1 = time()
    d.put("1", 1.1)

    assert d.get("1") == 1.1
    assert d.get("1", t1) == 1
    assert d.get("1", t0) is None
