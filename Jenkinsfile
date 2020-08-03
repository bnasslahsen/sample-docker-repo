
pipeline {
   agent any
	stages {
	  stage('do everything in docker') {
		agent {
			docker {
				image 'maven:3-alpine'
				args '-v /var/lib/jenkins/.m2:/root/.m2'
			}
		}
		stages {
			stage('Test') {
				steps {
				//	sh 'mvn -B clean test'
					sh 'echo hi'
				}
			}
			stage('Build') {
				steps {
					sh 'mvn -B -DskipTests package'
					archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
				}
			}
		  }
	  }
	def dockerImage
	def pushToRegitry = {
	      dockerImage.push()
	      dockerImage.push("${env.BUILD_NUMBER}")
	}
		
	  stage('build docker') {
			steps {
			  script {
				sh "mkdir -p target"
				sh "cp -R ddd-sample-exposition/Dockerfile target/"
				sh "cp -R ddd-sample-exposition/target/* target/"
				dockerImage = docker.build('bnasslahsen/jenkins-repo', 'target')
				}
			}
		}
		stage('Deploy Image') {
		  steps{
		   script {
			  docker.withRegistry('https://registry.hub.docker.com', 'docker-login',pushToRegitry)
			  }
			}
		  }
	}
}

