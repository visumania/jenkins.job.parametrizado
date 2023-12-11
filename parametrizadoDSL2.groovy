job('ejemplo2-job-DSL') {
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm {
    git('https://github.com/visumania/jenkins.job.parametrizado.git', 'main') { node -> 
      node / gitConfigName('visumania')
      node / gitConfigEmail('adrian.moreno.mon@gmail.com')
    }
  }
  parameters {
    stringParam('nombre', defaultValue = 'Adrián', description = 'Parámetro de cadena para el Job Booleano')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Saturno', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
    cron('H/7 * * * *')
	githubPush()
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers {
    mailer('amoreno@stemdo.io', true, true)
  }
}