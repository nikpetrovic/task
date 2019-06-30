# Task Assignment App Redme

## What is missing for PROD

### Implementation
When data for prices is fetched from IEX and stored to database key is a combination of company **symbol** and **lastUpdate** timestamp. It was the easiest way to ensure record are not duplicated.

### Security
Application is publicly available. Some identity provider should be used in order to get access token for authorize usage.

### Unit Tests
Application has poor test coverage. Only few used to demonstrate global idea.

### Minikube
Minikube is suitable for development purposes.

## Run locally
Before you run the app locally you should provide:
- MySQL instance running
- Created Database 'task'
- Root user (with username 'root' and password 'root')

Now you have MySql IP and port so you can execute the following command in order to run the app:
 
`MYSQL_SERVICE_HOST=localhost MYSQL_SERVICE_PORT=3306 ./gradlew bootRun`

##Run in minikube

### Prerequisites
- VirtualBox (from `virtualbox.org`)
- Docker (from `docker.io`)
- Minikube (`brew cask install minikube`)
- kubernetes-cli (`brew install kubernetes-cli`)

Follow these steps:
1. start Minikube: 
`minikube start`.
2. switch context to use minikube to interact with `kubectl` (if more context available): 
`kubectl config use-context minikube`
3. switch to docker deamon on minikube
`eval $(minikube docker-env)`
4. pull App (`task-assignment`) docker image:
`docker pull nikpetrovic/task-assignment:v1.0`
5. start pod for MySQL:
`kubectl run mysql --image=mysql/mysql-server --port=3306 --env MYSQL_ROOT_HOST=% --env MYSQL_ROOT_PASSWORD=root --env MYSQL_DATABASE=task`
6. start pod for App:
`kubectl run task-assignment --image=nikpetrovic/task-assignment:v1.0 --port=8080`
7. expose `task-assignment` to be accessible externally:
`kubectl expose deployment task-assignment --type=NodePort --port=8080`
8. get assigned port of the service and store it to environment variable:
`export NODE_PORT=$(kubectl get services/task-assignment -o go-template='{{(index .spec.ports 0).nodePort}}')`
9. check the service is running:
`curl $(minikube ip):$(echo ${NODE_PORT})/aapl/prices`
