-- 프로그래머스 298517번. 가장 큰 물고기 10마리 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/298517

SELECT ID, LENGTH
FROM FISH_INFO
ORDER BY IFNULL(LENGTH, 10) DESC, ID LIMIT 10
