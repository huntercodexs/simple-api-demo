pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/huntercodexs/simple-api-demo.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install' // Use 'mvn clean install' if using Maven
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test' // Use 'mvn test' if using Maven
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package' // Use 'mvn package' if using Maven
            }
        }
        stage('Deploy') {
            steps {
                // Add your deployment steps here, e.g., using SCP, SSH, Docker, etc.
                //sh 'scp build/libs/*.jar user@server:/path/to/deploy'
                echo 'DEPLOY IN PROGRESS'
            }
        }
    }

    post {
        success {
            echo 'Build and Deploy succeeded!'
        }
        failure {
            echo 'Build or Deploy failed!'
        }
    }
}
