-- 프로그래머스 59040번. 고양이와 개는 몇 마리 있을까
-- https://school.programmers.co.kr/learn/courses/30/lessons/59040

SELECT ANIMAL_TYPE, COUNT(*) AS COUNT
FROM ANIMAL_INS
GROUP BY ANIMAL_TYPE
HAVING ANIMAL_TYPE = 'Cat' OR ANIMAL_TYPE = 'Dog'
ORDER BY ANIMAL_TYPE;
