pipeline {
    agent any

    tools {
        maven 'Maven 3.3.9'
    }

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

}
