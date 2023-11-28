# 프로그래머스 92344번. 파괴되지 않은 건물
# https://school.programmers.co.kr/learn/courses/30/lessons/92344

def solution(board, skill):
    tmp = [[0] * (len(board[0]) + 1) for _ in range(len(board) + 1)]  # 누적합 기록을 위한 배열

    for type, r1, c1, r2, c2, degree in skill:
        tmp[r1][c1] += degree if type == 2 else -degree
        tmp[r1][c2 + 1] += -degree if type == 2 else degree
        tmp[r2 + 1][c1] += -degree if type == 2 else degree
        tmp[r2 + 1][c2 + 1] += degree if type == 2 else -degree

    # 왼쪽 -> 오른쪽
    for i in range(len(tmp) - 1):
        for j in range(len(tmp[0]) - 1):
            tmp[i][j + 1] += tmp[i][j]

    # 위 -> 아래
    for j in range(len(tmp[0]) - 1):
        for i in range(len(tmp) - 1):
            tmp[i + 1][j] += tmp[i][j]

    # 건물 내구도 구하기
    answer = 0
    for i in range(len(board)):
        for j in range(len(board[0])):
            if board[i][j] + tmp[i][j] > 0:  # 건물이 파괴되지 않았다면
                answer += 1

    return answer
