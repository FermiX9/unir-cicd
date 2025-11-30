pipeline {
    agent {
        label 'pc'
    }
    stages {
        stage('Source') {
            steps {
                git 'https://github.com/FermiX9/unir-test.git'
                }
            }        
        stage('Build') {
            steps {
                echo 'Building stage!'
                sh 'make build'
                }
            }
        stage('Unit tests') {
            steps {
                sh 'make test-unit'
                archiveArtifacts artifacts: 'results/*.xml'                
                }
            }
        stage('API tests') {
            steps {
                sh 'make test-api'
                archiveArtifacts artifacts: 'results/*.xml'
            }
        }
        stage('E2E tests') {
            steps {
                sh 'make test-e2e'
                archiveArtifacts artifacts: 'results/*.xml'
            }
        }
    }
    post {
        always {
            junit 'results/*_result.xml'
        }
        success {
            echo "Pipeline completed successfully! Pipeline: ${env.JOB_NAME} Build Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_URL}"
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
            mail body: "<br>Pipeline: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", 
            charset: 'UTF-8', 
            from: 'jenkins@domain.com',
            mimeType: 'text/html', 
            subject: "ERROR CI: Pipeline name -> ${env.JOB_NAME}", 
            to: "fermin.robledillo122@comunidadunir.net";
        }
    }
}
