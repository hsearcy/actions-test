pipeline {
  agent any
  stages {
    stage('JIRA Search') {
      steps {
        script {
          def issues = jiraJqlSearch(jql: 'project = Jenkins AND status = Done AND labels = service1', auditLog: true, failOnError: true, site: 'Gather')
          def firstIssue = true
          def deployedStatusID = "-1"
          issues.data.issues.each { issue ->
            echo issue.key.toString()
            if (firstIssue) {
              firstIssue = false
              def transitions = jiraGetIssueTransitions idOrKey: issue.key, site: 'Gather'
              deployedStatusID = transitions.data.transitions.find { it.name == "Deployed" }.id.toString()
            }
            if (deployedStatusID != "-1") {
              def issueUpdate = [ transition: [ id: deployedStatusID ]]
              def response = jiraTransitionIssue (idOrKey: issue.key, input: issueUpdate, site: 'Gather')
            }
          }
        }

      }
    }
  }
}
