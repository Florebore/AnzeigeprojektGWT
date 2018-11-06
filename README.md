# Build
mvn clean package && docker build -t com.flope/Anzeigeprojekt_Server_ver2 .

# RUN

docker rm -f Anzeigeprojekt_Server_ver2 || true && docker run -d -p 8080:8080 -p 4848:4848 --name Anzeigeprojekt_Server_ver2 com.flope/Anzeigeprojekt_Server_ver2 