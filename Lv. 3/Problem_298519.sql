-- 프로그래머스 298519번. 특정 조건을 만족하는 물고기별 수와 최대 길이 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/298519

SELECT COUNT(*) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH, FISH_TYPE
FROM FISH_INFO
GROUP BY FISH_TYPE
HAVING AVG(IFNULL(LENGTH, 10)) >= 33
ORDER BY FISH_TYPE ASC;
