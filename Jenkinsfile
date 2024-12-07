pipeline{
    agent any
    stages{
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