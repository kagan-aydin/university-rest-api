# university-rest-api
A Rest API with Spring Boot


In order to run program on local kubernetes environment:   <br />
    minikube start   <br />
    kubectl apply -f postgres-cm.yaml   <br />
    kubectl apply -f postgres-secret.yaml   <br />
    kubectl apply -f k8s-db.yaml   <br />
    kubectl apply -f k8s-api.yaml   <br />
    minikube service university-api-service   <br />


Consuming messages from Kafka console:   <br />
    docker run -d -p 9092:9092 --name broker apache/kafka:latest <br />
    docker exec -it broker sh <br />
    bin/kafka-topics.sh --bootstrap-server localhost:9092 --topic test-topic --create <br />
    bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning   <br />
