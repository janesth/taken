create database taken;

use taken;

create table reservation (
    car INT,
    name VARCHAR(12),
    booked TINYINT(1),
    deviceID VARCHAR(255)
)

INSERT INTO reservation(car) VALUES (2131230813)
INSERT INTO reservation(car) VALUES (2131230819)
INSERT INTO reservation(car) VALUES (2131230816)
INSERT INTO reservation(car) VALUES (2131230810)