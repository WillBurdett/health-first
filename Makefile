build-member-service:
	cd member-service/src/main/resources/scripts; ./build.sh
.PHONY: build-member-service

build-welcome-service:
	cd welcome-service/src/main/resources/scripts; ./build.sh
.PHONY: build-welcome-service

build-classes-service:
	cd classes-service/src/main/resources/scripts; ./build.sh
.PHONY: build-classes-service

build-email-service:
	cd email-service/src/main/resources/scripts; ./build.sh
.PHONY: build-email-service

purge-member-service:
	cd member-service/src/main/resources/scripts; ./purge.sh
.PHONY: purge-member-service

purge-welcome-service:
	cd welcome-service/src/main/resources/scripts; ./purge.sh
.PHONY: purge-welcome-service

purge-classes-service:
	cd classes-service/src/main/resources/scripts; ./purge.sh
.PHONY: purge-classes-service

purge-email-service:
	cd email-service/src/main/resources/scripts; ./purge.sh
.PHONY: purge-email-service

deploy-services:
	docker-compose up
.PHONY: deploy-services

purge-services:
	docker-compose down
.PHONY: purge-services

deploy: build-member-service build-welcome-service build-classes-service build-email-service deploy-services

purge: purge-services purge-member-service purge-welcome-service purge-classes-service purge-email-service
