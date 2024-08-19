# university-rest-api
A Rest API with Spring Boot


In order to run program on local kubernetes environment:__
minikube start__
kubectl apply -f postgres-cm.yaml__
kubectl apply -f postgres-secret.yaml__
kubectl apply -f k8s-db.yaml__
kubectl apply -f k8s-api.yaml__
minikube service university-api-service
