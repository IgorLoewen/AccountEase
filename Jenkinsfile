pipeline {
    agent any

    tools {
        maven 'Maven' // Ensure Maven is configured in Jenkins
        jdk 'JDK17'   // Use JDK 17 as the Java runtime
    }

    stages {
        stage('Checkout') {
            steps {
                // Retrieve the source code from the repository
                checkout scm
            }
        }
        stage('Build') {
            steps {
                // Clean the project and build the artifacts
                sh 'mvn clean install'
            }
        }
        stage('Run Tests') {
            steps {
                // Execute unit and integration tests
                sh 'mvn test'
            }
        }
        stage('Allure Report') {
            steps {
                // Generate Allure report based on the test results
                allure([
                    includeProperties: false,
                    jdk: '', // Leave JDK configuration empty if not explicitly required
                    properties: [], // Add any specific Allure properties here if needed
                    reportBuildPolicy: 'ALWAYS', // Always generate the Allure report
                    results: [[path: 'target/allure-results']] // Path to Allure results
                ])
            }
        }
    }

    post {
        always {
            // Log a completion message regardless of the build result
            echo 'Build completed.'
        }
        success {
            // Log a success message when the build passes
            echo 'Build successful!'
        }
        failure {
            // Log a failure message when the build fails
            echo 'Build failed!'
        }
    }
}
