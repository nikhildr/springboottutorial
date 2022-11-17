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
          jacoco()
          junit '**/target/surefire-reports/TEST-*.xml'
        }
      }
    }
    stage('Build') {
      steps {
        sh './mvnw install -Dskiptests=true'
      }

    }
    stage('SonarQube analysis') {
      //    def scannerHome = tool 'SonarScanner 4.0';
      steps {
        withSonarQubeEnv('sonarqube9.7') {
          sh "./mvnw sonar:sonar"
        }
      }
    }
    stage('Docker Build') {
      steps {
        script {
          sh 'docker build -t nikhildr/springtest .'
        }
      }
    }
    stage('Docker Deploy') {
      steps {
        script {
          sh "docker stop nikhildr/springtest || true && docker rm nikhildr/springtest || true"
          sh "docker run --name nikhildr/springtest -d -p 8081:8081 nikhildr/springtest"
        }
      }
    }
  }
}
}