def bin_search(arr: list[int], v: int) -> int:
    """
    :param: arr - строго возрастающий массив чисел
    :param: v - искомое число
    :return: если v есть в массиве, то индекс элемента;
    иначе место вставки, то есть такой индекс в массие,
    в который следует вставить элемент v так, чтобы массив
    остался возрастающим.
    """
    if not arr:
        return 0
    if v < arr[0]:
        return 0
    l = 0
    r = len(arr)
    # Инвариант: arr[l] <= v < arr[r]
    while l + 1 < r:
        m = (l + r) // 2
        if v < arr[m]:
            r = m
        else:  # arr[m] <= v
            l = m
    # arr[l] <= v < arr[l+1], r = l + 1
    if arr[l] == v:
        return l
    # arr[l] < v < arr[l+1], r = l + 1
    return l + 1
