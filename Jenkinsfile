pipeline {
    agent { dockerfile true }
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