pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh("./mvnw clean install")
            }
        }
        stage('Testing') {
            steps {
                sh("./mvnw test")
            }
        }
        stage('Deploy') {
            steps {
                sleep(5)
                echo 'This for Deploy'
            }
        }
    }
}
