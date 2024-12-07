pipeline{
    agent any
    stages{
        stage('Build'){
            steps{
                echo 'Hello Build'
            }
        }
        stage('Test'){
            steps{
                echo 'Hello Test'
            }
        }
        stage('Deploy'){
            steps{
                echo 'Hello Deploy'
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