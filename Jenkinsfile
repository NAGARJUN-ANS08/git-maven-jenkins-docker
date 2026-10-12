
pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = 'git-maven-jenkins-docker'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/NAGARJUN-ANS08/git-maven-jenkins-docker.git'
            }
        }

        stage('Check Docker') {
            steps {
                withEnv(["PATH+DOCKER=/usr/local/bin:/opt/homebrew/bin:/Applications/Docker.app/Contents/Resources/bin"]) {
                    sh '''
                        echo "Checking Docker CLI..."
                        echo "PATH=$PATH"
                        command -v docker
                        docker --version
                        docker info
                    '''
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar',
                    fingerprint: true
            }
        }

        stage('Docker Build') {
            steps {
                withEnv(["PATH+DOCKER=/usr/local/bin:/opt/homebrew/bin:/Applications/Docker.app/Contents/Resources/bin"]) {
                    sh 'docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} .'
                }
            }
        }

        stage('Docker Run/Test') {
            steps {
                withEnv(["PATH+DOCKER=/usr/local/bin:/opt/homebrew/bin:/Applications/Docker.app/Contents/Resources/bin"]) {
                    sh 'docker run --rm ${IMAGE_NAME}:${BUILD_NUMBER}'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check the stage logs.'
        }

        always {
            echo 'Pipeline execution finished.'
        }
    }
}

