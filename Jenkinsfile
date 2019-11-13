pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh "docker image ls"
            }
        }
        stage("Unit test") {
            steps {
                sh "mvn test"
            }
        }
    }
}