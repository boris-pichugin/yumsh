from bin_search import bin_search


def test_1() -> None:
    assert bin_search([], 1) == 0


def test_2() -> None:
    assert bin_search([1], 0) == 0
    assert bin_search([1], 1) == 0
    assert bin_search([1], 2) == 1


def test_3() -> None:
    arr = [1, 2, 3, 4]
    assert bin_search(arr, 0) == 0
    assert bin_search(arr, 5) == 4
    for i in range(len(arr)):
        assert bin_search(arr, arr[i]) == i


def test_4() -> None:
    assert bin_search([1, 2, 7, 8], 5) == 2


test_1()
test_2()
test_3()
test_4()
