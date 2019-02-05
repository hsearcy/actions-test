pipeline {
  agent any
  stages {
    stage('error') {
      steps {
        jiraJqlSearch(jql: 'project = APP AND component = "Partnership Service"', auditLog: true, failOnError: true, site: 'Gather')
      }
    }
  }
}