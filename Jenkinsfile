pipeline {
  agent none
  stages {
    stage('Deploy') {
      agent any
      steps {
        echo 'Deploying'
        sh '/pda/docker-compose/conf/pda-api/pda-api.sh'
      }
    }
  }
}