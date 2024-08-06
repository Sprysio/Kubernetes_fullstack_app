# Kubernetes Postgres-SpringBoot-React Application

## Overview

This guide provides a comprehensive explanation for deploying a full-stack application consisting of PostgreSQL, Spring Boot, and React using Kubernetes. The deployment is organized into three primary components:

1. Postgres Database - Provides persistent data storage.
2. Product Service - A Spring Boot application serving as the backend.
3. Product Front - A React application serving as the frontend.

## Components Explanation

### Postgres Configuration

#### ConfigMap

- **Stores SQL initialization scripts**
- **Used to initialize the PostgreSQL database with necessary tables and data.**
- **ConfigMap allows configuration to be decoupled from the container image, making it easier to manage and update.**

#### PersistentVolume (PV)

- **Provides persistent storage for the database.**
- **Ensures data is retained even if the PostgreSQL pod is restarted.**
- **HostPath storage class indicates local disk usage on the node.**

#### PersistentVolumeClaim (PVC)

- **Claims the persistent volume for use by PostgreSQL.**
- **Defines the storage size and access mode.**
- **Ensures the PostgreSQL pod has access to the defined persistent storage.**

#### Secret

- **Stores sensitive information such as database username and password.**
- **Ensures sensitive data is not hardcoded into the application configuration.**
- **Data is stored in a base64-encoded format for security.**

#### Service

- **Exposes the PostgreSQL database within the Kubernetes cluster.**
- **Allows other components (like the Spring Boot application) to communicate with the database.**
- **Defines the port and target port for database access.**

#### StatefulSet

- **Manages the deployment and scaling of PostgreSQL pods.**
- **Ensures each pod has a stable network identity and persistent storage.**
- **Ensures ordered deployment, scaling, and termination, which is essential for stateful applications like databases.**

### Product Service Configuration

#### Deployment

- **Manages the deployment of the Spring Boot application.**
- **Ensures the specified number of replicas are running.**
- **Defines the Docker image to use, environment variables, and ports.**
- **Includes liveness and readiness probes to monitor the health of the application.**

#### Service

- **Exposes the Spring Boot application within the Kubernetes cluster.**
- **Allows the frontend and other services to communicate with the backend.**
- **Defines the port and target port for backend access.**

### Product Front Configuration

#### Deployment

- **Manages the deployment of the React application.**
- **Ensures the specified number of replicas are running.**
- **Defines the Docker image to use, environment variables, and ports.**
- **Includes liveness and readiness probes to monitor the health of the application.**

#### ConfigMap

- **Stores non-sensitive configuration data for the React application.**
- **Allows configuration to be updated without changing the container image.**

#### Ingress

- **Manages external access to the services within the cluster.**
- **Defines rules for routing traffic to the backend and frontend services.**
- **Uses annotations to customize the behavior of the ingress controller, such as URL rewriting.**

#### Secret

- **Stores sensitive information such as API keys or database credentials.**
- **Ensures sensitive data is not hardcoded into the application configuration.**
- **Data is stored in a base64-encoded format for security.**

#### Service

- **Exposes the React application within the Kubernetes cluster.**
- **Allows users to access the frontend via a defined port and target port.**


## Setup and Running

### Prerequisites

- Kubernetes(I did k3s)
- Helm

### Setup

Install kubernetes distribution of your choice i did k3s:

```sh
curl -sfL https://get.k3s.io | sh -
```

For k3s there is also a need to download ingress-nginx

```sh
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install nginx-ingress ingress-nginx/ingress-nginx --namespace ingress-nginx --create-namespace
```

### Running the Project

To start the project, navigate to the project directory and run:

```sh
helm install postgres ./postgres/template
helm install product-service ./product-service/template
helm install product-front ./product-front/template
```

Those commands will install the apps. The frontend react app will be available on port 80 with / prefix and backend will be available on prot 8080 with /api prefix.

## Conclusion
By following this setup, you ensure that each component of your application is properly configured and managed within the Kubernetes environment. This approach leverages Kubernetes' capabilities to provide reliable, scalable, and maintainable infrastructure for your full-stack application.

## Licensing
This project is licensed under the MIT License. See the LICENSE file for more details.
