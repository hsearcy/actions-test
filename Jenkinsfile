library 'monorepo-utils'
def changedPackages = [];
def buildNames = ["sls-test-A", "sls-test-B"];

pipeline {
  agent any
  stages {
    stage('Get Changed Paths') {
      steps {
        script {
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
                def matchingBuild = buildNames.find { pkg -> file.path.matches("packages/${pkg}/(.*)") }
                if (matchingBuild && !changedPackages.contains(matchingBuild)) changedPackages << "${matchingBuild}/master"
              }
            }
          }
          echo "All changes since last build:\n${allChanges}."
          echo "Changed: ${changedPackages}"
          ProcessChangelog();
        }
      }
    }

    stage('Launch builds with changes') {
      steps {
        script {
          changedPackages.each { buildName ->
            build job: buildName, wait: false
          }
        }
      }
    }
  }
}
