# 프로그래머스 150369번. 택배 배달과 수거하기
# https://school.programmers.co.kr/learn/courses/30/lessons/150369

import heapq

def solution(cap, n, deliveries, pickups):
    left = []
    for i in range(0, n):
        heapq.heappush(left, -i)  # i번째 도시의 정보 추가. 가장 멀리 있는 도시를 먼저 처리해야하므로 음수로 입력

    dist = 0
    while left:
        house = -heapq.heappop(left)

        if deliveries[house] <= 0 and pickups[house] <= 0:  # 해당 집을 배달 or 픽업할 필요가 없다면
            if house > 0:  # 다른 집을 배달해야 하는 경우
                if deliveries[house] < 0:
                    deliveries[house - 1] -= abs(deliveries[house])  # 이전 배달할 때 이전 집 방문하며 배달

                if pickups[house] < 0:
                    pickups[house - 1] -= abs(pickups[house])  # 이전 픽업할 때 이전 집도 픽업

            continue  # 이전 집 방문

        # 배달 or 픽업을 하기 위해 집에 방문해야 한다면
        dist += (house + 1) * 2  # 해당 집 방문해서
        deliveries[house] -= cap  # 배달하고
        pickups[house] -= cap  # 픽업하고

        # 재방문 의사를 표현한다
        heapq.heappush(left, -house)

    return dist
