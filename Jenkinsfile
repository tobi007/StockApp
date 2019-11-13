pipeline {
    agent any
    stages {
        stage("Compile") {
            steps {
                sh "ls"
            }
        }
        stage("Unit test") {
            steps {
                sh "cd StockApp-Service && mvn test"
            }
        }
    }
}