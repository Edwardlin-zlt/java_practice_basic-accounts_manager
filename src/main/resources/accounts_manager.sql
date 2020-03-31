USE thought_works;

CREATE TABLE account
(
    id           int PRIMARY KEY AUTO_INCREMENT,
    user_name    varchar(10) NOT NULL,
    phone_number varchar(11) NOT NULL,
    email        varchar(50) NOT NULL,
    password     varchar(16) NOT NULL
);

CREATE TABLE login_record
(
    id            int PRIMARY KEY AUTO_INCREMENT,
    user_id       int NOT NULL,
    login_time    datetime   DEFAULT now(),
    lock_flag     boolean    DEFAULT FALSE,
    failure_count tinyint(1) DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES account (id)
);

