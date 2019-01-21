pipeline {
    agent any

    tools {
        maven 'Maven 3.6.0'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }

    def scmVars

    stages {

        stage('Checkout') {
            steps {
              scmVars = checkout scm
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Build Downstream Projects'){
            steps{
                build job: 'a2si-dos-proxy/scmVars.GIT_BRANCH',
                propagate: true,
                wait: false
            }
        }

}

}
