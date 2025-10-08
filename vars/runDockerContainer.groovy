// vars/runDockerContainer.groovy
def call(String containerName, String imageName, String port = "8000") {
    echo "Removing old container if exists: ${containerName}"
    sh """
        if [ \$(docker ps -aq -f name=${containerName}) ]; then
            docker rm -f ${containerName} || true
        fi
    """
    echo "Running new container: ${containerName}"
    sh "docker run -dt --name ${containerName} -p ${port}:${port} ${imageName}:latest"
}
