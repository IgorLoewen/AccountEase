pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeResults: true
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline abgeschlossen!'
        }
        success {
            echo 'Pipeline erfolgreich ausgeführt!'
        }
        failure {
            echo 'Pipeline fehlgeschlagen.'
        }
    }
}
