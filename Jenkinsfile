pipeline {

    agent any
    stages {

        stage('Checkout Codebase') {
            steps {
                cleanWs()
                checkout scm: [$class: 'GitSCM',branches:[[name:'*/main]],userRemoteConfigs:[[credentialsId: 'github-ssh-key',urls:'git@github.com:nikhildr/springboottutorial.git']]]
            }
        }

        stage('Build'){
                    steps{
                        sh './mvnw clean compile'
                    }
                }
         stage('Test'){
                            steps{
                                sh './mvnw test'
                            }

                            post{
                                always{
                                    junit '**/target/surefire-reports/TEST-*.xml'
                                }
                            }
                        }
    }
}