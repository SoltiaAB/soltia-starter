1. execute the cmd
    ```shell
   ./gradlew clean assemble
   ```

2. execute the cmd
    ```shell
   java --jar /build/libs/starter-1.0.0-SNAPSHOT-fat.jar
   ```

### Create an image file for the project

1. create an image for jdk 17
    ```shell
   docker run amazoncorretto:17 java -version
   ```

2. create an image for our jar file project
    ```shell
   docker build -t first/soltia-starter .
   ```

3. create the container from the image
    ```shell
   docker run -t -i -p 8888:8888 first/soltia-starter
   ```
