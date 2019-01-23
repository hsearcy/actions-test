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
        nodejs('Node 8') {
          sh '''cd ./packages/sls-test-A
yarn jest'''
        }

      }
    }
    stage('Deploy') {
      steps {
        input 'Deploy?'
        nodejs('Node 8') {
          sh '''cd ./packages/sls-test-A
sls deploy'''
        }

      }
    }
    stage('Remove') {
      steps {
        input 'Remove sls'
        nodejs('Node 8') {
          sh '''cd ./packages/sls-test-A
sls remove'''
        }

      }
    }
  }
}