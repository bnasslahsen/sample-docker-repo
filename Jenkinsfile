#!/usr/bin/env groovy

node {
	stage('clean workspace') {
        cleanWs()
	}

	stage('checkout') {
		// Checkout the repository
		checkout scm
	}

	docker.image("openjdk:11-jdk-slim").inside('--network="host" -u root -v $HOME/.m2:/root/.m2') {
		stage('check java') {
			sh "chmod +x mvnw"
			sh "./mvnw -version"
		}

		stage('clean') {
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

		stage('publish docker') {
			withCredentials([usernamePassword(credentialsId: 'docker-login', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_LOGIN')]) {
				sh './mvnw -pl exposition -Djib.to.auth.username=${DOCKER_LOGIN} -Djib.to.auth.password=${DOCKER_PASSWORD} jib:build'
			}
		}

	}
}
