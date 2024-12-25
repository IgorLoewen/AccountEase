pipeline {
    agent any
    tools {
        maven 'Maven' // Stelle sicher, dass Maven in Jenkins konfiguriert ist
        jdk 'JDK17'   // Nutze JDK 17
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm // Holt den Code aus dem Repository
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install' // Baut das Projekt
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn test' // Führt die Tests aus
            }
        }
        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS', // Generiere immer den Report
                    results: [[path: 'target/allure-results']] // Pfad zu den Allure-Resultaten
                ])
            }
        }
    }
    post {
        always {
            echo 'Build abgeschlossen.' // Loggt eine Abschlussmeldung
        }
        success {
            echo 'Build erfolgreich!' // Loggt eine Erfolgsmeldung
        }
        failure {
            echo 'Build fehlgeschlagen!' // Loggt eine Fehlermeldung
        }
    }
}
