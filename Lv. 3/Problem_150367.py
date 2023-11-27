# 프로그래머스 150367번. 표현 가능한 이진트리
# https://school.programmers.co.kr/learn/courses/30/lessons/150367

import math

def toBinary(number):
    binary_num = format(number, 'b')

    # 2^n - 1의 자릿수로 변환
    digit = 2 ** (int(math.log(len(binary_num), 2)) + 1) - 1
    binary_num = "0" * (digit - len(binary_num)) + binary_num

    return binary_num


def isPossible(binary_num, parent_exist):
    child_pos = len(binary_num) // 2  # parent의 child node 위치

    if binary_num:  # 이진트리가 비어있지 않은 경우
        child_exist = (binary_num[child_pos] == '1')  # 자식 노드의 존재를 확인한다

    else:  # 이진트리가 비어있는 경우
        return True

    if child_exist and not parent_exist:  # 자식 노드가 존재하는데 부모 노드가 존재하는 경우
        return False  # 이진트리를 구성할 수 없다.

    else:  # 트리를 구성할 수 있는 경우
        # 왼쪽 하위 트리와 오른쪽 하위 트리에 대해 재귀적으로 검사한다.
        return isPossible(binary_num[:child_pos], child_exist) and isPossible(binary_num[child_pos + 1:], child_exist)


def result(number):
    if number == 1: return 1  # 1은 항상 가능

    binary_num = toBinary(number)

    if binary_num[len(binary_num) // 2] == '1' and isPossible(binary_num, True):  # root 노드가 있고 자식 노드를 만들 수 있다면
        return 1
    else:
        return 0


def solution(numbers):
    answer = []

    for number in numbers:
        answer.append(result(number))

    return answer
