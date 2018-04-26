/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp02;

import java.util.Comparator;

/**
 *
 * @author Bambi
 */
public class Compare implements Comparator<Kontobewegung> {

	@Override
	public int compare(Kontobewegung o1, Kontobewegung o2) {
		return Double.compare(o1.getTimeInMillis(), o2.getTimeInMillis());
	}
	
}
