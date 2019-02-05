pipeline {
  agent any
  stages {
    stage('JIRA Search') {
        def issues = jiraJqlSearch(jql: 'project = APP AND component = "Partnership Service"', auditLog: true, failOnError: true, site: 'Gather')
        echo issues.data.toString()
    }
  }
}
