build-member-service:
	cd member-service/src/main/resources/scripts; ./build.sh
.PHONY: build-member-service

deploy-member-service:
	cd member-service/src/main/resources/scripts; ./build.sh; docker-compose up
.PHONY: deploy-member-service

build-config-server:
	cd config-server/src/main/resources/scripts; ./build.sh
.PHONY: build-config-server

deploy-config-server:
	cd config-server/src/main/resources/scripts; ./build.sh; docker-compose up
.PHONY: deploy-config-server

build-welcome-service:
	cd welcome-service/src/main/resources/scripts; ./build.sh
.PHONY: build-welcome-service

deploy-welcome-service:
	cd welcome-service/src/main/resources/scripts; ./build.sh; docker-compose up
.PHONY: deploy-welcome-service

build-classes-service:
	cd classes-service/src/main/resources/scripts; ./build.sh
.PHONY: build-classes-service

deploy-classes-service:
	cd classes-service/src/main/resources/scripts; ./build.sh; docker-compose up
.PHONY: deploy-classes-service

build-email-service:
	cd email-service/src/main/resources/scripts; ./build.sh
.PHONY: build-email-service

deploy-email-service:
	cd email-service/src/main/resources/scripts; ./build.sh; docker-compose up
.PHONY: deploy-email-service

purge-member-service:
	cd member-service/src/main/resources/scripts; ./purge.sh
.PHONY: purge-member-service

purge-config-server:
	cd config-server/src/main/resources/scripts; ./purge.sh
.PHONY: purge-config-server

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

deploy: build-config-server build-member-service build-welcome-service build-classes-service build-email-service deploy-services

purge: purge-services purge-config-server purge-member-service purge-welcome-service purge-classes-service purge-email-service
