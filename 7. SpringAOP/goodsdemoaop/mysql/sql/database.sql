CREATE DATABASE IF NOT EXISTS oomall_demo;

CREATE USER 'demouser'@'localhost' IDENTIFIED BY '123456';
CREATE USER 'demouser'@'%' IDENTIFIED BY '123456';

GRANT ALL ON oomall_demo.* TO 'demouser'@'localhost';
GRANT ALL ON oomall_demo.* TO 'demouser'@'%';
GRANT PROCESS ON *.* TO 'demouser'@'%';

FLUSH PRIVILEGES;