This repository contains all the source files needed to follow the series [Kubernetes and everything else](https://rinormaloku.com/series/kubernetes-and-everything-else/)

This is the best introduction to Kubernetes and Everything related to be able to deploy scalable and resilient applications on Kubernetes managed clusters.


### Docker sa-logic <---> sa-webapp

docker inspect <container sa-logic>

find:

"NetworkSettings" -> "Networks" -> "bridge" -> "Gateway"

``` 
$ docker run -d -p 8080:8080 -e SA_LOGIC_API_URL='http://<Gateway ip>:<5000 or 5050>' $DOCKER_USER_ID/sentiment-analysis-web-app  
```
 