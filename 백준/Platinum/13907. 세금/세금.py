import heapq
import sys

input = sys.stdin.readline


def solution():
    # 입력 받기
    N, M, K = map(int, input().split())
    S, D = map(int, input().split())

    # 그래프 생성 (인접 리스트)
    graph = [[] for _ in range(N + 1)]

    # 간선 정보 입력 받기
    for _ in range(M):
        a, b, w = map(int, input().split())
        graph[a].append((b, w))
        graph[b].append((a, w))

    # 세금 인상액 입력 받기
    tax_increases = [int(input()) for _ in range(K)]

    # distance[도시][지나온 간선 수] = 최소 비용
    distance = [[float('inf')] * N for _ in range(N + 1)]
    distance[S][0] = 0

    # 우선순위 큐: (통행료, 지나온 간선 수, 현재 도시)
    pq = [(0, 0, S)]

    while pq:
        cost, edges, node = heapq.heappop(pq)

        # 최적화: 같은 도시에 더 적은 간선 수로 도달할 수 있으면 스킵
        skip = False
        for i in range(edges + 1):
            if distance[node][i] < cost:
                skip = True
                break

        # 모든 도시를 이미 방문했거나 최적화 조건에 해당하면 스킵
        if edges == N - 1 or skip:
            continue

        for next_node, weight in graph[node]:
            next_edges = edges + 1
            next_cost = cost + weight

            if next_cost < distance[next_node][next_edges]:
                distance[next_node][next_edges] = next_cost
                heapq.heappush(pq, (next_cost, next_edges, next_node))

    # 초기 최소 통행료 및 최적 간선 수 구하기
    min_toll = float('inf')
    limit = 0
    for edges in range(N):
        if distance[D][edges] < min_toll:
            min_toll = distance[D][edges]
            limit = edges

    print(min_toll)

    # 각 세금 인상 후의 최소 통행료
    accumulated_tax = 0
    for tax in tax_increases:
        accumulated_tax += tax

        min_toll = float('inf')
        new_limit = 0
        # 세금 증가 후에는 이전 limit 이하의 간선 수만 검사
        for edges in range(limit + 1):
            if distance[D][edges] != float('inf'):
                current_toll = distance[D][edges] + edges * accumulated_tax
                if current_toll < min_toll:
                    min_toll = current_toll
                    new_limit = edges

        limit = new_limit  # 다음 세금 계산을 위해 업데이트
        print(min_toll)


if __name__ == "__main__":
    solution()