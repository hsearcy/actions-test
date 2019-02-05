pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        script {
          def issues =  jiraJqlSearch(jql: 'project = APP AND status = "Merge Request"', auditLog: true, failOnError: true, site: 'Gather')
          echo issues.data.toString()
          echo issues.data.issues[0].key.toString()

          def testIssue = [fields: [ components: ["Partnership Service"]]]

          response = jiraEditIssue(idOrKey: 'APP-2378', issue: testIssue, site: 'Gather')

          echo response.data.toString()
        }

      }
    }
  }
}