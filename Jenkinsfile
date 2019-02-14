pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        script {
          def issues =  jiraJqlSearch(jql: 'project = Jenkins AND status = Done AND labels = service1', auditLog: true, failOnError: true, site: 'Gather')
          //echo issues.data.toString()
          // echo issues.data.issues[0].key.toString()
          for (issue in issues.data.issue) {
            echo issue.key.toString()
          }


          def testIssue = [fields: [ components: [[name: 'Venue App']]]]

          response = jiraEditIssue(idOrKey: 'APP-2378', issue: testIssue, site: 'Gather')

          echo response.data.toString()
        }

      }
    }
  }
}
