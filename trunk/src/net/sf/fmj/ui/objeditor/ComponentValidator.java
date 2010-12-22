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

package net.sf.fmj.ui.objeditor;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * 
 *
 */
public class ComponentValidator 
{

	public void validateNotEmpty(JTextField f, JLabel label) throws ComponentValidationException
	{
		final String s = f.getText();
		if (s == null || s.equals(""))
			throw new ComponentValidationException(f, buildMessage(label.getText(), "may not be empty"));

	}
	public void validateNotEmpty(JComboBox c, JLabel label) throws ComponentValidationException
	{
		final Object o = c.getSelectedItem();
		final String s = o == null ? null : o.toString(); 
		if (s == null || s.equals(""))
			throw new ComponentValidationException(c, buildMessage(label.getText(), "may not be empty"));

	}

	public void validateInteger(JComboBox c, JLabel label) throws ComponentValidationException
	{
		final Object o = c.getSelectedItem();
		final String s = o == null ? null : o.toString(); 
		try
		{
			Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{	
			throw new ComponentValidationException(c, buildMessage(label.getText(), "not a valid number"));
		}

	}
	
	public void validateInteger(JTextField f, JLabel label) throws ComponentValidationException
	{
		final String s = f.getText();
		try
		{
			Integer.parseInt(s);
		}
		catch (NumberFormatException e)
		{	
			throw new ComponentValidationException(f, buildMessage(label.getText(), "not a valid number"));
		}

	}

	
	private static String buildMessage(String label, String msg)
	{
		if (label == null || label.equals(""))
			return msg;
		if (label.endsWith(":"))
			return label + " " + msg;
		else
			return label + ": " + msg;
		
	}
}
