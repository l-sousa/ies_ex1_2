José Lucas Sousa
93019

# lab1.1

a)...
b)

###### Comandos executados

-   mvn --version
    -mvn archetype:generate -DgroupId=ua.ies.mec93019.lab1 -DartifactId=lab1_1 -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
-   cd lab1_1
-   mvn package
-   java -cp target/lab1_1-1.0-SNAPSHOT.jar ua.ies.mec93019.lab1.App
-   mvn clean dependency:copy-dependencies package
-   mvn site

groupid: identifica o grupo que cria o projeto maven
artifactid: é um subdiretório criado dentro do grupo

c)

d)

###### Comandos executados

-   mvn --version
    -mvn archetype:generate -DgroupId=ua.ies.mec93019.weather_radar -DartifactId=MyWeatherRadar -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
-   cd MyWeatherRadar
-   mvn package
-   java -cp target/MyWeatherRadar-1.0-SNAPSHOT.jar ua.ies.mec93019.weather_radar.App
-   mvn clean dependency:copy-dependencies package
-   mvn site

e)..
f) Alterei a versão do compiler para 11
g) Adicionei as devidas dependencias
h) Criei os ficheiros necessários
i)
Correr o seguinte comando após devida alteração do WeatherStarter.java para aceitar o codigo como argumento:
mvn exec:java -Dexec.mainClass="ua.ies.mec93019.weather_radar.WeatherStarter" -Dexec.args="1010500"

Maven Goal: representa uma task específica que contribui para o processo de criação e de gestão de um projeto.

# lab1.2

git log

6d471b8 (HEAD -> logging/feature, origin/logging/feature) added_logs
413d8bd extra_files
2fb9579 ex2
ff3ef2e (origin/main, main) gitignore
08512c6 fiexd
