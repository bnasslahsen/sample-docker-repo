#!/usr/bin/env groovy
pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /var/lib/jenkins/.m2:/root/.m2'
    }
  }
  options {
    skipStagesAfterUnstable()
  }
  stages {
    stage('checkout') {
      checkout scm
    }
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
      } catch(err) {
        throw err
      } finally {
        junit '**/target/surefire-reports/TEST-*.xml'
      }
    }

    stage('packaging') {
      sh "./mvnw package -DskipTests"
      archiveArtifacts artifacts: '**/target/*.jar',
      fingerprint: true
    }
    def dockerImage
    stage('build docker') {
      // sh "sudo mkdir target"
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
}
