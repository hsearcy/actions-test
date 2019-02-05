pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        jiraJqlSearch(jql: 'project = APP AND component = "Partnership Service"', auditLog: true, failOnError: true, site: 'Gather')
        script {
          def issues =  jiraJqlSearch(jql: 'project = APP AND component = "Partnership Service"', auditLog: true, failOnError: true, site: 'Gather')
          echo issues.data.toString()
        }

      }
    }
  }
}