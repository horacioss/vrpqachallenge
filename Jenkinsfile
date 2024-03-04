pipeline {
    agent any

    environment {
        // Define browser types to run tests
        BROWSER_TYPES = ['chrome', 'firefox', 'edge']
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build the project
                    sh 'mvn clean package -DskipTests=true'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Run tests for each browser type and generate Allure report
                    for (def browserType in BROWSER_TYPES) {
                        sh "mvn clean test -DbrowserType=${browserType}"
                    }
                }
            }
        }

        stage('Allure Report') {
            steps {
                script {
                    // Generate and serve Allure report
                    sh 'allure generate target/allure-results --clean'
                    allureServe()
                }
            }
        }
    }

    post {
        always {
            // Clean up workspace
            cleanWs()
        }
    }
}
