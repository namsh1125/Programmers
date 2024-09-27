-- 프로그래머스 301647번. 부모의 형질을 모두 가지는 대장균 찾기
-- https://school.programmers.co.kr/learn/courses/30/lessons/301647

SELECT A.ID, A.GENOTYPE, B.GENOTYPE AS PARENT_GENOTYPE
FROM ECOLI_DATA AS A
         JOIN ECOLI_DATA AS B
              ON A.PARENT_ID = B.ID
WHERE A.GENOTYPE & B.GENOTYPE = B.GENOTYPE
ORDER BY A.ID;
