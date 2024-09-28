def find_pair_of_sum_sorted(arr: list[int], s: int) -> (int, int):
    if not arr or len(arr) <= 1:
        return -1, -1
    i = 0
    j = len(arr) - 1
    while i < j:
        sum_ij = arr[i] + arr[j]
        if sum_ij < s:
            i += 1
        elif s < sum_ij:
            j -= 1
        else:
            return i, j
    return -1, -1
