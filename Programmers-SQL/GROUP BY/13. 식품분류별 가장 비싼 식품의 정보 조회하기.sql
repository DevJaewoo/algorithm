SELECT F.CATEGORY, T.MAX_PRICE, F.PRODUCT_NAME
FROM FOOD_PRODUCT AS F
JOIN (
    SELECT CATEGORY, MAX(PRICE) AS MAX_PRICE
    FROM FOOD_PRODUCT
    GROUP BY CATEGORY
    HAVING CATEGORY IN ('과자', '국', '김치', '식용유')
) AS T
ON F.CATEGORY = T.CATEGORY AND F.PRICE = T.MAX_PRICE
ORDER BY PRICE DESC