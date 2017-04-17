# rod

[![Build Status](https://travis-ci.org/ekholabs/rod.svg)](https://travis-ci.org/ekholabs/rod)
[![Coverage Status](https://coveralls.io/repos/ekholabs/rod/badge.svg?branch=master&service=github)](https://coveralls.io/github/ekholabs/rod?branch=master)
<a href="https://scan.coverity.com/projects/5771">
  <img alt="Coverity Scan Build Status"
       src="https://scan.coverity.com/projects/5771/badge.svg"/>
</a>

<p/>
[![wercker status](https://app.wercker.com/status/1fc82ce885f244cff3f6c71088f69b46/m "wercker status")](https://app.wercker.com/project/bykey/1fc82ce885f244cff3f6c71088f69b46)

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

 
