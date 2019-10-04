pipeline {
    agent any

  environment {
    def mvn_version = 'Maven3'
  }
  stages {

    stage('clean package test'){
      steps {

        withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] ) {
          sh 'mvn clean package'
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
  }

}