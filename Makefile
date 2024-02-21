SHELL := /bin/bash
PROJECT_NAME := $(shell basename $(PWD))

.PHONY: run_all build_docker run_docker update_api

run_all:
	@echo "Starting project $(PROJECT_NAME)..."
	@echo "Building docker..."
	make build_docker
	@echo "Running docker..."
	make run_docker

build_docker:
	docker-compose build

run_docker:
	docker-compose up

update_api:
	@echo "Updating API..."
	@echo "Building docker..."
	make build_docker
	@echo "Running docker..."
	make run_docker

report:
	@./dictionary-api/gradlew clean build
	@./dictionary-api/gradlew test
	@./dictionary-api/gradlew jacocoTestReport
	@./dictionary-api/gradlew sonarqube
	@./dictionary-api/gradlew jacocoTestCoverageVerification