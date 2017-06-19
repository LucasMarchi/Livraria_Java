package br.com.caelum.timer;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class Agendador {
	
	@Schedule(hour="*",minute="*",second="*/10", persistent=false) // � obriga��o do ejb recuperar tarefas agendadas se o servidor cair, persistence false evita esse comportamento automatico
	void agendador(){
		System.out.println("Verificando servi�o externo periodicamente");
	}

}
