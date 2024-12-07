pipeline{
    agent any

    environment {
        AUTHOR = "Johan Works"
        EMAIL = "johan.works@gmail.com"
        WEB = "https://johanmarkets.com"
    }

    stages{
        stage('Prepare'){
            steps{
                echo ('Author ${AUTHOR}')
                echo ('Email ${EMAIL}')
                echo ('Web ${WEB}')
                echo ('Start Job: ${env.JOB_NAME}')
                echo ('Start Build Number: ${env.BUILD_NUMBER}')
                echo ('Branch name: ${env.BRANCH_NAME}')
            }
        }
        stage('Build'){
            steps{
                echo ('Start Build')
                sh('chmod +x ./mvnw')
                sh('./mvnw clean compile test-compile')
                echo ('End Build')
            }
        }
        stage('Test'){
            steps{
                echo ('Start Test')
                sh('./mvnw test')
                echo ('End Test')

            }
        }
        stage('Deploy'){
            steps{
                echo ('Start Deploy')
                sleep(5)
                echo('End Deploy')
            }
        }
    }

    post{
        always{
            echo "This for always notify!!!"
        }
        success{
            echo "Notify Success"
        }
        failure{
            echo "Notify Failure"
        }
        cleanup{
            echo "Dont care success or error"
        }
    }
}