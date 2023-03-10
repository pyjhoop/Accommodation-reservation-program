-- TABLE DROP
DROP TABLE TB_MEMBER;
DROP TABLE TB_ZZIM;
DROP TABLE TB_ROOM;
DROP TABLE TB_RESERVATION;
DROP TABLE TB_WISHLIST;
DROP TABLE TB_REVIEW;
-- SEQUENCE DROP
DROP SEQUENCE SEQ_ROOM;
DROP SEQUENCE SEQ_RESERVE;
DROP SEQUENCE SEQ_MEMBER;
DROP SEQUENCE SEQ_REVIEW;
DROP SEQUENCE SEQ_ZZIM;

CREATE TABLE TB_MEMBER(
    RESERVATION_NO NUMBER PRIMARY KEY,
    USERNAME VARCHAR2(40) NOT NULL,
    USERID VARCHAR2(20) UNIQUE NOT NULL,
    USERPWD VARCHAR2(20) NOT NULL,
    NUMBER_GUESTS NUMBER
);

CREATE SEQUENCE SEQ_MEMBER
NOCACHE;

INSERT INTO TB_MEMBER VALUES(SEQ_MEMBER.NEXTVAL,'신동엽','dy6600','sin555',4);
INSERT INTO TB_MEMBER VALUES(SEQ_MEMBER.NEXTVAL,'강호동','hd3322','kang123',2);
INSERT INTO TB_MEMBER VALUES(SEQ_MEMBER.NEXTVAL,'유재석','js9999','yu1922',1);
INSERT INTO TB_MEMBER VALUES(SEQ_MEMBER.NEXTVAL,'박명수','msooo2','park21',3);
INSERT INTO TB_MEMBER VALUES(SEQ_MEMBER.NEXTVAL,'김국진','kook200','jinjin15',2);

CREATE TABLE TB_ROOM(
    ROOMNO NUMBER PRIMARY KEY,
    ROOMNAME VARCHAR2(60) NOT NULL,
    CAPACITY NUMBER NOT NULL,
    TYPE VARCHAR2(20) CHECK(TYPE IN('모텔', '호텔','펜션')) NOT NULL,
    LOCATION VARCHAR2(60),
    PRICE NUMBER NOT NULL
);

CREATE SEQUENCE SEQ_ROOM
START WITH 100
NOCYCLE
NOCACHE;

INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'명동 뉴서울호텔',3,'모텔','중구 태평로1가',90000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'명동 밀리오레호텔',2,'모텔','중구 충무로1가',90000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'종로 호텔 더 포스트',4,'모텔','종로구 관수동',85000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'종로 호텔라와',2,'모텔','종로구 낙원동',22000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'동대문 대경',3,'모텔','중구 을지로6가',144450);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'용산 엘르인',2,'모텔','용산구 갈월동',35000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'신촌 앨리 - ALLEY',3,'모텔','서대문구 대현동',100000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'신촌 포레스타',3,'모텔','서대문구 창천동',100000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'건대 호텔 K World',5,'모텔','광진구 자양동',25000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'건대 컬리넌-1호점',2,'모텔','광진구 군자동',150000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'엘리에나 호텔',3,'호텔','강남구 학동역 도보 4분',217412);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'조선 팰리스 서울 강남 럭셔리 컬렉션',2,'호텔','역삼역 8번 출구 도보 5분',792000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'신라스테이 삼성',3,'호텔','삼성역 도보 2분',230000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'호텔 인 나인 강남',4,'호텔','봉은사역 3번출구 도보 1분',129000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'신라스테이 역삼',2,'호텔','역삼역 도보 10분',211365);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'노보텔 앰배서더 서울 강남',2,'호텔','신논현역 도보 3분',407000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'양평 독채펜션 빌라엘라',5,'펜션','양평군 중미산천문대 차량 11분', 1200000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'양평 바오하우스',3,'펜션','양평군 매곡역 차량 17분',280000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'양평 개울가펜션',2,'펜션','양평군 중미산휴양림 차량 16분',280000);
INSERT INTO TB_ROOM VALUES(SEQ_ROOM.NEXTVAL,'양평 테이트펜션',2,'펜션','양평군 환순원문학촌 차량 4분',112000);

CREATE TABLE TB_RESERVATION(
    RESERVNO NUMBER PRIMARY KEY,
    STATE VARCHAR2(10) CHECK(STATE IN('취소','예약','공실')),
    ROOMNO NUMBER REFERENCES TB_ROOM,
    STARTDATE DATE,
    ENDDATE DATE,
    RESERVATION_NO NUMBER REFERENCES TB_MEMBER
);

CREATE SEQUENCE SEQ_RESERVE
START WITH 1000
NOCYCLE
NOCACHE;

CREATE TABLE TB_WISHLIST(
    RESERVATION_NO NUMBER REFERENCES TB_MEMBER,
    ROOMNO NUMBER REFERENCES TB_ROOM
);

CREATE TABLE TB_REVIEW(
    REVIEWNO NUMBER PRIMARY KEY,
    ROOMNO NUMBER REFERENCES TB_ROOM,
    RESERVATION_NO NUMBER REFERENCES TB_MEMBER,
    REVIEW VARCHAR2(900) NOT NULL,
    RATED NUMBER CHECK(RATED IN(1,2,3,4,5))
);


CREATE SEQUENCE SEQ_REVIEW
NOCACHE;
INSERT INTO TB_REVIEW VALUES(SEQ_REVIEW.NEXTVAL,119,1,'방이 너무 깔끔해요~',5);

CREATE TABLE TB_ZZIM(
    ZZIMNO NUMBER,
    MEMBERNO NUMBER REFERENCES TB_MEMBER,
    ROOMNO NUMBER REFERENCES TB_ROOM,
    PRIMARY KEY(MEMBERNO, ROOMNO)
);

CREATE SEQUENCE SEQ_ZZIM
NOCACHE
NOCYCLE;

COMMIT;