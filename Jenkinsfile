pipeline {
  agent any
  stages {
    stage('') {
      steps {
        jiraJqlSearch(jql: 'project = APP AND component = "Partnership Service"', auditLog: true, failOnError: true)
      }
    }
  }
}