pipeline {
    agent {label 'java-mvn'}
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