package packfunctors;

import packppal.CasillaEstandar;
import packppal.Jugador;
import net.sf.jga.fn.UnaryFunctor;

public class FunctorCasilla extends UnaryFunctor<CasillaEstandar, Boolean> {
	
	private Jugador jugador;
	
	public FunctorCasilla(Jugador pJugador){
		jugador=pJugador;
	}

	@Override
	public Boolean fn(CasillaEstandar pCasilla) {
		Boolean rdo= pCasilla.esDeJugador(jugador);
		return rdo;
	}

}
