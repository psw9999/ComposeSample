package com.psw9999.composesample

val mockData = listOf(
    Comment(
        commentIndex = 1105L,
        ipoIndex = 207,
        writer = "DB_TRIGGER_IPO_INSERT",
        stockName = "유안타제12호기업인수목적",
        stockKinds = "스팩주",
        commentTitle = "신규 등록되었습니다.",
        commentList = emptyList(),
        registrationDate = "2023-01-20"
    ),
    Comment(
        commentIndex = 1104L,
        ipoIndex = 191,
        writer = "DB_TRIGGER",
        stockName = "삼기이브이",
        stockKinds = "공모주",
        commentTitle = "공모 정보가 업데이트 되었습니다.",
        commentList = listOf(
            CommentList(
                type = "DYNAMIC",
                comment = "의무보유확약 비율이 정정되었습니다.",
                detailContent = "0.45"
            ),
            CommentList(
                type = "DYNAMIC",
                comment = "수요 예측 결과가 발표되었습니다.",
                detailContent = "37.51"
            ),
            CommentList(
                type = "DYNAMIC",
                comment = "공모가가 확정되었습니다.",
                detailContent = "11,000원"
            )
        ),
        registrationDate = "2023-01-20"
    ),
    Comment(
        commentIndex = 1100L,
        ipoIndex = 190,
        writer = "DB_TRIGGER",
        stockName = "미래반도체",
        stockKinds = "공모주",
        commentTitle = "일정이 변경되었습니다.",
        commentList = listOf(
            CommentList(
                type = "DYNAMIC",
                comment = "상장일이 변경되었습니다.",
                detailContent = "0.45"
            )
        ),
        registrationDate = "2023-01-20"
    )
)