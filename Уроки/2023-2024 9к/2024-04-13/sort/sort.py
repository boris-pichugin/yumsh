def sort_by_selection(arr: list[int]) -> None:
    for i in range(len(arr) - 1):
        min_i = i
        for j in range(i + 1, len(arr)):
            if arr[j] < arr[min_i]:
                min_i = j
        if i < min_i:
            arr[i], arr[min_i] = arr[min_i], arr[i]


def sort_by_insertion(arr: list[int]) -> None:
    for i in range(1, len(arr)):
        v = arr[i]
        for j in range(i, 0, -1):
            if arr[j - 1] > v:
                arr[j] = arr[j - 1]
            else:
                arr[j] = v
                break


def sort_by_bubble(arr: list[int]) -> None:
    is_sorted = False
    while not is_sorted:
        is_sorted = True
        for i in range(len(arr) - 1):
            if arr[i] > arr[i + 1]:
                is_sorted = False
                arr[i], arr[i + 1] = arr[i + 1], arr[i]


def sort_by_shake(arr: list[int]) -> None:
    pass


def sort_by_merge(arr: list[int]) -> None:
    pass
