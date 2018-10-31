USE javaweb;

CREATE TABLE
    sys_log
    (
        id INT(10) NOT NULL,
        module VARCHAR(40) NOT NULL,
        action VARCHAR(40) NOT NULL,
        user_name VARCHAR(40) NOT NULL,
        create_date DATETIME NOT NULL
    )
    ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='system log information'