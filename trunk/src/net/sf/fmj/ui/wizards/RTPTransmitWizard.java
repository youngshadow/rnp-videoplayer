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

package net.sf.fmj.ui.wizards;

import java.awt.Frame;

import javax.media.protocol.ContentDescriptor;

import net.sf.fmj.ui.wizard.Wizard;

/**
 * 
 * 
 *
 */
public class RTPTransmitWizard extends Wizard
{
	private final RTPTransmitWizardResult result = new RTPTransmitWizardResult();
	private final RTPTransmitWizardConfig config;

	
	public RTPTransmitWizardResult getResult()
	{
		return result;
	}
	
	public RTPTransmitWizardConfig getConfig()
	{	return config;
	}


	public RTPTransmitWizard(Frame owner, RTPTransmitWizardConfig config)
	{
		super(owner);
		
		if (config != null)
			this.config = config;
		else
			this.config = new RTPTransmitWizardConfig();


		
		getDialog().setTitle("RTP Transmit Wizard");
        
        final ChooseSourcePanelDescriptor descriptor1 = new ChooseSourcePanelDescriptor(config, result);
        registerWizardPanel(ChooseSourcePanelDescriptor.IDENTIFIER, descriptor1);

        final ContentAndTrackFormatPanelDescriptor descriptor2 = new ContentAndTrackFormatPanelDescriptor(RTPDestPanelDescriptor.IDENTIFIER, new RTPContentDescriptorFilter(), config, result);
        registerWizardPanel(ContentAndTrackFormatPanelDescriptor.IDENTIFIER, descriptor2);
        
        final RTPDestPanelDescriptor descriptor3 = new RTPDestPanelDescriptor(config, result);
        registerWizardPanel(RTPDestPanelDescriptor.IDENTIFIER, descriptor3);
        
        setCurrentPanel(ChooseSourcePanelDescriptor.IDENTIFIER);
        
	}
	
	public boolean run()
	{
        final int ret = showModalDialog();
        //System.out.println("Dialog return code is (0=Finish,1=Cancel,2=Error): " + ret);
        return ret == 0;
	}
	
	private static class RTPContentDescriptorFilter implements ContentDescriptorFilter
	{

		public boolean isCompatible(ContentDescriptor d) 
		{
	    	//if (d.equals(new ContentDescriptor(ContentDescriptor.RAW)))	// TODO: we want RAW_RTP only
	    	//	return true;
	    	if (d.equals(new ContentDescriptor(ContentDescriptor.RAW_RTP)))
	    		return true;
	    	
	    	return false;
	    }
		
	}
}
