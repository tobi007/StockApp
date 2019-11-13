pipeline {
    agent {
        docker { image 'mzagar/jenkins-slave-jdk-maven-git' }
    }
    stages {
        stage("Compile") {
            steps {
                sh "ls"
            }
        }
        stage("Unit test") {
            steps {
                sh "mvn --version"
            }
        }
    }
}