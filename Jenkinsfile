pipeline{
    agent any
    stages{
        stage('Hello'){
            steps{
                echo 'Hello World'
            }
        }
    }

    post{
        always{
            echo "This for always notify!!!"
        {
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