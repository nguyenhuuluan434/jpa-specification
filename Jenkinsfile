pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }

  }
  stages {
    stage('Build') {
      steps {
        sh 'mvn -B -DskipTests clean package'
      }
    }

    stage('Test') {
      steps {
        sh 'echo "mvn test"'
      }
    }

    stage('Deliver') {
      steps {
        sh '''chmod +x ./scripts/deliver.sh
bash ./scripts/deliver.sh'''
      }
    }

  }
}