pipeline{
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    environment{
        SCANNER_HOME= tool 'sonarqube-scanner'
    }

    stages{
        stage('Check Docker') {
            steps {
                sh 'docker --version'
                echo("TEST DOCKER!!")
            }
         }
         stage('Setup Database') {
            steps {
                sh '''
                docker run -d --network postgres-network --name postgres-test \
                -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password \
                -e POSTGRES_DB=product_service_db postgres:latest
                sleep 10
                '''
            }
        }
        stage("Integration Testing"){
            steps{
                sh("mvn verify")
            }
        }
        stage("SonarQube Analysis"){
            steps{
               withSonarQubeEnv('sonarqube-server') {
                   sh ''' $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=product-service \
                   -Dsonar.projectKey=product-service \
                   -Dsonar.java.binaries=. '''
               }
            }
        }
    }
}