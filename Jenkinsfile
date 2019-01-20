pipeline {
  agent any
  stages {
    stage('error') {
      steps {
        nodejs 'Node 8'
        sh '''cd ./packages/sls-test-A
yarn'''
      }
    }
  }
}