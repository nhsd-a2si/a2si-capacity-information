pipeline {
    agent any

    tools {
        maven 'Maven 3.6.0'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }

    stages {

        stage('Checkout') {
            steps {
                step{
                    checkout scm
                }
                step{
                    def branchName = scmVars.GIT_BRANCH
                }
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Build Downstream Projects'){
            steps{
                build job: 'a2si-dos-proxy/$branchName',
                propagate: true,
                wait: false
            }
        }

}

}
