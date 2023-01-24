build-member-service:
	cd member-service; ./deploy.sh
.PHONY: build-member-service

build-welcome-service:
	cd welcome-service; ./deploy.sh
.PHONY: build-welcome-service

build-classes-service:
	cd classes-service; ./deploy.sh
.PHONY: build-classes-service

purge-member-service:
	cd member-service; ./purge.sh
.PHONY: purge-member-service

purge-welcome-service:
	cd welcome-service; ./purge.sh
.PHONY: purge-welcome-service

purge-classes-service:
	cd classes-service; ./purge.sh
.PHONY: purge-classes-service

deploy-services:
	docker-compose up
.PHONY: deploy-services

purge-services:
	docker-compose down
.PHONY: purge-services

deploy: build-member-service build-welcome-service build-classes-service deploy-services

purge: purge-services purge-member-service purge-welcome-service purge-classes-service
