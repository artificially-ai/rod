# rod

[![Build Status](https://travis-ci.org/ekholabs/rod.svg)](https://travis-ci.org/ekholabs/rod)
[![Coverage Status](https://coveralls.io/repos/ekholabs/rod/badge.svg?branch=master&service=github)](https://coveralls.io/github/ekholabs/rod?branch=master)

## Start REST API

```bash
mvn install && mvn -pl rod-server spring-boot:run
```

### Register Dummy Resource

```bash
curl localhost:8080/resource/register
```

### Observe commands generated from Dummy Observations

```bash
curl localhost:8080/resource/observe
```

### Stop observing commands

```bash
curl localhost:8080/resource/stop-observing
```

