def ProcessChangelog() {
  echo "Package path is: ${env.PACKAGE_PATH}"
  def continuePipeline = true
  def changeLogSets = currentBuild.changeSets
  def allChanges = ""
  def relevantChanges = ""
  for (int i = 0; i < changeLogSets.size(); i++) {
    def entries = changeLogSets[i].items
    for (int j = 0; j < entries.length; j++) {
      def relevantIncluded = false
      def entry = entries[j]
      def commitHeader = "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}\n"
      allChanges += commitHeader
      def files = new ArrayList(entry.affectedFiles)
      for (int k = 0; k < files.size(); k++) {
        def file = files[k]
        allChanges += "  ${file.editType.name} ${file.path}\n"
        if (file.path.contains(env.PACKAGE_PATH)) {
          if (!relevantIncluded) {
            relevantIncluded = true
            relevantChanges += commitHeader
          }
          relevantChanges += "  ${file.editType.name} ${file.path}\n"
        }
      }
    }
  }
  echo "All changes since last build:\n${allChanges}\nChanges relevant to ${env.PACKAGE_PATH}:\n${relevantChanges}"
  if (relevantChanges.length() > 0) {
    echo "Changes detected, continuing pipeline."
  } else {
    continuePipeline = false
    echo "No changes detected, waiting for user input to continue."
  }
  return continuePipeline;
}

def UpdateJiraIssues(service) {
  def issues = jiraJqlSearch(jql: "project = Jenkins AND status = Done AND labels = ${service}", auditLog: true, failOnError: true, site: 'Gather')
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

def GetAllChanges() {
  def changes = "Changes:\n"
  build = currentBuild
  while(build != null && build.result != 'SUCCESS') {
    def theseChanges = "In ${build.id}:\n"
    for (changeLog in build.changeSets) {
        for(entry in changeLog.items) {
            def commitHeader = "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}\n"
            def first = true;
            for(file in entry.affectedFiles) {
              if (file.path.contains(env.PACKAGE_PATH)) {
                if (first) theseChanges += commitHeader;
                theseChanges += " ${file.path}\n"
              }
            }
        }
    }
    if (theseChanges.length() > 10) changes += theseChanges;
    build = build.previousBuild
  }
  echo "All changes since the last deployment:\n${changes}"
}
return this
