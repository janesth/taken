# taken

taken is an app to book objects of movement.

### Database connection

defined in app.properties

### Database statements

- create table: `` CREATE TABLE reservation (car varchar(12),name varchar(12),booked tinyint(1),deviceID varchar(255)); ``
- to reset DB: `` UPDATE `reservation` SET `name` = '', `booked` = 0, `deviceID` = ''` ``