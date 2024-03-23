from find_min import find_min


def test_0():
    min_value = find_min([10 ** 20])
    assert min_value == 10 ** 20


def test_1():
    min_value = find_min([1, 2, 3, 4, 5, 6])
    assert min_value == 1


def test_2():
    min_value = find_min([3, 2, 1, 4, 5, 6])
    assert min_value == 1


def test_3():
    min_value = find_min([])
    assert min_value == None


def test_4():
    min_value = find_min([2])
    assert min_value == 2


def test_1():
    min_value = find_min([1, 2, 3, 4, 5, 6])
    assert min_value == 1


def test_5():
    min_value = find_min([3, 2, 1])
    assert min_value == 1


test_0()
test_1()
test_2()
test_3()
test_4()
test_5()
