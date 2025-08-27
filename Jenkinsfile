pipeline {
    agent any

    environment {
        AUTHOR = "Johan Work"
        EMAIL = "johan.work@gmail.com"
        WEB = "johanwork.com"
    }

    stages {
        stage("Prepare"){
            environment{
                APP = credentials('Postgres-Credential')
            }
            steps {
                echo("Start Job:${env.JOB_NAME}")
                echo("Build Number:${env.BUILD_NUMBER}")
                echo("${AUTHOR}")
                echo("${EMAIL}")
                echo("${APP_USR}")
                echo("${APP_PSW}")
            }
        }
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
        stage('Build Docker') {
            steps {
                sh("sudo docker build -t johanwork/product-service .")
            }
        }
        stage('Push Docker to Repository'){
            steps{
                sh("sudo docker push product-service")
            }
        }
    }
}
