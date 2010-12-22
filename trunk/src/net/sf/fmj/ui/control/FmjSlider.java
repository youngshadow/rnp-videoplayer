/*
 * Copyright (C) 2009, Edmundo Albuquerque de Souza e Silva.
 *
 * This file may be distributed under the terms of the Q Public License
 * as defined by Trolltech AS of Norway and appearing in the file
 * LICENSE.QPL included in the packaging of this file.
 *
 * THIS FILE IS PROVIDED AS IS WITH NO WARRANTY OF ANY KIND, INCLUDING
 * THE WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE.  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL,
 * INDIRECT OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING
 * FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION
 * WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 */

package net.sf.fmj.ui.control;

import java.awt.Color;
import java.util.HashSet;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.fmj.ui.plaf.FmjSliderUI;

/**
 * A slider with a nice look-and-feel.
 * 
 * @author Warren Bloomer
 *
 */
public class FmjSlider extends JSlider {

    private static final long serialVersionUID = 014L;
    /** whether to track the slider */
//	private boolean trackSlider = false;
    private boolean paintFocus = false;
    private int value = -1;
    private HashSet linearListeners;

    /**
     * Constructor
     *
     */
    public FmjSlider() {
        initialize();
    }

    public boolean getPaintFocus() {
        return paintFocus;
    }

    public void setPaintFocus(boolean paintFocus) {
        this.paintFocus = paintFocus;
    }

    private void initialize() {
        this.setUI(FmjSliderUI.createUI(this));
        this.setName("Slider");
        this.setBackground(Color.WHITE);
        this.setOpaque(false);

        this.setPaintTrack(true);
        this.setPaintTicks(false);
        this.setSnapToTicks(true);

        this.addChangeListener(new SliderListener());
    }

    /**
     * Returns the Linear listeners.
     *
     * @return the Linear listeners
     */
    private HashSet getLinearListeners() {
        if (linearListeners == null) {
            linearListeners = new HashSet();
        }
        return linearListeners;
    }

    /**
     * A listener for state change events on the slider
     */
    private class SliderListener implements ChangeListener {

        public void stateChanged(ChangeEvent event) {

            sendValue();
        }

        private void sendValue() {
            int newValue = getValue();

            // only send if value has changed
            if (newValue != value) {
                value = newValue;
                
                // TODO send to listeners
            }
        }
    }
}
