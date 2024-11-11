pipeline {
	agent any

	environment {
		mavenHome = tool 'JENKINS_MAVEN_HOME-3.6.3'
	}

	tools {
	    maven "JENKINS_MAVEN_HOME-3.6.3"
		jdk 'java17-native'
	}

	stages {

        stage('Checkout') {
            steps {
                git branch: 'pipeline-release', url: 'https://github.com/huntercodexs/simple-api-demo.git'
            }
        }

		stage('Build'){
			steps {
				sh "mvn clean install -DskipTests"
			}
		}

		stage('Test'){
			steps{
				sh "mvn test"
			}
		}

		stage('Deploy') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: 'ssh_key_jenkins', keyFileVariable: 'ssh_key_jenkins_value')]) {
                    sh '''
                    ssh -i $ssh_key_jenkins_value ubuntu-vbox@192.168.0.24 "mkdir -p /home/ubuntu-vbox/Deployment/simple-api-demo/backup"
                    scp -i $ssh_key_jenkins_value target/classes/application.properties ubuntu-vbox@192.168.0.24:/home/ubuntu-vbox/Deployment/simple-api-demo/application.properties
                    scp -i $ssh_key_jenkins_value target/simple-api-demo-1.0.0-SNAPSHOT.jar ubuntu-vbox@192.168.0.24:/home/ubuntu-vbox/Deployment/simple-api-demo/simple-api-demo-1.0.0-SNAPSHOT.jar
                    ssh -i $ssh_key_jenkins_value ubuntu-vbox@192.168.0.24 "nohup java -jar /home/ubuntu-vbox/Deployment/simple-api-demo/simple-api-demo-1.0.0-SNAPSHOT.jar --spring.config.location=/home/ubuntu-vbox/Deployment/simple-api-demo/application.properties > /home/ubuntu-vbox/Deployment/simple-api-demo/service.out 2> /home/ubuntu-vbox/Deployment/simple-api-demo/errors.txt < /dev/null &"
                    '''
                }
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