def count_intersect(arr1: list[int], arr2: list[int]) -> int:
    """
    Число различных элементов в пересечении
    """
    count = 0
    i = 0
    j = 0
    while i < len(arr1) and j < len(arr2):
        if arr1[i] == arr2[j]:
            if count == 0 or arr1[i] != arr1[i - 1]:
                count += 1
            i += 1
            j += 1
        elif arr1[i] < arr2[j]:
            i += 1
        else:
            j += 1
    return count


def count_union(arr1: list[int], arr2: list[int]) -> int:
    """
    Число различных элементов в объединении
    """
    count = 0
    i = 0
    j = 0
    prev_item = None
    while i < len(arr1) and j < len(arr2):
        if arr1[i] == arr2[j]:
            v = arr1[i]
            i += 1
            j += 1
        elif arr1[i] < arr2[j]:
            v = arr1[i]
            i += 1
        else:
            v = arr2[j]
            j += 1
        if v != prev_item:
            count += 1
            prev_item = v

    while i < len(arr1):
        if arr1[i] != prev_item:
            count += 1
            prev_item = arr1[i]
        i += 1

    while j < len(arr2):
        if arr2[j] != prev_item:
            count += 1
            prev_item = arr2[j]
        j += 1

    return count


def count_unique(arr: list[int]) -> int:
    if len(arr) == 0:
        return 0
    count = 1
    for i in range(1, len(arr)):
        if arr[i] != arr[i - 1]:
            count += 1
    return count


def find_closest(arr1: list[int], arr2: list[int]) -> (int, int):
    """
    Вернуть индексы ближайших элементов.
    """
    if not arr1 or not arr2:
        return -1, -1
    i = 0
    j = 0
    min_diff = abs(arr1[0] - arr2[0])
    min_i = 0
    min_j = 0
    while i < len(arr1) and j < len(arr2):
        if arr1[i] == arr2[j]:
            return i, j

        diff = abs(arr1[i] - arr2[j])
        if diff < min_diff:
            min_diff = diff
            min_i = i
            min_j = j

        if arr1[i] < arr2[j]:
            i += 1
        else:
            j += 1

    return min_i, min_j
