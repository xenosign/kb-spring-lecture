FROM tomcat:9.0.91-jdk8-openjdk

# 워크디렉토리 설정
WORKDIR /usr/local/tomcat/webapps/

# WAR 파일을 컨테이너의 webapps 디렉토리로 복사
COPY target/*.war .

# 컨텍스트 경로 변경 (선택사항)
RUN mv *.war ROOT.war

# 8080 포트 노출
EXPOSE 8080

# Tomcat 실행
CMD ["catalina.sh", "run"]