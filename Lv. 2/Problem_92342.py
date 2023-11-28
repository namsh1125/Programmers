# 프로그래머스 92342번. 양궁대회
# https://school.programmers.co.kr/learn/courses/30/lessons/92342

def score_difference(apeach, lion):
    score_apeach = 0  # 어피치 점수
    score_lion = 0  # 라이언 점수

    for i in range(11):
        if apeach[i] == lion[i] == 0:  # 어피치와 라이언 둘 다 해당 과녁을 쏘지 못한 경우
            continue
        elif apeach[i] >= lion[i]:  # 어피치가 더 많이 쏜 경우
            score_apeach += 10 - i
        else:  # 라이언이 해당 과녁에 더 많이 쏜 경우
            score_lion += 10 - i

    return score_lion - score_apeach


# 지금 쏘는 과녁 번호, 남은 화살 개수, 어피치 점수, 라이언 점수
def DFS(idx, remain, apeach, lion):
    global answer, max_diff
    if remain < 0:  # 화살이 남아있지 않은 경우
        return

    if idx > 10:  # 10점까지 다 쏜 경우
        diff = score_difference(apeach, lion)  # 어피치와 라이언의 점수 차이를 구한다
        if diff <= 0:  # 어피치 점수가 더 높은 경우
            return

        if diff > max_diff:  # 점수 차이가 더 큰 경우
            max_diff = diff  # 점수 차이를 업데이트 해주고
            answer = [lion[i] for i in range(11)]  # 라이언 점수를 업데이트 해준다.
            answer[10] += remain  # 남은 화살을 0점으로 쏜다

        return

    lion[10 - idx] = apeach[10 - idx] + 1  # 어피치가 쏜 점수보다 더 높게 쏴본다
    DFS(idx + 1, remain - lion[10 - idx], apeach, lion)
    lion[10 - idx] = 0  # 해당 과녁을 포기하고 다른 과녁을 노린다
    DFS(idx + 1, remain, apeach, lion)


def solution(n, info):
    global answer, max_diff
    answer = [-1]
    max_diff = 0

    DFS(0, n, info, [0 for _ in range(11)])

    return answer
