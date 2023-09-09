#!/bin/bash

REPOSITORY=/home/ubuntu/app/step2
PROJECT_NAME=mymate-app-service #해당 위치에 properties에 작성한 프로젝트명과 동일하게 작성합니다.

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -f $PROJECT_NAME)

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR_NAME: $JAR_NAME"
echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
nohup java -jar \
  -Dspring.config.location=classpath:/application.yml,/home/ubuntu/app/application-devdb.yml \
  -Dspring.profile.active=dev \
  $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &