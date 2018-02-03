#!/bin/bash

cd /opt/app/kafka
curl http://mirror.vorboss.net/apache/kafka/1.0.0/kafka_2.11-1.0.0.tgz -o kafka_2.11-1.0.0.tgz
tar xzf kafka_2.11-1.0.0.tgz
