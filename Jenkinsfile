def date = new Date()

pipeline {
	agent any
	stages {
		stage ('HolaMundo') {
			steps {
				echo "Hola Mundo! EL día de hoy es elDia " + date.format('dd-MM-yyyy') + ". Este curso me hizo programar mas de lo que me hubiese gustado"
			}
		}
	}
}
