pipeline {
    agent any

    tools {
        maven '3.6.0'
    }

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '3'))
    }

    stages {

        stage('Checkout project') {
            steps {
              echo 'Pulling...' + env.BRANCH_NAME
              checkout scm
            }
        }

        stage('Clean project') {
            steps {
                sh "mvn clean"
            }
        }

        stage('Validate project') {
            steps {
                sh "mvn validate"
            }
        }

        stage('Compile project') {
            steps {
                sh "mvn compile"
            }
        }

        stage('Build project') {
            steps {
                sh "mvn build"
            }
        }

        stage('Run unit tests') {
            steps {
                sh "mvn test"
            }
        }

        stage('Install project') {
            steps {
                sh "mvn install -DskipTests=true"
            }
        }

        stage('Build Downstream Projects'){
            steps{
                build job: 'a2si-capacity-service-client/' + env.BRANCH_NAME,
                propagate: true,
                wait: false

                build job: 'a2si-capacity-service/' + env.BRANCH_NAME,
                propagate: true,
                wait: false
            }
        }
    }
}
