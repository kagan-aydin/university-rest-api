# university-rest-api
A Rest API with Spring Boot


In order to run program on local kubernetes environment:  
    minikube start  
    kubectl apply -f postgres-cm.yaml  
    kubectl apply -f postgres-secret.yaml  
    kubectl apply -f k8s-db.yaml  
    kubectl apply -f k8s-api.yaml  
    minikube service university-api-service  

Consuming messages from Kafka console:
    docker run -d -p 9092:9092 --name broker apache/kafka:latest
    docker exec -it broker sh
    bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic test-topic --create
    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
