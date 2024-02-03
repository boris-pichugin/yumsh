from min_value import find_min_value


def test_1():
    arr = [i + 1 for i in range(10)]
    min_value = find_min_value(arr)
    assert min_value == 1


def test_2():
    arr = []
    min_value = find_min_value(arr)
    assert min_value == None


def test_3():
    arr = [1]
    min_value = find_min_value(arr)
    assert min_value == 1


def test_4():
    arr = [1, 1, 1, 1]
    min_value = find_min_value(arr)
    assert min_value == 1


def test_5():
    arr = [10 - i for i in range(10)]
    min_value = find_min_value(arr)
    assert min_value == 1


def test_6():
    arr = [10 for i in range(10)]
    arr[3] = 5
    min_value = find_min_value(arr)
    assert min_value == 5


test_1()
test_2()
test_3()
test_4()
test_5()
test_6()
