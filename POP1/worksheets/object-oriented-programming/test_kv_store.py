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

def test_remove():
    d = TimedKVStore()
    t0 = time()
    d.put("1", 1)

    t1 = time()
    d.put("1", 1.1)

    t2 = time()
    d.put("1", 1.2)

    assert d.get("1", t2) == 1.1
    d.remove("1", t2)
    assert d.get("1", t2) == None

    assert d.get("1") == 1.2
    d.remove("1")
    assert d.get("1") is None
