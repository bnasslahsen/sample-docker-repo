#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

   withCredentials([usernamePassword(credentialsId: 'docker-login', passwordVariable: 'pass', usernameVariable: 'user')]) {
    // the code in here can access $pass and $user

    docker.image('openjdk:8').inside('-u root -e MAVEN_OPTS="-Duser.home=./"') {
        stage('check java') {
            sh "java -version"
        }

        stage('clean') {
            sh "chmod +x mvnw"
            sh "./mvnw clean"
        }

        stage('install tools') {
            sh "./mvnw com.github.eirslett:frontend-maven-plugin:install-node-and-npm -DnodeVersion=v10.14.1 -DnpmVersion=6.4.1"
        }

        stage('npm install') {
            sh "./mvnw com.github.eirslett:frontend-maven-plugin:npm"
        }
        stage('packaging and docker Image') {
            sh "./mvnw package -Pprod -Djib.to.auth.username=$user -Djib.to.auth.password=$pass -DskipTests"
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
    }
  }
}
