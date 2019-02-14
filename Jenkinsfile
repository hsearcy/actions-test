pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        script {
          def issues = jiraJqlSearch(jql: 'project = Jenkins AND status = Done AND labels = service1', auditLog: true, failOnError: true, site: 'Gather')
          def firstIssue = true
          def deployedStatusID = -1
          issues.data.issues.each { issue ->
            echo issue.key.toString()
            echo issue.fields.status.toString()
            echo issue.toString()
            if (firstIssue) {
              firstIssue = false
              def transitions = jiraGetIssueTransitions idOrKey: issue.key, site: 'Gather'
              deployedStatusID = transitions.data.transitions.find { it.name == "Deployed" }.id
              echo deployedStatusID.toString()
            }
            if (deployedStatusID > -1 ) {
              def issueUpdate = [ transition: [ id: deployedStatusID ]]
              def response = jiraTransitionIssue (idOrKey: issue.key, input: issueUpdate, site: 'Gather')
              echo response.data.toString()
            }
          }


          def testIssue = [fields: [ components: [[name: 'Venue App']]]]

          def oldresponse = jiraEditIssue(idOrKey: 'APP-2378', issue: testIssue, site: 'Gather')

          echo oldresponse.data.toString()
        }

      }
    }
  }
}
