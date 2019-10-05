pipeline {
    agent any

  environment {
    def mvn_version = 'Maven3'
  }
  stages {

    stage('clean package test'){
      steps {
        withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
          sh 'mvn clean package test'
        }
      }
    }
    stage('archival'){
      steps {
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'target/site/jacoco/', reportFiles: 'index.html', reportName: 'Code coverage', reportTitles: ''])
        archiveArtifacts 'target/*.jar'
        step([$class: 'JUnitResultArchiver', testResults: 'target/surefire-reports/TEST-*.xml'])
      }
    }
    stage('sonarqube'){
      steps {
        withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
          sh 'mvn sonar:sonar -Dsonar.projectKey=rest-user -Dsonar.host.url=http://sonarqube:9502 -Dsonar.login=7e96e5a79795c51a5f1d8457cefabbddfcba46c2'
        }
      }
    }
  }

}