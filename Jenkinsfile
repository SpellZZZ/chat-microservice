pipeline {
    agent {
        label 'docker-agent-alpine'
    }

    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/SpellZZZ/chat-microservice.git'
                sh "chmod +x mvnw"
                sh "java --version"
                sh "ls"
                sh "./mvnw clean install"
           }

        }
        stage('Deliver') {
            steps {
                echo 'Deliver....'
                sh '''
                echo "doing delivery stuff.."
                '''
            }
        }
    }
}
