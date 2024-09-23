-- 프로그래머스 273711번. 업그레이드 된 아이템 구하기
-- https://school.programmers.co.kr/learn/courses/30/lessons/273711

SELECT ITEM_ID, ITEM_NAME, RARITY
FROM ITEM_INFO
WHERE ITEM_ID IN (
    SELECT ITEM_ID
    FROM ITEM_TREE
    WHERE PARENT_ITEM_ID IN (
        SELECT ITEM_ID
        FROM ITEM_INFO
        WHERE RARITY = 'RARE'
    )
)
ORDER BY ITEM_ID DESC;
