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
        stage("Code Compile"){
            steps{
                sh("mvn clean compile")
            }
        }
        stage("Unit Testing"){
            steps{
                sh("mvn test")
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