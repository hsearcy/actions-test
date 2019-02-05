pipeline {
  agent any
  stages {
    stage('error') {
      steps {
        def issues = jiraJqlSearch(jql: 'project = APP AND component = "Partnership Service"', auditLog: true, failOnError: true, site: 'Gather')
        echo issues.data.toString()
      }
    }
  }
}
