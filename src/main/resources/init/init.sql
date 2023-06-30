-- GRANT ALL privileges on *.* TO 'root'@'%';
-- 授权 root 用户可以远程链接
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native-pasword BY '123456';