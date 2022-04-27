#!/usr/bin/env groovy

node {
	stage('checkout') {
		checkout scm
	}
	docker.image('maven:3.8.5-openjdk-11-slim') {
		stage('check java') {
			sh "java -version"
		}

		stage('clean') {
			sh "chmod +x mvnw"
			sh "./mvnw clean"
		}

		stage('backend tests') {
			try {
				 sh "./mvnw test"
			} catch (err) {
				throw err
			} finally {
				junit '**/target/surefire-reports/TEST-*.xml'
			}
		}

		stage('quality analysis') {
			withSonarQubeEnv('Sonar') {
				sh "./mvnw org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=bnasslahsen_sample-docker-repo"
			}
		}

		stage('packaging') {
			sh "./mvnw package -DskipTests"
			archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
		}

	}

	def dockerImage
	stage('build docker') {
		//sh "sudo mkdir target"
		sh "sudo cp -R ddd-sample-exposition/Dockerfile target/"
		sh "sudo cp -R ddd-sample-exposition/target/* target/"

		dockerImage = docker.build('bnasslahsen/jenkins-repo', 'target')
	}

	stage('publish docker') {
		docker.withRegistry('https://registry.hub.docker.com', 'docker-login') {
			dockerImage.push 'latest'
		}
	}


}
