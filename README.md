# university-rest-api
A Rest API with Spring Boot


In order to run program on local kubernetes environment:
minikube start
kubectl apply -f postgres-cm.yaml
kubectl apply -f postgres-secret.yaml
kubectl apply -f k8s-db.yaml
kubectl apply -f k8s-api.yaml
minikube service university-api-service
