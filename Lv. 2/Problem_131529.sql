-- 프로그래머스 131529번. 카테고리 별 상품 개수 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/131529

SELECT LEFT (PRODUCT_CODE, 2) AS CATEGORY, COUNT (*) AS PRODUCTS
FROM PRODUCT
GROUP BY CATEGORY
ORDER BY CATEGORY;
