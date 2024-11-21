# you need to start with:
    # install java, must be the 1st line
FROM eclipse-temurin:23-jdk
LABEL maintainer="Samuel"

## how to build the application

#create a directory /app and change directory into /app
    #dir creation while outside of /app and access
WORKDIR /app

#while inside of /app directory
    #copy files over
    #dont need git/docker/mvnw.cmd
    # grab .mvn/mvnw(not mvnw.cmd)/pom.xml/src
    # directory use .mvn .mvn etc.
    # files use file_name .
#copy files over to src destination
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

#building the jar
# RUN /app/mvnw ...... (full name below)
# RUN ./mvnw package -Dmaven.test.skip=true

#making the jar executable for windows + railway
RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true

# if build is successful, then the jar is in target folder
# ./target/exercise-0.01.1-SNAPSHOT.jar


## how to run the application
#for documentation, so ppl know what port to use
# sets server port
    # ENV SERVER_PORT=8080
#PORT for railway version (doesnt really matter)
ENV PORT=8080

#what port does the application need
# EXPOSE ${SERVER_PORT}
#EXPOSE for railway version (doesnt really matter)
EXPOSE ${PORT}

#running the application
# ensure mvn package is ran once to get jar file name
    # ENTRYPOINT java -jar target/exercise-0.0.1-SNAPSHOT.jar

#running for railway version
ENTRYPOINT SERVER_PORT=${PORT} java -jar target/exercise-0.0.1-SNAPSHOT.jar

########docker image build -t sphuar/ssf-day14:v1.0.0 . (context '.' being where the imporatnt files are relative to Dockerfile path)
        #if Dockerfile is in root directory with multiple application folders (deprecated)
            #docker image build -t sphuar/ssf-day14:v1.0.0 . (context '.' being where the imporatnt files are relative to Dockerfile path)
                                    #name/image_name/version/context
    #run CLI code for railway
        #docker image build -t sphuar/ssf-day14:v1.0.0-railway .

#docker image ls, checks pulled (chuks) images as well
    # check status of created image

# actually run the image this time
    ## docker run -d -p 12345:8080 sphuar/ssf-day14:v1.0.0
    # -detach -port-binding host_port:container_port image_name

# check run status
    #docker ps -a

#exit docker
    # docker container stop "1st 4 digits of container id"

# delete docker
    # docker container rm "1st 4 digits of container id"

# restart docker
    # docker start "1st 4 digits of container id"
    
# check logs
    # docker logs "1st 4 digits of container id"


## pushing to docker
    #docker login
    #docker push sphuar/ssf-day14:v1.0.0
        #same as saying docker push docker.io ... 

##pulling from docker, if we want to run it -> use above code
    #docker image pull chukmunnlee/ssf-day13:v1.0.0


# changing pulled PORT env variable from cli using -e (sometimes pulled containers need a specific port, we force them to use our 8080)
    # setting container port to 3000
    # docker container run -d -e SERVER_PORT=3000 -p 8080:3000 sphuar/ssf-day14:v1.0.0


