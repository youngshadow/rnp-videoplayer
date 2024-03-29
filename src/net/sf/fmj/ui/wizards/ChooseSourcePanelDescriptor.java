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

import java.util.logging.Logger;

import net.sf.fmj.ui.wizard.WizardPanelDescriptor;
import net.sf.fmj.utility.LoggerSingleton;


/**
 * 
 * 
 *
 */
public class ChooseSourcePanelDescriptor extends WizardPanelDescriptor {
    
	private static final Logger logger = LoggerSingleton.logger;

    public static final String IDENTIFIER = ChooseSourcePanelDescriptor.class.getName();
    
    private final ProcessorWizardConfig config;
    private final ProcessorWizardResult result;
    
    public ChooseSourcePanelDescriptor(ProcessorWizardConfig config, ProcessorWizardResult result) {
        super(IDENTIFIER, new ChooseSourcePanel());
        this.config = config;
        this.result = result;
    }
    
    public Object getNextPanelDescriptor() {
        return ContentAndTrackFormatPanelDescriptor.IDENTIFIER;
    }
    
    public Object getBackPanelDescriptor() {
        return null;
    }
    
    public ChooseSourcePanel getChooseSourcePanel()
    {	return (ChooseSourcePanel) getPanelComponent();
    }

   
	public boolean aboutToDisplayPanel(Object prevId)
	{
		if (prevId == getBackPanelDescriptor())
		{	
			if (config.url != null)
				getChooseSourcePanel().getTextFieldURL().setText(config.url);		
			// TODO: hard-coded, get from prefs
			return true;
		}
		return super.aboutToDisplayPanel(prevId);
	}

	public boolean aboutToHidePanel(Object idOfNext)
	{
		if (idOfNext == getNextPanelDescriptor())
		{	// forward transition
			
			config.url = getChooseSourcePanel().getTextFieldURL().getText();
			if (config.url == null || config.url.equals(""))
			{	showError("Source URL may not be blank");
				return false;
			}
			
			try
			{
				result.step1_createProcessorAndSetUrl(config);
			} catch (WizardStepException e1)
			{
				showError(e1);
				return false;
			}
			
			return true;
		}
		else
		{	return super.aboutToHidePanel(idOfNext);
		}
	}  
    
}
