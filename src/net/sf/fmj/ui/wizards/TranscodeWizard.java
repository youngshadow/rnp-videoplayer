/*
 * TranscodeWizard.java
 *
 * Created on June 20, 2007, 12:46 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

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
import javax.media.protocol.FileTypeDescriptor;

import net.sf.fmj.ui.wizard.Wizard;

/**
 *
 * 
 */
public class TranscodeWizard extends Wizard
{
	private final TranscodeWizardResult result = new TranscodeWizardResult();
	private final TranscodeWizardConfig config;
	
	public TranscodeWizardResult getResult()
	{
		return result;
	}
	
	public TranscodeWizardConfig getConfig()
	{	return config;
	}

	public TranscodeWizard(Frame owner, TranscodeWizardConfig config)
	{
		super(owner);
		
		if (config != null)
			this.config = config;
		else
			this.config = new TranscodeWizardConfig();

		
		getDialog().setTitle("Transcode Wizard");
        
        ChooseSourcePanelDescriptor descriptor1 = new ChooseSourcePanelDescriptor(config, result);
        registerWizardPanel(ChooseSourcePanelDescriptor.IDENTIFIER, descriptor1);

        ContentAndTrackFormatPanelDescriptor descriptor2 = new ContentAndTrackFormatPanelDescriptor(FileDestPanelDescriptor.IDENTIFIER, new TranscodeContentDescriptorFilter(), config, result);
        registerWizardPanel(ContentAndTrackFormatPanelDescriptor.IDENTIFIER, descriptor2);
        
//        AudioFormat f = new AudioFormat(AudioFormat.ULAW_RTP, 8000.0, 8, 1, AudioFormat.LITTLE_ENDIAN, AudioFormat.SIGNED);
//        ((ContentAndTrackFormatPanel) descriptor2.getPanelComponent()).addTrack(f);

        FileDestPanelDescriptor descriptor3 = new FileDestPanelDescriptor(config, result);
        registerWizardPanel(FileDestPanelDescriptor.IDENTIFIER, descriptor3);
        
        setCurrentPanel(ChooseSourcePanelDescriptor.IDENTIFIER);
        
	}
	
	public boolean run()
	{
        int ret = showModalDialog();
        
        //System.out.println("Dialog return code is (0=Finish,1=Cancel,2=Error): " + ret);
//        System.out.println("Second panel selection is: " + 
//            (((ContentAndTrackFormatPanel)descriptor2.getPanelComponent()).getRadioButtonSelected()));
//        
        return ret == 0;
	}
	
	private static class TranscodeContentDescriptorFilter implements ContentDescriptorFilter
	{

		public boolean isCompatible(ContentDescriptor d) 
		{
	    	if (d instanceof FileTypeDescriptor)
	    		return true;
	    	
	    	return false;
	    }
		
	}
}
