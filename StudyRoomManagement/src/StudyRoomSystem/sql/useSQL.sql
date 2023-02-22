
CREATE SEQUENCE Customer_Seq;

create table Customer( -- 고객 테이블
    customer_UID NUMBER(6)  DEFAULT -1 NOT NULL , -- 고객 번호
    customer_ID VARCHAR(20) NOT NULL, -- 고객 ID 
    customer_PW VARCHAR(20) NOT NULL, -- 고객 password
    customer_NAME VARCHAR(20), -- 고객 이름
    customer_TEL VARCHAR(13), -- 고객 전화번호
    CONSTRAINT Customer_pk PRIMARY KEY (customer_UID) -- 기본키 : 고객 번호
);

-- -1을 넣고 싶은데 부모에게 없는 테이블이면 할 수가 없다. 그래서 강제로
insert into customer (customer_id, customer_PW, customer_NAME) VALUES ('admin', '1', '관리자');

create table Seat( -- 스터디룸 마다 자리 좌석
    Seat_UID NUMBER(10) NOT NULL, --좌석 번호
    Seat_TYPE NUMBER(2) DEFAULT 0, -- 0 : 자유석 1: 고정석 [좌석 타입]
    Status NUMBER(2) DEFAULT 0, --Status가 0이면 사용가능 status가 1이면 사용 불가능 
    customer_UID NUMBER(6) Default -1, --만약 자리에 앉아있다면 해당 자리에 고객 이름을 띄워줘야함
    CONSTRAINT seat_pk PRIMARY KEY (Seat_UID),
    CONSTRAINT Seat_customer_UID_fk FOREIGN KEY (customer_UID) REFERENCES Customer(customer_UID) 
);

insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (1, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (2, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (3, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (4, 0, 0, -1);

insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (5, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (6, 0, 0, -1);

insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (7, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (8, 0, 0, -1);

insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (9, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (10, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (11, 0, 0, -1);
insert into Seat (Seat_UID, seat_type, status, customer_UID) VALUES (12, 0, 0, -1);

select * from Seat;