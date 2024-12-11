pipeline{
    agent any

    environment{
        AUTHOR="Johan Market"
        EMAIL="johan@works.com"
    }

    stages{
         stage("Prepare"){
            environment{
                APP= credentials("postgres")
            }
            steps{
                echo "Start Job: ${env.JOB_NAME}"
                echo "Build number: ${env.BUILD_NUMBER}"
                echo "Author= ${AUTHOR}"
                echo "Email= ${EMAIL}"
                echo "App User: ${APP_USR}"
                sh ('echo "App password: $APP_PSW" > "secret.txt"')
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