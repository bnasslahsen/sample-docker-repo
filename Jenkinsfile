#!/usr/bin/env groovy

node {
	stage('checkout') {
		checkout scm
	}
	docker.image("docker pull openjdk:11-jdk-slim").inside('--network="host" -u root -v $HOME/.m2:/root/.m2') {
		stage('check java') {
			sh "chmod +x mvnw"
			sh "./mvnw clean"
		}

		stage('clean') {
			sh "./mvnw test"
		}

		stage('backend tests') {
			try {
				 sh "mvn test"
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
