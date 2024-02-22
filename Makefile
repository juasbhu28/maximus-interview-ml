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

test_api:
	@echo "Testing API..."
	@echo "Building API..."
	cd dictionary-api && ./gradlew clean build test

