# H1 Docker commands
sudo docker build -t anderew-kafka .
sudo docker images
docker run -p 4000:80 anderew-kafka 
docker run -p -d 4000:80 anderew-kafka # background
sudo docker container ls
sudo docker container stop 14911477d8a7

sudo docker run -p 9092:9092 -p 2181:2181 anderew-kafka 


sudo docker port e1bfbe9e1dcc # lists ports mapped

sudo docker network inspect bridge # look at bridge network 

sudo docker attach e1bfbe9e1dcc # connect to the container

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test

sudo docker exec -it 73f1859e5fef /bin/bash


# H2 Getting started using third party image
sudo docker pull wurstmeister/kafka # Gets the image and installs locally
