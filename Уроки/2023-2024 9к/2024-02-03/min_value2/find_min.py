def find_min(arr: list[int]) -> int | None:
    if not arr:
        return None

    min_value = arr[0]
    for i in range(1, len(arr)):
        if arr[i] < min_value:
            min_value = arr[i]
    return min_value
