Bugs:

-- Order List page: Ben's Picture(3).


- In Order Management Page, add 2 buttons: Delete a Product, Add New Product:

    Update the quantity greater than the maximum number in storage, should have validation:
        -> If max out, user will click Acknowledged, user will double check order details again then click Finish Update one more time
    Nullify the button at the maximum quantity, show : Unavailable(in red color), put hover over: Quantity maxout. --DONE
- Detail ->> Buy -- DONE


-- Mobile format does not work

-- Add Images for Products : --DONE




-------------------------------------------

Backend: ./mvnw spring-boot:run
Frontend:
cd ui 
npm start

To log in:
ssh root@137.184.123.204

to view: cat .env
to edit: vi .env

To view docker in linux: docker ps

to stop docker: docker stop 583831d23d1f

to remove docker : docker remove eshop-backend

To create and run a new container from an image: docker run --name=mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mypassword -d mysql/mysql-server

To access mysql server in docker: 
docker exec -it mysql /bin/bash

To log in: 

mysql -u root -p

show databases;

select host, user from mysql.user;

use mysql;

show tables;

Enable front-end access database:
update user set host='%' where user='root';
flush privileges;

To create docker for eshop's backend:
cd project/eshop
ls -l
chmod u+x mvnw
docker build -t eshop-back .

To deploy backend's docker:
docker run -d --name eshop-backend -p 8081:8081 eshop-back
docker logs -f eshop-backend


0. Make sure having original copy eshop.zip

Make sure change url in application.properties:
spring.datasource.url=jdbc:mysql://172.18.0.1:3306/test

Make sure change url in database.properties:
jdbc.url=jdbc:mysql://172.18.0.1:3306/test

To create docker for eshop's frontend:
Go to folder eshop/ui: 
docker build -t eshop-front .

If permission error from eshop/ui directory:
sudo chmod +x node_modules/.bin/react-scripts

To deploy frontend's docker:
docker run -d --name eshop-frontend -p 3000:3000 eshop-front


1. Zip eshop folder
2. Command to copy zip file to digital ocean: scp -r eshop.zip root@137.184.123.204:/root/project   
3. Command: unzip eshop.zip
