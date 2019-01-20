pipeline {
  agent {
    node {
      label 'Deploy'
    }

  }
  stages {
    stage('Yarn install') {
      steps {
        dir(path: './packages/sls-test-A')
        nodejs('Node 8') {
          sh 'yarn'
        }

      }
    }
    stage('Test') {
      steps {
        nodejs('Node 8') {
          sh 'yarn jest'
        }

      }
    }
  }
}