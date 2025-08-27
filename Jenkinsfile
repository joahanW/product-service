pipeline {
    agent any

    stages {
        stage("Prepare"){
            steps {
                echo("Start Job:${env.JOB_NAME}")
                echo("Build Number:${env.BUILD_NUMBER}")
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
        stage('Deploy') {
            steps {
                sleep(5)
                echo 'This for Deploy'
            }
        }
    }
}
