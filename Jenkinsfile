pipeline{
  agent any
  tools {
    jdk 'adoptopenjdk-hotspot-jdk8-latest'
  }
  environment {
    MAVEN_HOME = '$WORKSPACE/.m2/'
    MAVEN_USER_HOME = '$MAVEN_HOME'
  }
  stages{
    stage("Maven Build"){
        steps {
          withMaven {
            sh './mvnw clean verify -B -Pci,generate-p2 -Dcbi.jarsigner.skip=false'
          }
        }
    }
    stage('Deploy to downloads.eclipse.org') {
      when {
        branch 'master'
      }
      steps {
        sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
          sh '''
            targetDir=/home/data/httpd/download.eclipse.org/lemminx/snapshots
            ssh genie.lemminx@projects-storage.eclipse.org rm -rf $targetDir
            ssh genie.lemminx@projects-storage.eclipse.org mkdir -p $targetDir
            scp -r org.eclipse.lemminx/target/org.eclipse.lemminx-* genie.lemminx@projects-storage.eclipse.org:$targetDir
            ssh genie.lemminx@projects-storage.eclipse.org unzip $targetDir/org.eclipse.lemminx-p2repo.zip -d $targetDir/repository
            '''
        }
      }
    }
    stage ('Deploy Maven artifacts') {
      when {
          branch 'master'
      }
      steps {
        withMaven {
          sh './mvnw deploy -B -Pci,generate-p2 -DskipTests' //don't clean to keep previous test results
        }
      }
    }
  }
}