import sys


def distance_squared(p1, p2):
    """두 점 사이의 제곱 거리를 계산합니다."""
    return (p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2


def brute_force(points):
    """모든 쌍을 비교하여 가장 작은 거리를 찾습니다."""
    n = len(points)
    min_dist = float('inf')
    for i in range(n):
        for j in range(i + 1, n):
            dist = distance_squared(points[i], points[j])
            min_dist = min(min_dist, dist)
    return min_dist


def closest_pair_strip(strip, d):
    """strip 내에서 가장 가까운 두 점의 거리를 찾습니다."""
    min_dist = d
    n = len(strip)

    for i in range(n):
        j = i + 1
        # y좌표 차이가 d보다 작은 최대 7개의 점만 비교
        while j < n and j <= i + 7:
            dist = distance_squared(strip[i], strip[j])
            min_dist = min(min_dist, dist)
            j += 1

    return min_dist


def closest_pair_recursive(px, py):
    """
    분할 정복으로 가장 가까운 두 점의 거리를 구합니다.
    px: x좌표 기준으로 정렬된 점들
    py: y좌표 기준으로 정렬된 점들
    """
    n = len(px)

    # 기본 케이스: 점이 2~3개일 경우 직접 계산
    if n <= 3:
        return brute_force(px)

    # 점을 x좌표 기준으로 반으로 나눔
    mid = n // 2
    mid_x = px[mid - 1][0]

    # Py를 스캔하며 y순서 유지하면서 분할
    py_left = []
    py_right = []
    for p in py:
        if p[0] <= mid_x:
            py_left.append(p)
        else:
            py_right.append(p)

    # 왼쪽과 오른쪽 부분에서 최소 거리를 재귀적으로 구함
    dl = closest_pair_recursive(px[:mid], py_left)
    dr = closest_pair_recursive(px[mid:], py_right)

    d = min(dl, dr)

    # 중간 띠(strip)를 구성 - x좌표가 중간점에서 sqrt(d) 이내인 점들
    strip = []
    for p in py:
        if (p[0] - mid_x) ** 2 <= d:
            strip.append(p)

    # 중간 띠에서 최소 거리를 계산
    return min(d, closest_pair_strip(strip, d))


def counting_sort_points(points):
    """
    좌표의 범위를 활용한 계수 정렬을 사용하여 X와 Y 기준으로 정렬된 리스트 반환
    """
    # 좌표 범위가 -10000부터 10000까지이므로 0~20000 인덱스 사용
    buckets_x = [[] for _ in range(20001)]
    buckets_y = [[] for _ in range(20001)]

    for x, y in points:
        buckets_x[x + 10000].append((x, y))
        buckets_y[y + 10000].append((x, y))

    # x 증가 순으로 정렬된 리스트
    px = []
    for bucket in buckets_x:
        px.extend(bucket)

    # y 증가 순으로 정렬된 리스트
    py = []
    for bucket in buckets_y:
        py.extend(bucket)

    return px, py


def closest_pair(points):
    """주어진 점들 중 가장 가까운 두 점의 제곱 거리를 구합니다."""
    # 계수 정렬을 사용하여 x, y 좌표 기준으로 정렬
    px, py = counting_sort_points(points)
    return closest_pair_recursive(px, py)


def main():
    # 표준 입력 처리
    n = int(sys.stdin.readline())
    points = []

    for _ in range(n):
        x, y = map(int, sys.stdin.readline().split())
        points.append((x, y))

    # 가장 가까운 두 점의 제곱 거리 계산
    result = closest_pair(points)
    print(result)


if __name__ == "__main__":
    main()