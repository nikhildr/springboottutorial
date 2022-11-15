pipeline {

    agent any
    stages {

        stage('Build'){
           git url: 'https://github.com/nikhildr/springboottutorial.git',
               credentialsId: 'ba192036-da00-4442-a8b5-924785bc1ad3',
               branch: 'develop'
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