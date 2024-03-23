def find_pair_of_sum(arr: list[int], s: int) -> (int, int):
    if not arr or len(arr) < 2:
        return -1, -1

    for i in range(1, len(arr)):
        for j in range(i):
            if arr[i] + arr[j] == s:
                return j, i

    return -1, -1
