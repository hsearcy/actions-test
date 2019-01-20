pipeline {
  agent any
  stages {
    stage('error') {
      steps {
        sh '''cd ./packages/sls-test-A
npm install -g yarn
yarn'''
      }
    }
  }
}