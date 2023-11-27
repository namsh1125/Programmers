# 프로그래머스 150368번. 이모티콘 할인행사
# https://school.programmers.co.kr/learn/courses/30/lessons/150368

import heapq
from itertools import product

def solution(users, emoticons):
    answer = []

    rate = [.1, .2, .3, .4]  # 할인 비율
    discounts = product(rate, repeat=len(emoticons))  # 이모티콘 할인 비율 list

    for discount in discounts:
        emoticonPlusUser = 0  # 이모티콘 플러스 구매 사용자
        revenue = 0  # 이모티콘 수익

        for user in users:  # 모든 user를 돌면서
            cost = 0  # user 한 명의 구매 비용

            for j in range(0, len(emoticons)):
                if discount[j] * 100 >= user[0]:  # 이모티콘 할인율이 내 구매 비율 보다 넘는다면
                    cost += emoticons[j] * (1 - discount[j])  # 해당 이모티콘 구매

            if cost >= user[1]:  # 이모티콘 폴러스를 구매하는 게 더 낫다면
                emoticonPlusUser += 1

            else:  # 이모티콘만 구매하는 게 더 낫다면
                revenue += cost

        heapq.heappush(answer, [-emoticonPlusUser, -revenue])

    # 이모티콘 플러스 서비스 가입 수와 이모티콘 매출액
    result = heapq.heappop(answer)

    return [-result[0], -result[1]]
