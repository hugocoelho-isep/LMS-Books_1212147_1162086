
> cd /path/to/lmsbooks

Dockerfile
> mvn package
> docker build -t lmsbooks .

DockerfileWithPackaging (inclui mvn package)
> docker build -f DockerfileWithPackaging -t lmsbooks .

Running:
> docker compose -f docker-compose-rabbitmq+postgres.yml up -d
>  p
    psql (16.3 (Debian 16.3-1.pgdg120+1))
    Type "help" for help.

    postgres=# create database books_1;
    CREATE DATABASE
    postgres=# create database books_1;
    CREATE DATABASE
    postgres=# \l
                                                      List of databases
       Name    |  Owner   | Encoding | Locale Provider |  Collate   |   Ctype    | ICU Locale | ICU Rules |   Access privileges
    -----------+----------+----------+-----------------+------------+------------+------------+-----------+-----------------------
     books_1   | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           |
     books_2   | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           |
     books_3   | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           |
     postgres  | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           |
     template0 | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | =c/postgres          +
               |          |          |                 |            |            |            |           | postgres=CTc/postgres
     template1 | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           | =c/postgres          +
               |          |          |                 |            |            |            |           | postgres=CTc/postgres
     users_1   | postgres | UTF8     | libc            | en_US.utf8 | en_US.utf8 |            |           |
    (7 rows)
    postgres=# exit
> docker compose up



Using docker stack

Create a network:
> docker network create --driver=overlay --attachable lms_overlay_attachable_network

Init:
> docker swarm init

Deploy:
> docker stack deploy -c docker-stack.yml lmsbooks

Remove:
> docker stack rm lmsbooks

Script to create databases and lmsbooks service:
> ./run.sh

Script to remove databases and lmsbooks service:
> ./shutdown.sh