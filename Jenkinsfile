pipeline {
  agent any

  stages {
    stage('Git') {
      steps {
        git branch: 'develop', credentialsId: 'ba192036-da00-4442-a8b5-924785bc1ad3', url: 'https://github.com/nikhildr/springboottutorial.git'
      }
    }
    stage('Test') {
      steps {
        sh './mvnw test'
      }

      post {
        always {
          junit '**/target/surefire-reports/TEST-*.xml'
        }
      }
    }
    stage('Build') {
      steps {
        sh './mvnw install'
      }
    }
    stage('Deploy') {
      steps {
        sh './mvn spring-boot:run'
      }
    }
  }
}