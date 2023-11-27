# 프로그래머스 60063번. 블록 이동하기
# https://school.programmers.co.kr/learn/courses/30/lessons/60063

from collections import deque

def solution(board):
    # 로봇의 가능한 이동 경로를 계산하는 함수
    def move(loc1, loc2, board):
        # 상하좌우 이동을 위한 리스트
        moves = [(-1, 0), (1, 0), (0, 1), (0, -1)]
        result = []

        # loc1과 loc2를 리스트로 변환
        loc1 = list(loc1)
        loc2 = list(loc2)

        # 상하좌우로 이동하는 경우를 계산
        for move in moves:
            new_loc1 = [loc1[0] + move[0], loc1[1] + move[1]]
            new_loc2 = [loc2[0] + move[0], loc2[1] + move[1]]
            if board[new_loc1[0]][new_loc1[1]] == 0 and board[new_loc2[0]][new_loc2[1]] == 0:
                result.append((tuple(new_loc1), tuple(new_loc2)))

        # 로봇이 가로로 놓여있는 경우, 회전하는 경우를 계산
        if loc1[0] == loc2[0]:
            for i in [-1, 1]:
                if board[loc1[0] + i][loc1[1]] == 0 and board[loc2[0] + i][loc2[1]] == 0:
                    result.append((tuple(loc1), (loc1[0] + i, loc1[1])))
                    result.append((tuple(loc2), (loc2[0] + i, loc2[1])))

        # 로봇이 세로로 놓여있는 경우, 회전하는 경우를 계산
        elif loc1[1] == loc2[1]:
            for i in [-1, 1]:
                if board[loc1[0]][loc1[1] + i] == 0 and board[loc2[0]][loc2[1] + i] == 0:
                    result.append(((loc1[0], loc1[1] + i), tuple(loc1)))
                    result.append(((loc2[0], loc2[1] + i), tuple(loc2)))

        return result

    # BFS를 수행하는 함수
    def bfs(start, board):
        queue = deque([(start, 0)])  # 초기 위치와 이동 횟수를 함께 저장
        visited = set([start])

        while queue:
            loc, count = queue.popleft()  # 위치와 이동 횟수를 함께 받아옴

            if (N, N) in loc:  # 도착지에 도달하면
                return count  # 이동 횟수 반환

            for next_loc in move(*loc, board):
                if next_loc not in visited:
                    queue.append((next_loc, count + 1))  # 다음 위치와 현재 이동 횟수 + 1을 함께 저장
                    visited.add(next_loc)

    # board의 크기를 저장
    N = len(board)

    # 벽이나 지도 밖으로 이동하지 않도록 padding 처리
    new_board = [[1] * (N + 2) for _ in range(N + 2)]
    for i in range(N):
        for j in range(N):
            new_board[i + 1][j + 1] = board[i][j]

    # BFS를 수행하고 결과를 반환
    return bfs(((1, 1), (1, 2)), new_board)
