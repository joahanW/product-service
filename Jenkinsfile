pipeline{
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
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
    }


}