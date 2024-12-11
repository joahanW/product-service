pipeline{
    agent any
    stages{
         stage("Prepare"){
            steps{
                echo "Start Job: ${env.JOB_NAME}"
                echo "Build number: ${env.BUILD_NUMBER}"
                echo "Branch name github: ${env.BRANCH_NAME}"
            }
        }
        stage("Build"){
            steps{
                echo 'Start Build..'
            }
        }
        stage("Test"){
            steps{
                echo 'Start Test..'
            }
        }

        stage("Deploy"){
            steps{
                echo 'Start Deploy..'
            }
        }
    }

    post{
        always{
            echo 'This will always run'
        }
        success{
            echo 'This will run only if successful'
        }
        failure{
            echo 'This will run only if failed'
        }
        cleanup{
            echo 'This will run when SUCCESS or FAILED'
        }
    }
}