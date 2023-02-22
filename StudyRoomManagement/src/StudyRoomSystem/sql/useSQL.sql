

CREATE SEQUENCE Customer_Seq;

create table Customer( -- 고객 테이블
    customer_UID NUMBER(6)  NOT NULL, -- 고객 번호
    customer_ID VARCHAR(20) NOT NULL, -- 고객 ID 
    customer_PW VARCHAR(20) NOT NULL, -- 고객 password
    customer_NAME VARCHAR(20), -- 고객 이름
    customer_TEL VARCHAR(13), -- 고객 전화번호
    CONSTRAINT Customer_pk PRIMARY KEY (customer_UID) -- 기본키 : 고객 번호
);

create table Seat( -- 스터디룸 마다 자리 좌석
    Seat_UID NUMBER(10) NOT NULL, --좌석 번호
    Seat_TYPE NUMBER(2) DEFAULT 0, -- 0 : 자유석 1: 고정석 [좌석 타입]
    Status NUMBER(2) DEFAULT 0, --Status가 0이면 사용가능 status가 1이면 사용 불가능 
    customer_UID NUMBER(6), --만약 자리에 앉아있다면 해당 자리에 고객 이름을 띄워줘야함
    CONSTRAINT seat_pk PRIMARY KEY (Seat_UID),
    CONSTRAINT Seat_customer_UID_fk FOREIGN KEY (customer_UID) REFERENCES Customer(customer_UID)
);