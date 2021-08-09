DROP DATABASE springblog_db;
CREATE DATABASE IF NOT EXISTS springblog_db;


-- Having this in our file is a no no on actual deployed non local apps
# CREATE USER springblog_user@localhost IDENTIFIED BY 'p@$$w0rd';
# GRANT ALL ON springblog_db.* TO springblog_user@localhost;