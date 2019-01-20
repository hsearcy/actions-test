pipeline {
  agent any
  stages {
    stage('Yarn install') {
      steps {
        nodejs('Node 8') {
          sh '''cd ./packages/sls-test-A
yarn'''
        }

      }
    }
    stage('Test') {
      steps {
        sh 'yarn jest'
      }
    }
  }
}