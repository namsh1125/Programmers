-- 프로그래머스 131530번. 가격대 별 상품 개수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/131530

SELECT 
    FLOOR(PRICE / 10000) * 10000 AS PRICE_GROUP,
    COUNT(*) AS PRODUCTS
FROM PRODUCT
GROUP BY PRICE_GROUP
ORDER BY PRICE_GROUP;
