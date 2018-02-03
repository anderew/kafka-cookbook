#!/bin/bash

su - kafka
id
cd /opt/app/kafka
pwd
#find .
cd kafka_2.11-1.0.0
nohup bin/zookeeper-server-start.sh config/zookeeper.properties 1> zk.out &2>1 &
nohup bin/kafka-server-start.sh config/server.properties  1> ks.out 2>&1 &
tail -f ks.out
