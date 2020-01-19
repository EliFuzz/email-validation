# Vision

### Description

Microservice email validator build in Kotlin with Continuous Delivery to Kubernetes with [jib](https://github.com/GoogleContainerTools/jib#what-is-jib), [skaffold](https://skaffold.dev/docs/getting-started/#installing-skaffold) and [ktor](https://github.com/ktorio/ktor)  


##### Preconditions
- [jq](https://stedolan.github.io/jq/download/)
- [Kubernetes](https://kubernetes.io/)
- [Minikube](https://kubernetes.io/docs/setup/minikube/)
  - one VM provider [VirtualBox](https://www.virtualbox.org/)
- [Docker](https://www.docker.com/)
- [Skaffold](https://skaffold.dev/docs/getting-started/#installing-skaffold)
- [Java 12](https://jdk.java.net/12/)
  - [community installation options not only for mac os](https://stackoverflow.com/questions/52524112/how-do-i-install-java-on-mac-osx-allowing-version-switching)
- [Kotlin](https://kotlinlang.org/)
- [Ktor](https://ktor.io/)
- [Gradle](https://gradle.org/)
- [Jib](https://github.com/GoogleContainerTools/jib)
  - [Jib Gradle Plugin](https://github.com/GoogleContainerTools/jib/tree/master/jib-gradle-plugin)

#### Continuous delivery

start development environment:
```shell script
skaffold dev
```

dev access endpoint:
```shell script
printf "http://%s:%s\n" $(minikube ip) $(kubectl get svc web -o json | jq '.spec? | .ports? | .[] | .nodePort?')
```

#### Run application with Gradle

```shell script
./gradlew run
```

access the endpoint:
```shell script
curl http://0.0.0.0:8080
```

#### Test application

```shell script
./gradlew test
```

#### Clean up with Gradle
```shell script
./gradlew clean
docker rmi $(docker images -q)
```
