pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        script {
          def issues =  jiraJqlSearch(jql: 'project = APP AND status = "Merge Request"', auditLog: true, failOnError: true, site: 'Gather')
          echo issues.data.toString()
          echo issues.data.issues[0].key.toString()
        }

      }
    }
  }
}