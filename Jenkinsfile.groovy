pipeline{
    agent any
    stages{
        stage("Run Command"){
            steps{
                sh '''
                set +xe 
                echo Hello
                ech Error
                yum install httpd -y
                ping -c 4 google.com
                '''
            }
        }
       tage("Download Terraform"){
            steps{
                ws("tmp/"){
                    script {
                        def exists = fileExists 'terraform_0.12.7_linux_amd64.zip'
                        if (exists) {
                            sh "unzip -o terraform_0.12.7_linux_amd64.zip"
                            sh "mv terraform /bin"
                            sh "terraform version"
                        } else {
                            sh "wget https://releases.hashicorp.com/terraform/0.12.7/terraform_0.12.7_linux_amd64.zip"
                            sh "unzip -o terraform_0.12.7_linux_amd64.zip"
                            sh "mv terraform /bin"
                            sh "terraform version"
                        }
                    }
                }
            }
        }
    }
}