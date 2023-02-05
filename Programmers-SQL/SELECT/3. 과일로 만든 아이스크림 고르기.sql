SELECT F.FLAVOR
FROM FIRST_HALF F
JOIN ICECREAM_INFO I
ON F.FLAVOR = I.FLAVOR
WHERE (
    TOTAL_ORDER >= 3000
    AND INGREDIENT_TYPE = 'fruit_based'
)
ORDER BY TOTAL_ORDER DESC