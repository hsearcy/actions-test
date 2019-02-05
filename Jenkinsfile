pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        script {
          def issues =  jiraJqlSearch(jql: 'project = APP AND status = "Merge Request"', auditLog: true, failOnError: true, site: 'Gather')
          echo issues.data.toString()
        }

        jiraJqlSearch(jql: 'project = APP AND status = "Merge Request"', auditLog: true, failOnError: true, site: 'Gather')
        script {
          echo response.data.toString()
        }

      }
    }
  }
}