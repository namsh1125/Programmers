# 프로그래머스 42891번. 무지의 먹방 라이브
# https://school.programmers.co.kr/learn/courses/30/lessons/42891

import heapq

def solution(food_times, k):
    answer = -1
    hq = []

    for i in range(len(food_times)):
        heapq.heappush(hq, (food_times[i], i + 1)) # 힙에 (섭취시간, 순번)을 넣어준다.

    remain = len(food_times) # 남은 음식 개수
    before = 0 # 직전 음식 섭취 시간

    while hq:
        time = (hq[0][0] - before) * remain # 가장 빠르게 남은 음식 중 하나를 클리어할 수 있는 시간

        if k >= time: # 한 음식을 먹어치우고도 시간이 남은 경우
            k -= time # 남아있는 모든 음식의 시간을 줄여주고
            remain -= 1 # 남은 음식 개수 줄여주고
            before, _ = heapq.heappop(hq) # 클리어한 음식을 제외한다 + 직전 음식 섭취 시간을 업데이트해 준다.

        else: # 한 음식을 클리어하지 못하는 경우
            hq.sort(key = lambda x: x[1]) # 남은 음식을 순번에 따라 정렬해주고
            answer = hq[k % remain][1] # 순번에 따라 다시 섭취할 음식 번호를 리턴한다
            break

    return answer

