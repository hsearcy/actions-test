def changedPackages = [];
def packageNames = [];

pipeline {
  agent any
  stages {
    stage('Get Changed Paths') {
      steps {
        script {
          Jenkins.instance.getAllItems(org.jenkinsci.plugins.workflow.job.WorkflowJob).each {
            def pkg = it.fullName.split("/")[0];
            packageNames << "packages/${pkg}/"
          }
          def changeLogSets = currentBuild.changeSets
          def allChanges = ""
          for (int i = 0; i < changeLogSets.size(); i++) {
            def entries = changeLogSets[i].items
            for (int j = 0; j < entries.length; j++) {
              def entry = entries[j]
              def commitHeader = "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}\n"
              allChanges += commitHeader
              def files = new ArrayList(entry.affectedFiles)
              for (int k = 0; k < files.size(); k++) {
                def file = files[k]
                allChanges += "  ${file.editType.name} ${file.path}\n"
                if (file.path.contains(env.PACKAGE_PATH)) {

                }
              }
            }
          }
          echo "All changes since last build:\n${allChanges}."
        }
      }
    }
  }
}
