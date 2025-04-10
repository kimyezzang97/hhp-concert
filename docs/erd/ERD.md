```mermaid
---
config:
  theme: forest
---
erDiagram
    QUEUE {
        INT queue_id PK "Primary Key, 대기열 ID"
        INT member_id FK "FK, 사용자 ID"
        VARCHAR queue_status "대기열 상태[ENUM] (WAIT/PLAY/EXPIRE/CANCEL)"
        DATETIME created_at "대기열 등록 시간"
        DATETIME expired_at "대기열 만료 시간"
    }
    MEMBER{
        INT member_id PK "Primary Key, 사용자 ID"
        VARCHAR member_name "사용자 이름"
        INT point "포인트 잔액"
        DATETIME created_at "사용자 생성 시각"
        DATETIME updated_at "사용자 수정 시각"
    }
    PAYMENT {
        INT payment_id PK "PK, 결제 ID"
        INT reservation_id FK "FK, 예약 ID"
        INT member_id FK "FK, 사용자 ID"
        INT payment_price "결제 금액"
        DATETIME created_at "결제 생성 시각"
    }
    RESERVATION {
        INT reservation_id PK "PK, 예약 ID"
        INT member_id FK "FK, 사용자 ID"
        INT seat_id FK "FK, 좌석 ID"
        VARCHAR reservation_status "예약 상태[ENUM] (EMPTY/TEMP/RESERVED)"
        DATETIME created_at "예약 생성 시각"
        DATETIME expired_at "예약 만료 시각"
    }
    SEAT {
        INT seat_id PK "PK, 좌석 ID"
        INT schedule_id FK "FK, 스케줄 ID"
        INT seat_number "좌석 번호"
        INT seat_price "좌석 가격"
        VARCHAR seat_status "좌석 상태[ENUM] (POSSIBLE/IMPOSSIBLE)"
        DATETIME created_at "좌석 생성 시각"
        DATETIME updated_at "좌석 수정 시각"
    }
    SCHEDULE {
        INT schedule_id PK "PK, 스케줄 ID"
        INT concert_id FK "FK, 콘서트 ID"
        DATETIME schedule_date "콘서트 스케줄 시각"
        DATETIME created_at "스케줄 생성 시각"
        DATETIME updated_at "스케줄 수정 시각"
    }
    CONCERT {
        INT concert_id PK "PK, 콘서트 ID"
        VARCHAR concert_name "콘서트 이름"
        DATETIME created_at "콘서트 생성 시각"
        DATETIME updated_at "콘서트 수정 시각"
    }
    CONCERT ||--o{ SCHEDULE : "1:N"
    SCHEDULE ||--o{ SEAT : "1:N"
    SEAT ||--o{ RESERVATION : "1:N"
    RESERVATION ||--o{ PAYMENT : "1:N"
    MEMBER ||--o{ PAYMENT : "1:N"
    MEMBER ||--o{ RESERVATION : "1:N"
    MEMBER  ||--o{ QUEUE : "1:N"
    SCHEDULE  ||--o{ QUEUE : "1:N"
```