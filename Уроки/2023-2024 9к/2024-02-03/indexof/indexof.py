def index_of(arr: list[int], v: int) -> int:
    for i in range(len(arr)):
        if arr[i] == v:
            return i
    return -1
