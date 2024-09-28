from segment_of_sum import find_segment_of_sum


def test1():
    assert find_segment_of_sum([], 0) == (0, 0)


def test2():
    assert find_segment_of_sum([], 1) == (-1, -1)


def test3():
    assert find_segment_of_sum([1], 1) == (0, 1)


def test4():
    assert find_segment_of_sum([1], 2) == (-1, -1)


def test5():
    assert find_segment_of_sum([1], 0) == (0, 0)


def test6():
    assert find_segment_of_sum([1, 2, 1], 3) == (0, 2)


def test7():
    assert find_segment_of_sum([1, 2, 1], 5) == (-1, -1)


test1()
test2()
test3()
test4()
test5()
test6()
test7()
