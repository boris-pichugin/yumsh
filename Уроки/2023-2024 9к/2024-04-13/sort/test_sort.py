from sort import sort_by_selection


def test1():
    a = [2, 1, 3]
    sort_by_selection(a)
    assert a == [1, 2, 3]
