FROM postgres:16.3-alpine3.19

ADD /ddl/user_service/ddl/00_user.sql /docker-entrypoint-initdb.d/

ADD /ddl/user_service/ddl/01_user.sql /docker-entrypoint-initdb.d/