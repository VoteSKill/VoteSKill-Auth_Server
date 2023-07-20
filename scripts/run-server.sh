#!/bin/bash
fuser -k 80/tcp
REPOSITORY=/home/ec2-user/auth-server
cd $REPOSITORY
nohup java -Dserver.port=80 -jar build/libs/*.jar > $REPOSITORY/nohup.out 2>&1 &