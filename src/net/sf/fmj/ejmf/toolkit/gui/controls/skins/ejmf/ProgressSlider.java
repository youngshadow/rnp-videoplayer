package net.sf.fmj.ejmf.toolkit.gui.controls.skins.ejmf;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

import net.sf.fmj.ejmf.toolkit.gui.controls.ProgressBar;

/** 
  * ProgressSlider for StandardControlPanel.
  * <p>
  * A simple slider, mostly stolen from JSlider code.
  * This is a bare bones implementation. JSlider can't be used 
  * as is. It will make media choppy dealing with all
  * the timer update events generated by control panel.
  */

public class ProgressSlider extends Canvas 
		implements ProgressBar {

    private static final int	DEFAULT_THUMB_WIDTH = 6;
    private static final int	DEFAULT_THUMB_HEIGHT = 12;
    private int  		sliderLen;
    private int  		value;
    private int max =  		100;
    private int min = 		0;
    private Rectangle 		thumbRect;
    private boolean 		dragOn = false;
    private boolean		isOperational = true;
    private EventListenerList	listenerList = null;

    private Object[]	listeners;

	/**
	* Create a ProgressSlider.
	*/
    public ProgressSlider() {

	sliderLen = max - min;
	value = sliderLen / 2;

	addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
	        if (thumbRect.contains(p)) {
	            startDrag(p);
		}
	    }
       
            public void mouseReleased(MouseEvent e) {
		endDrag(e.getPoint());
	    }
	});
	
	addMouseMotionListener(new MouseMotionAdapter() {
    	    public void mouseDragged(MouseEvent e) {
		if (!dragOn)
		    return;

		Point p = e.getPoint();

		setValue(p.x);
		listeners = listenerList.getListenerList();	
		for (int i = listeners.length-2; i >= 0; i -= 2)
		    if (listeners[i] == ChangeListener.class)		
			((ChangeListener)listeners[i+1]).stateChanged(
			    new ChangeEvent(ProgressSlider.this));
	    }
	});
    }
   
    void startDrag(Point p) {
	dragOn = true;
    }

    void endDrag(Point p) {
	dragOn =  false;
    }

	/**
	* Paint the progress slider.	
	*/
    public void paint(Graphics g) {

	thumbRect = computeThumbRectangle();

	int thumbWidth = thumbRect.width;
        int thumbHeight = thumbRect.height;

        int offset = thumbWidth / 2;
	int drawAt = value + offset;


	Color oldColor = g.getColor();

	// Draw bar
 	g.drawLine(0, 5, sliderLen+thumbWidth, 5);
 	g.drawLine(0, 6, sliderLen+thumbWidth, 6);

	// Draw bar etching
	// First draw bottom hightlight of bar, then right side
 	g.setColor(getBackground().brighter());
 	g.drawLine(0, 7, sliderLen+thumbWidth, 7); 	
	g.drawLine(sliderLen+thumbWidth, 5, sliderLen+thumbWidth, 7);

	// Track thumb rectangle so test for contain(Point)
	// works in mousePressed method
        thumbRect.setLocation(drawAt-offset, 0); 

	g.translate(drawAt-offset, 0);

        paintThumb(g);

	// Reset color and coordinate system
	g.setColor(oldColor);
	g.translate(-(drawAt-offset), 0);
    }

	/**	
	* Paint the slider thumb.
	* @param g graphics in which thumb is painted.
	*/
    public void paintThumb(Graphics g) {
	thumbRect = computeThumbRectangle();

	int thumbHeight = thumbRect.height;
	int thumbWidth = thumbRect.width;

	// Left highlight
 	g.setColor(getBackground().brighter());
	g.drawLine(0, 0, 0, thumbHeight);

	//  Draw thumb proper
 	g.setColor(getBackground());
	for (int i = 1; i < thumbWidth; i++)
	    g.drawLine(i, 0, i, thumbHeight);

	// right/bottom shadow
 	g.setColor(UIManager.getColor("controlDkShadow"));
	g.drawLine(thumbWidth, 0, thumbWidth, thumbHeight);
	g.drawLine(1, thumbHeight-1, thumbWidth, thumbHeight-1);

	// Top highlight of thumb
 	g.setColor(getBackground().brighter());
	g.drawLine(0, 0, thumbWidth-1, 0);
    }

	/** Return Rectangle which describes location and size of
	* thumb.
	* @return A Rectangle that contains thumb.	
	*/
    public Rectangle getThumbRect() {
	return thumbRect;   
    }

	/** Recompute thumb size in response to a change	
	* in value of the slider.	
	* @return A Rectangle.
	*/
    public Rectangle computeThumbRectangle() {
	return new Rectangle(new Point(value-DEFAULT_THUMB_WIDTH/2, 0),
				new Dimension(DEFAULT_THUMB_WIDTH, 
					      DEFAULT_THUMB_HEIGHT));
    }

    public Dimension getPreferredSize() {
	return new Dimension(sliderLen+DEFAULT_THUMB_WIDTH+1, 
				DEFAULT_THUMB_HEIGHT);
    }

    ///////////////// Implement ProgressBar Interface /////////////

	/** Get current value of sprogress bar.
	* @return An integer indicatiing position of	
	* thumb within range of legal slider values.	
	*/
    public int getValue() {
	return value;
    }

	/** Get current value of sprogress bar.
	* @param value	An integer value within range
	* of legal slider values.	
	*/
    public void setValue(int value) {
	if (value < min)	
	    value = min;
	if (value > max)
	    value = max;
	this.value = value;
	repaint();
    }

	/** Set the largest value progress slider can take on.
	* When slider reaches this value operation which progress
	* bar is measuring is complete.
	* @param value The maximum value slider can take on.
	*/
    public void setMaximum(int value) {
	max = value;
    }

	/** Get the largest value progress slider can take on.
	* @return The maximum value slider can take on.
	*/
    public int getMaximum() {
	return max;
    }

	/** Set the smallest value progress slider can take on.
	* When slider value reaches this value, the operation
	* measured by progress bar has not been started.
	* @param value The minimum value slider can take on.
	*/
    public void setMinimum(int value) {
	min = value;
    }

	/** Get the smallest value progress slider can take on.
	* @return The minimum value slider can take on.
	*/
    public int getMinimum() {
	return min;
    }

    ////////////// Implement ControllerControl Interface //////////////////


	/**
	* Add a ChangeListener
	* @param l a ChangeListener
	*/
    public void addChangeListener(ChangeListener l) {
	if (listenerList == null)
	    listenerList = new EventListenerList();
	listenerList.add(ChangeListener.class, l);
    }

	/**
	* Remove a ChangeListener
	* @param l a ChangeListener
	*/
    public void removeChangeListener(ChangeListener l) {
	if (listenerList == null)
	    return;
	listenerList.remove(ChangeListener.class, l);
    }
}
