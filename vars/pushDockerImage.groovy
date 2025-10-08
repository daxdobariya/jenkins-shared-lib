// vars/pushDockerImage.groovy
def call(String imageName, String dockerHubUser, String dockerHubPassword) {
    echo "Logging in and pushing Docker image to Docker Hub: ${dockerHubUser}/${imageName}"

    // Login to Docker Hub
    sh "echo ${dockerHubPassword} | docker login -u ${dockerHubUser} --password-stdin"

    // Tag and push
    sh "docker tag ${imageName}:latest ${dockerHubUser}/${imageName}:latest"
    sh "docker push ${dockerHubUser}/${imageName}:latest"

    // Logout
    sh "docker logout"
}
