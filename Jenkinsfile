pipeline {
    agent any

--    tools {
--        gradle '3.3'
--    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Clean') {
            steps {
                sh "mvn clean install"
            }
        }

        stage('Compile') {
            steps {
                sh "mvn clean install"
            }
        }
}
