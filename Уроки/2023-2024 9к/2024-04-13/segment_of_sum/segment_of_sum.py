def find_segment_of_sum(arr: list[int], s: int) -> (int, int):
    """
    arr - массив положительных чисел
    индексы (i,j) такие, что arr[i] + ... + arr[j-1] = s
    """
    i = 0
    j = 0
    sum_ij = 0
    while j <= len(arr):
        if sum_ij == s:
            return i, j
        if sum_ij < s:
            if j == len(arr):
                break
            sum_ij += arr[j]
            j += 1
        else:
            sum_ij -= arr[i]
            i += 1
    return -1, -1
