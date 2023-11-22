# 프로그래머스 60057번. 문자열 압축
# https://school.programmers.co.kr/learn/courses/30/lessons/60057

result = []  # 압축된 문자열의 길이를 저장하는 배열

def solution(s):
    # 압축할 문자열의 개수. 문자열의 절반까지 압축할 수 있음
    for i in range(1, len(s) // 2 + 1):
        sentence = ''  # 압축된 문자열
        count = 1  # 중복 횟수

        for j in range(0, len(s), i):
            if s[j: j + i] == s[j + i: j + i * 2]:  # 현재 문자열과 다음 문자열이 같다면
                count += 1  # 중복 횟수 추가
                target = s[j: j + i]  # 중복 문자열

            elif count > 1:  # 더 이상 중복된 문자열이 발견되지 않고, 중복된 문자열이 있었다면
                sentence += str(count) + target  # 문자열 압축
                count = 1  # 중복 횟수 초기화

            else:  # 중복되는 문자열이 없는 경우
                sentence += s[j: j + i]  # 문자열 그대로

        result.append(len(sentence))  # 압축 결과 저장

    return 1 if len(s) == 1 else min(result)  # 문자열이 1자리인 경우는 1을 반환하고 그 외의 경우는 최소 길이 반환
