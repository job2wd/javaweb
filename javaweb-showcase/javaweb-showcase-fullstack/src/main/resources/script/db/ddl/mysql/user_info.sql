USE javaweb;
#USE javaweb_conf;

CREATE TABLE
    user_info
    (
        id INT(10) NOT NULL,
        name VARCHAR(40) NOT NULL,
        age TINYINT(3) DEFAULT '0' NOT NULL,
        create_date DATETIME NOT NULL
    )
    ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='user information';
    