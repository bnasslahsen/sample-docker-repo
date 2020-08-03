def dockerImage

pipeline {
	agent {
		docker {
			image 'maven:3-alpine'
			args '-v /var/lib/jenkins/.m2:/root/.m2'
		}
	}
	stages {
		stage('Test') {
			steps {
				sh 'mvn -B clean test'
			}
			post {
				always {
					junit 'target/surefire-reports/*.xml'
				}
			}
		}
		stage('Build') {
			steps {
				sh 'mvn -B -DskipTests package'
				archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
			}
		}

		stage('build docker') {
			steps {
			  script {
				// sh "sudo mkdir target"
				sh "sudo cp -R ddd-sample-exposition/Dockerfile target/"
				sh "sudo cp -R ddd-sample-exposition/target/* target/"
				dockerImage = docker.build('bnasslahsen/jenkins-repo', 'target')
				}
			}
		}

		stage('Deploy Image') {
		  steps{
		   script {
			  docker.withRegistry( '', registryCredential ) {
				dockerImage.push()
			  }
			}
		  }
		}

	}
}

