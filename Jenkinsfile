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
                checkout scm
                sh 'echo Branch Name: $BRANCH_NAME'
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
