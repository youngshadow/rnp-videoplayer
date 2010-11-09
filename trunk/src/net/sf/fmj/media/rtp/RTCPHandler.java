/*
 * @(#)RTCPHandler.java
 * Created: 27-Oct-2005
 * Version: 1-1-alpha3
 * Copyright (c) 2005-2006, University of Manchester All rights reserved. 
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer. Redistributions in binary
 * form must reproduce the above copyright notice, this list of conditions and
 * the following disclaimer in the documentation and/or other materials 
 * provided with the distribution. Neither the name of the University of 
 * Manchester nor the names of its contributors may be used to endorse or 
 * promote products derived from this software without specific prior written
 * permission. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package net.sf.fmj.media.rtp;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.media.protocol.PushSourceStream;
import javax.media.protocol.SourceTransferHandler;

import net.sf.fmj.utility.LoggerSingleton;

/**
 * Handles incoming RTCP Packets
 * @author Andrew G D Rowley
 * @version 1-1-alpha3
 */
public class RTCPHandler implements SourceTransferHandler {

	private static final Logger logger = LoggerSingleton.logger;

    // The manager where packets are coming from
    private RTPSessionMgr manager = null;
    
    /**
     * Creates a new RTPHandler
     * @param manager
     */
    public RTCPHandler(RTPSessionMgr manager) {
        this.manager = manager;
    }
    
    public void transferData(PushSourceStream stream) {
        try {
            byte[] buffer = new byte[stream.getMinimumTransferSize()];
            int length = stream.read(buffer, 0, buffer.length);
            manager.handleRTCPPacket(buffer, 0, length);
        } catch (Exception e) {
        	logger.log(Level.WARNING, "" + e, e);
        }
    }

}