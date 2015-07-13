# rod

[![Build Status](https://travis-ci.org/ekholabs/rod.svg)](https://travis-ci.org/ekholabs/rod)


## Start REST API

```bash
mvn install && mvn -pl rod-server spring-boot:run
```

### Register Dummy Resource

curl localhost:8080/resource/register

### Observe commands generated from Dummy Observations

```bash
curl localhost:8080/resource/observe
```

### Stop observing commands

```bash
curl localhost:8080/resource/stop-observing
```

