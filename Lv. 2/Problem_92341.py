# 프로그래머스 92341번. 주차 요금 계산
# https://school.programmers.co.kr/learn/courses/30/lessons/92341

import math

def get_fee(time, fees):
    if time < fees[0]:  # 기본 시간보다 작은 경우
        return fees[1]  # 기본 요금을 받는다
    else:  # 기본 시간을 넘은 경우
        return fees[1] + math.ceil((time - fees[0]) / fees[2]) * fees[3]  # 기본 요금과 초과 시간에 대한 비용을 받는다


def solution(fees, records):
    parking = dict()
    stack = dict()

    for record in records:
        time, car, cmd = record.split()
        hour, minute = time.split(":")
        minutes = int(hour) * 60 + int(minute)  # 시간 -> 분 환산

        if cmd == 'IN':
            parking[car] = minutes
        elif cmd == 'OUT':
            try:
                stack[car] += minutes - parking[car]
            except:
                stack[car] = minutes - parking[car]
            del parking[car]  # 출차 차량 삭제

    # 출차 기록 없는 차 23:59 출차 처리
    for car, minute in parking.items():
        try:
            stack[car] += 23 * 60 + 59 - minute
        except:
            stack[car] = 23 * 60 + 59 - minute

    return [get_fee(time, fees) for car, time in sorted(list(stack.items()), key=lambda x: x[0])]
