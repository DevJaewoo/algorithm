SELECT I.REST_ID, I.REST_NAME, I.FOOD_TYPE, I.FAVORITES, I.ADDRESS, ROUND(AVG(R.REVIEW_SCORE), 2) AS SCORE
FROM REST_INFO AS I
JOIN REST_REVIEW AS R
ON I.REST_ID = R.REST_ID
WHERE SUBSTRING_INDEX(I.ADDRESS, ' ', 1) IN ('서울특별시', '서울시')
GROUP BY REST_ID
ORDER BY SCORE DESC, FAVORITES DESC