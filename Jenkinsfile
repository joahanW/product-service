pipeline{
    agent any
<<<<<<< HEAD
    stages{
        stage("Build"){
            steps{
                echo "Start Building"
            }
        }
        stage('Test'){
            steps{
                echo "Start Testing"
            }
        }
        stage('Deploy'){
            steps{
                echo "Start Deploying"
=======

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    environment{
        SCANNER_HOME= tool 'sonarqube-scanner'
    }

    stages{
         stage('Setup Database') {
            steps {
                script{
                    img = 'postgres:latest'
                    docker.image("${img}").run('--name postgres-test -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=product_service_db -d')
                }
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
>>>>>>> 50c4f14e96ffa119ca3325348dc1d326de364b9a
            }
        }
    }
}