-- 프로그래머스 59042번. 없어진 기록 찾기
-- https://school.programmers.co.kr/learn/courses/30/lessons/59042

SELECT ANIMAL_ID, NAME
FROM ANIMAL_OUTS
WHERE ANIMAL_ID NOT IN (SELECT ANIMAL_ID FROM ANIMAL_INS)
