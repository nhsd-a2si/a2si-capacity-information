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
              echo 'Pulling...' + env.BRANCH_NAME
              checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Build Downstream Projects'){
            steps{
                build job: 'a2si-dos-proxy/env.BRANCH_NAME',
                propagate: true,
                wait: false
            }
        }

}

}
