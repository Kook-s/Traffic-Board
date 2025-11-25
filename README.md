# Traffic Article

## Introduction
대규모 트래픽 환경을 고려해 설계한 게시판 시스템 프로젝트 입니다. 
서비스는 MSA 구조로 분리되어 있으며. 도메인 이벤트는 Kafka를 통해 전달되어 서비스 간 결합도를 낮추고 확장성을 높였습니다.
또한 데이터 증가와 트래픽 급증에 대응하기 위해 샤딩 전략을 고려한 테이블 구조와 Outbox 기반 이벤트 발행 방식을 적용했습니다.
읽기 기능은 별도의 Read 모델을 운영하고 Redis를 활용해 고성능 조회가 가능하도록 구성했습니다.

## Teach Stack
- Java 17
- Spring Boot 3.3.4
- Database: MySQL
- ORM: JPA
- Cache/Lock: Redis
- Messaging: Kafka

## Document
#### [ERD](./doc/erd.md)

## How To Run
Docker compose 파일을 따로 두지 않고, 개별로 실행함
필요시 컴포즈 파일로 설정 가능

### 1. Run MySQL / Redis / Kafka
#### MySQL
```bash
$ docker run --name board-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:8.0.38 
```
#### Redis
```bash 
$ docker run --name board-redis -d -p 6379:6379 redis:7.4
```
#### Kafka
```bash 
$ docker run -d --name board-kafka -p 9092:9092 apache/kafka:3.8.0
```

### 2. Run Spring boot
```bash
./gradlew :service:<project name>:bootRun
```