#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }
    docker.image('jhipster/jhipster:v5.3.1').inside('-u root -v /root/.m2:/root/.m2 -e MAVEN_OPTS="-Duser.home=./"') {
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
            archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
        }
        
/*         stage('quality analysis') {
            withSonarQubeEnv('sonar') {
                sh "./mvnw sonar:sonar"
            }
        }*/
        
    }

    def dockerImage
    stage('build docker') {
        sh "sudo cp -R ddd-sample-exposition/src/main/docker/Dockerfile ddd-sample-exposition/target/"
        dockerImage = docker.build('bnasslahsen/jenkins-repo', 'ddd-sample-exposition/target/')
    }

    stage('publish docker') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker-login') {
            dockerImage.push 'latest'
        }
    }
    
    
}
