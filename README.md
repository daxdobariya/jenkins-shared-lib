# Jenkins Shared Library

This repository contains **Groovy functions for Jenkins pipelines**, designed to simplify repetitive tasks such as building Docker images, running containers, and pushing images to Docker Hub.  

It is intended for beginners and can be extended in the future to include more complex tasks.  

---

## Repository Structure

jenkins-shared-lib/
├── vars/
│ ├── buildDockerImage.groovy # Build Docker image from Dockerfile
│ ├── runDockerContainer.groovy # Stop old container and run new container
│ └── pushDockerImage.groovy # Push Docker image to Docker Hub
├── src/ # Optional folder for reusable classes
│ └── org/example/Helper.groovy
└── README.md # This documentation


- **`vars/`**: Contains Groovy functions callable directly in a Jenkinsfile.  
- **`src/`**: Can be used for classes or helper functions for more complex logic.  
- **`README.md`**: Documentation and usage guide.  

---

## Current Functions

1. **`buildDockerImage(imageName)`**  
   Builds a Docker image from the project’s Dockerfile.  

2. **`runDockerContainer(containerName, imageName, port)`**  
   Stops any existing container with the same name and runs a new container.  

3. **`pushDockerImage(imageName, dockerHubUser, dockerHubPassword)`**  
   Logs in to Docker Hub and pushes the image.  

---

## Example Jenkinsfile Usage

```groovy
@Library('my-shared-lib') _

pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "covid19-dashboard"
        CONTAINER_NAME = "covid19-dashboard"
        DOCKER_HUB_USER = "daxdobariya"
        DOCKER_HUB_PASS = credentials('docker-hub-password')
        GIT_URL = "https://github.com/daxdobariya/covid19-dashboard.git"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: "${GIT_URL}"
            }
        }

        stage('Build Docker Image') {
            steps {
                buildDockerImage(DOCKER_IMAGE)
            }
        }

        stage('Run Docker Container') {
            steps {
                runDockerContainer(CONTAINER_NAME, DOCKER_IMAGE, "8000")
            }
        }

        stage('Push Docker Image') {
            steps {
                pushDockerImage(DOCKER_IMAGE, DOCKER_HUB_USER, DOCKER_HUB_PASS)
            }
        }
    }
}
Future Enhancements

Add more complex pipeline tasks like database migrations, Kubernetes deployment, or Slack notifications.

Organize src/ with helper classes to handle reusable logic across multiple pipelines.

Add versioning for library functions for safer production deployment.

Integrate environment-based configurations for staging, testing, and production.

