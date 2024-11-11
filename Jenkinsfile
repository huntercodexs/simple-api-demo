pipeline {
	agent any

	environment {
		mavenHome = tool 'JENKINS_MAVEN_HOME-3.6.3'
	}

	tools {
		jdk 'java17-native'
	}

	stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/spring-boot-app.git'
            }
        }

		stage('Build'){
			steps {
				bat "mvn clean install -DskipTests"
			}
		}

		stage('Test'){
			steps{
				bat "mvn test"
			}
		}

		stage('Deploy') {
            steps {
                sh 'scp target/simple-api-demo-1.0.0-SNAPSHOT.jar ubuntu-vbox@192.168.0.24:/home/ubuntu-vbox/Deployment/simple-api-demo/target/simple-api-demo-1.0.0-SNAPSHOT.jar'
                sh 'ssh ubuntu-vbox@192.168.0.24 "nohup java -jar /home/ubuntu-vbox/Deployment/simple-api-demo/target/simple-api-demo-1.0.0-SNAPSHOT.jar --spring.config.location=/home/ubuntu-vbox/Deployment/simple-api-demo/target/classes/application.properties > /home/ubuntu-vbox/Deployment/simple-api-demo/service.out 2> /home/ubuntu-vbox/Deployment/simple-api-demo/errors.txt < /dev/null &"'
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