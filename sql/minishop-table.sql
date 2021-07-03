-- 테이블 순서는 관계를 고려하여 한 번에 실행해도 에러가 발생하지 않게 정렬되었습니다.

-- category Table Create SQL
CREATE TABLE category
(
    `id`    INT            NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `name`  VARCHAR(45)    NOT NULL    COMMENT '이름', 
     PRIMARY KEY (id)
);

ALTER TABLE category COMMENT '카테고리';

CREATE UNIQUE INDEX UQ_category_1
    ON category(name);


-- member Table Create SQL
CREATE TABLE member
(
    `id`            INT             NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `name`          VARCHAR(45)     NOT NULL    COMMENT '이름', 
    `email`         VARCHAR(100)    NOT NULL    COMMENT '이메일', 
    `password`      VARCHAR(70)     NOT NULL    COMMENT '비밀번호', 
    `phone_number`  VARCHAR(45)     NOT NULL    COMMENT '전화번호', 
    `zip`           VARCHAR(10)     NOT NULL    COMMENT '우편번호', 
    `address1`      VARCHAR(50)     NOT NULL    COMMENT '주소 1', 
    `address2`      VARCHAR(50)     NULL        COMMENT '주소 2', 
    `authority`		VARCHAR(50)     NOT NULL    COMMENT '권한',
    `created_at`    DATETIME        NULL        COMMENT '생성일자', 
    `updated_at`    DATETIME        NULL        COMMENT '수정일자', 
     PRIMARY KEY (id)
);

ALTER TABLE member COMMENT '회원';

CREATE UNIQUE INDEX UQ_member_1
    ON member(email);

CREATE UNIQUE INDEX UQ_member_2
    ON member(phone_number);


-- product Table Create SQL
CREATE TABLE product
(
    `id`           INT             NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `name`         VARCHAR(45)     NOT NULL    COMMENT '이름', 
    `category_id`  INT             NOT NULL    COMMENT '카테고리 고유번호', 
    `description`  VARCHAR(300)    NULL        COMMENT '설명', 
    `price`        INT             NOT NULL    COMMENT '가격', 
    `image`        VARCHAR(45)     NULL        COMMENT '이미지 주소', 
     PRIMARY KEY (id)
);

CREATE UNIQUE INDEX UQ_product_1
    ON product(name);

ALTER TABLE product
    ADD CONSTRAINT FK_product_category_id_category_id FOREIGN KEY (category_id)
        REFERENCES category (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- order_history Table Create SQL
CREATE TABLE order_history
(
    `id`                     INT             NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `member_id`              INT             NOT NULL    COMMENT '사용자 고유번호', 
    `payment_method`         VARCHAR(45)     NOT NULL    COMMENT '결제수단', 
    `total_price`            INT             NOT NULL    COMMENT '총액', 
    `billing_name`           VARCHAR(50)    NOT NULL    COMMENT '청구지 이름', 
    `billing_phone_number`   VARCHAR(50)    NOT NULL    COMMENT '청구지 전화번호', 
    `billing_zip`            VARCHAR(50)    NOT NULL    COMMENT '청구지 우편번호', 
    `billing_address1`       VARCHAR(50)    NOT NULL    COMMENT '청구지 주소 1', 
    `billing_address2`       VARCHAR(50)    NULL        COMMENT '청구지 주소 2', 
    `shipping_name`          VARCHAR(50)    NOT NULL    COMMENT '수령지 이름', 
    `shipping_phone_number`  VARCHAR(50)    NOT NULL    COMMENT '수령지 전화번호', 
    `shipping_zip`           VARCHAR(50)    NOT NULL    COMMENT '수령지 우편번호', 
    `shipping_address1`      VARCHAR(50)    NOT NULL    COMMENT '수령지 주소 1', 
    `shipping_address2`      VARCHAR(50)    NULL        COMMENT '수령지 주소 2', 
     PRIMARY KEY (id)
);

ALTER TABLE order_history COMMENT '주문';

ALTER TABLE order_history
    ADD CONSTRAINT FK_order_history_member_id_member_id FOREIGN KEY (member_id)
        REFERENCES member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- cart Table Create SQL
CREATE TABLE cart
(
    `id`                INT            NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `member_id`         INT            NOT NULL    COMMENT '사용자 고유번호', 
    `product_id`        INT            NOT NULL    COMMENT '상품 고유번호', 
    `product_quantity`  INT            NOT NULL    COMMENT '상품 수량', 
    `product_color`     VARCHAR(45)    NOT NULL    COMMENT '상품 색상', 
    `product_size`      VARCHAR(45)    NOT NULL    COMMENT '상품 사이즈', 
     PRIMARY KEY (id)
);

ALTER TABLE cart COMMENT '장바구니';

ALTER TABLE cart
    ADD CONSTRAINT FK_cart_product_id_product_id FOREIGN KEY (product_id)
        REFERENCES product (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE cart
    ADD CONSTRAINT FK_cart_member_id_member_id FOREIGN KEY (member_id)
        REFERENCES member (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


-- order_detail Table Create SQL
CREATE TABLE order_detail
(
    `id`                INT            NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `order_id`          INT            NOT NULL    COMMENT '주문 고유번호', 
    `product_id`        INT            NOT NULL    COMMENT '상품 고유번호', 
    `product_quantity`  INT            NOT NULL    COMMENT '상품 수량', 
    `product_size`      VARCHAR(45)    NOT NULL    COMMENT '상품 색상', 
    `product_color`     VARCHAR(45)    NOT NULL    COMMENT '상품 사이즈', 
    `total_price`       INT            NOT NULL    COMMENT '총액', 
     PRIMARY KEY (id)
);

ALTER TABLE order_detail COMMENT '주문 상세';

ALTER TABLE order_detail
    ADD CONSTRAINT FK_order_detail_product_id_product_id FOREIGN KEY (product_id)
        REFERENCES product (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE order_detail
    ADD CONSTRAINT FK_order_detail_order_id_order_history_id FOREIGN KEY (order_id)
        REFERENCES order_history (id) ON DELETE RESTRICT ON UPDATE RESTRICT;


