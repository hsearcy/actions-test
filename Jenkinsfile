pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        script {
          def issues = jiraJqlSearch(jql: 'project = Jenkins AND status = Done AND labels = service1', auditLog: true, failOnError: true, site: 'Gather')
          //echo issues.data.toString()
          // echo issues.data.issues[0].key.toString()
          issues.data.issues.each { issue ->
            echo issue.key.toString()
            echo issue.fields.status.toString()
            echo issue.toString()
            def transitions = jiraGetIssueTransitions idOrKey: issue.key, site: 'Gather'
            echo transitions.data.toString()
            def issueUpdate = [ transition: [ name: 'Deployed' ]]
            def response = jiraTransitionIssue (idOrKey: issue.key, input: issueUpdate, site: 'Gather')
            echo response.data.toString()
          }


          def testIssue = [fields: [ components: [[name: 'Venue App']]]]

          def oldresponse = jiraEditIssue(idOrKey: 'APP-2378', issue: testIssue, site: 'Gather')

          echo oldresponse.data.toString()
        }

      }
    }
  }
}
