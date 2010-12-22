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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.media.Duration;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sf.fmj.ui.images.Images;

/**
 * 
 * @author Warren Bloomer
 *
 */
public class TransportControlPanel extends JPanel implements TransportControlListener {

    // TODO: the buttons need to enable/disable based on the controller state
    private TransportControl player;  //  @jve:decl-index=0:
    private JButton playButton = null;
    private JButton stopButton = null;
    private JButton backButton = null;
    private JButton forwardButton = null;
    private JButton nextButton = null;
    private JButton previousButton = null;
    private JPanel positionPanel = null;
    private JSlider positionSlider = null;
    private JPanel buttonPanel = null;
    private JLabel positionLabel = null;
    private JLabel lengthLabel = null;
    private JPanel infoPanel = null;
    private JTextPane infoTextPane = null;
    private JPanel audioPanel = null;
    private JSlider volumeSlider = null;
    private JToggleButton muteButton = null;
    private boolean isplay = true ;


    /**
     * This method initializes
     *
     */
    public TransportControlPanel() {
        super();
        initialize();
    }

    public void setPlayer(TransportControl player) {
        this.player = player;
        player.setTransportControlListener(this);
    }

    /**
     * This method initializes this
     *
     */
    private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(553, 58));

        this.add(getPositionPanel(), BorderLayout.NORTH); //barra de progresso
        this.add(getButtonPanel(), BorderLayout.WEST);//botoes      
        // this.add(getInfoPanel(), BorderLayout.CENTER);
        //  this.add(getInfoTextPane(), BorderLayout.CENTER);
        this.add(getAudioPanel(), BorderLayout.EAST);

       // setAudioControlEnabled(true);
    }

    public void start() {
        if (player != null) {
            player.start();
            isplay = true;
            playButton.setIcon(Images.get(Images.MEDIA_PAUSE));
        }
    }

    public void stop() {
        if (player != null) {
            player.stop();
            isplay = false;
            playButton.setIcon(Images.get(Images.MEDIA_PLAY));
            player.setPosition(0);
        }
    }
    public void pause() {
        if (player != null) {
            player.stop();
            isplay = false;
            playButton.setIcon(Images.get(Images.MEDIA_PLAY));
        }
    }

    private void setRate(float rate) {
        if (player != null) {
            player.setRate(rate);
        }
    }

    private void setGain(float gain) {
        if (player != null) {
            player.setGain(gain);

        }
    }

    private void setMute(boolean mute) {
        if (player != null) {
            player.setMute(mute);
        }
    }

    /**
     * This method initializes playButton
     *
     * @return javax.swing.JButton
     */
    private JButton getPlayButton() {
        if (playButton == null) {
            playButton = new JButton();
            //playButton.setText("play");
            playButton.setOpaque(false);
            playButton.setIcon(Images.get(Images.MEDIA_PAUSE));
            playButton.setMnemonic('p');
            playButton.setToolTipText("Alt p");
            playButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    setRate(1.0f);
                    if(isplay){
                    pause();
                    }else{
                   start();
                    }
                   
                }
            });
            playButton.setEnabled(false);
        }
        return playButton;
    }

    /**
     * This method initializes stopButton
     *
     * @return javax.swing.JButton
     */
    private JButton getStopButton() {
        if (stopButton == null) {
            stopButton = new JButton();
            //stopButton.setText("pause");
            stopButton.setOpaque(false);
            stopButton.setIcon(Images.get(Images.MEDIA_PAUSE));
            stopButton.addActionListener(new java.awt.event.ActionListener() {

                public void actionPerformed(java.awt.event.ActionEvent e) {
                    stop();
                }
            });
            stopButton.setEnabled(false);

        }
        return stopButton;
    }

    /**
     * This method initializes backButton
     *
     * @return javax.swing.JButton
     */
//    private JButton getBackButton() {
//        if (backButton == null) {
//            backButton = new JButton();
//            //backButton.setText("back");
//            backButton.setOpaque(false);
//            backButton.setIcon(Images.get(Images.MEDIA_REWIND));
//            backButton.addActionListener(new java.awt.event.ActionListener() {
//
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    setRate(-2.0f);
//                }
//            });
//        }
//        return backButton;
//    }

    /**
     * This method initializes forwardButton
     *
     * @return javax.swing.JButton
     */
//    private JButton getForwardButton() {
//        if (forwardButton == null) {
//            forwardButton = new JButton();
//            //forwardButton.setText("forward");
//            forwardButton.setOpaque(false);
//            forwardButton.setIcon(Images.get(Images.MEDIA_FASTFORWARD));
//            forwardButton.addActionListener(new java.awt.event.ActionListener() {
//
//                public void actionPerformed(java.awt.event.ActionEvent e) {
//                    setRate(2.0f);
//                }
//            });
//        }
//        return forwardButton;
//    }

    /**
     * This method initializes nextButton
     *
     * @return javax.swing.JButton
     */
//    private JButton getNextButton() {
//        if (nextButton == null) {
//            nextButton = new JButton();
//            //nextButton.setText("next");
//            nextButton.setIcon(Images.get(Images.MEDIA_STEPFORWARD));
//            nextButton.setOpaque(false);
//        }
//        return nextButton;
//    }

    /**
     * This method initializes previousButton
     *
     * @return javax.swing.JButton
     */
//    private JButton getPreviousButton() {
//        if (previousButton == null) {
//            previousButton = new JButton();
//            previousButton.setOpaque(false);
//            //previousButton.setText("previous");
//            previousButton.setIcon(Images.get(Images.MEDIA_STEPBACK));
//        }
//        return previousButton;
//    }

    /**
     * This method initializes positionPanel
     * @return javax.swing.JPanel
     */
    public JPanel getPositionPanel() {
        if (positionPanel == null) {
            lengthLabel = new JLabel();
            getLengthLabel().setText(nanosToString(0));
            getLengthLabel().setOpaque(false);
            positionLabel = new JLabel();
            getPositionLabel().setText(nanosToString(0));
            getPositionLabel().setOpaque(false);
            positionPanel = new JPanel();
            positionPanel.setOpaque(false);
            positionPanel.setLayout(new BorderLayout());
            positionPanel.add(getPositionSlider(), BorderLayout.CENTER);
            positionPanel.add(getPositionLabel(), BorderLayout.WEST);
            positionPanel.add(getLengthLabel(), BorderLayout.EAST);
        }
        return positionPanel;
    }

    /**
     * This method initializes positionSlider
     * @return javax.swing.JSlider
     */
    public JSlider getPositionSlider() {
        if (positionSlider == null) {
            positionSlider = new FmjSlider();
            positionSlider.setOpaque(false);
            positionSlider.setValue(0);
            positionSlider.setMinimum(0);
            positionSlider.setMaximum(100);
            positionSlider.setEnabled(true);
            positionSlider.addChangeListener(new SliderListener());
        }
        return positionSlider;
    }

    /**
     * This method initializes buttonPanel
     * @return javax.swing.JPanel
     */
    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            buttonPanel.setOpaque(false);
            //  buttonPanel.add(getPreviousButton(), new GridBagConstraints());
            //  buttonPanel.add(getBackButton(), new GridBagConstraints());
           // buttonPanel.add(getStopButton(), new GridBagConstraints());
            buttonPanel.add(getPlayButton(), new GridBagConstraints());
            // buttonPanel.add(getForwardButton(), new GridBagConstraints());
            //buttonPanel.add(getNextButton(), new GridBagConstraints());
        }
        return buttonPanel;
    }

    /**
     * This method initializes infoPanel
     * @return javax.swing.JPanel
     */
//    private JPanel getInfoPanel() {
//        if (infoPanel == null) {
//            infoPanel = new JPanel();
//            infoPanel.setLayout(new BorderLayout());
//            infoPanel.setOpaque(false);
//            infoPanel.add(getInfoTextPane(), BorderLayout.CENTER);
//        }
//        return infoPanel;
//    }

    /**
     * This method initializes infoTextPane
     * @return javax.swing.JTextPane
     */
//    private JTextPane getInfoTextPane() {
//        if (infoTextPane == null) {
//            infoTextPane = new JTextPane();
//            infoTextPane.setText("<html><body><center>Artist - Album - Title</center></body></html>");
//            infoTextPane.setContentType("text/html");
//            infoTextPane.setOpaque(false);
//            infoTextPane.setFont(new Font("Arial", Font.PLAIN, 13));
//            infoTextPane.setEditable(false);
//        }
//        return infoTextPane;
//    }

    /**
     * This method initializes audioPanel
     * @return javax.swing.JPanel
     */
    private JPanel getAudioPanel() {
        if (audioPanel == null) {
            GridBagConstraints gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.fill = GridBagConstraints.VERTICAL;
            gridBagConstraints.weightx = 1.0;
            audioPanel = new JPanel();
            audioPanel.setLayout(new GridBagLayout());
            audioPanel.setOpaque(false);
//            audioPanel.add(getVolumeSlider(), gridBagConstraints);
//            audioPanel.add(getMuteButton(), new GridBagConstraints());
        }
        return audioPanel;
    }

//    public void setAudioControlEnabled(boolean enabled) {
//        getVolumeSlider().setEnabled(true);
//        getMuteButton().setEnabled(true);
//    }

    /**
     * This method initializes volumeSlider
     * @return javax.swing.JSlider
     */
//    private JSlider getVolumeSlider() {
//        if (volumeSlider == null) {
//            volumeSlider = new JSlider();
//            volumeSlider.setMinimum(0);
//            volumeSlider.setMaximum(100);
//            volumeSlider.setValue(70);
//            volumeSlider.setPreferredSize(new Dimension(100, 29));
//            volumeSlider.setOpaque(false);
//            volumeSlider.addChangeListener(
//                    new ChangeListener() {
//
//                        public void stateChanged(ChangeEvent e) {
//                            if (!volumeSlider.getValueIsAdjusting()) {
//                                float newValue = (float) volumeSlider.getValue() / 100.0f;
//                                setGain(newValue);
//                            }
//                        }
//                    });
//        }
//        return volumeSlider;
//    }

    /**
     * This method initializes muteButton
     * @return javax.swing.JToggleButton
     */
//    private JToggleButton getMuteButton() {
//        if (muteButton == null) {
//            muteButton = new JToggleButton();
//            muteButton.setIcon(new ImageIcon(getClass().getResource("/net/sf/fmj/ui/images/Volume24.gif")));
//            muteButton.setOpaque(false);
//            muteButton.addActionListener(new ActionListener() {
//
//                public void actionPerformed(ActionEvent e) {
//                    setMute(muteButton.isSelected());
//                }
//            });
//        }
//        return muteButton;
//    }

    public void onStateChange(TransportControlState state) {

       // getStopButton().setEnabled(state.isAllowStop());
        getPlayButton().setEnabled(state.isAllowPlay());
       // setAudioControlEnabled(state.isAllowVolume());
    }

    private static int nanosToMillis(long nanos) {
        return (int) (nanos / 1000000L);
    }

    public void onDurationChange(long nanos) {
        if (nanos == Duration.DURATION_UNKNOWN.getNanoseconds()
                || nanos == Duration.DURATION_UNBOUNDED.getNanoseconds()) {
            positionSlider.setEnabled(true);
            getLengthLabel().setText("");
        } else {
            positionSlider.setEnabled(true);
            positionSlider.setMinimum(0);
            positionSlider.setMaximum(nanosToMillis(nanos));	// millis is good enough.
            getLengthLabel().setText(nanosToString(nanos));

        }
    }

    public void onProgressChange(long nanos) {
        if (positionSlider.isEnabled()) {

            positionSlider.setValue(nanosToMillis(nanos));


        }
        getPositionLabel().setText(nanosToString(nanos));

    }

    private static String zeroPad(int i, int len) {
        String result = Integer.toString(i);
        while (result.length() < len) {
            result = "0" + result;
        }
        return result;
    }

    private static String nanosToString(long nanos) {
        final long seconds = nanos / 1000000000L;
        final long minutes = seconds / 60;
        return "" + zeroPad((int) minutes, 2) + ":" + zeroPad((int) (seconds % 60), 2);
    }

    /**
     * @return the positionLabel
     */
    public JLabel getPositionLabel() {
        return positionLabel;
    }

    /**
     * @return the lengthLabel
     */
    public JLabel getLengthLabel() {
        return lengthLabel;
    }

    private class SliderListener implements ChangeListener {

        public void stateChanged(ChangeEvent event) {

            if (positionSlider.getValueIsAdjusting()) {             
                if(isplay){
                                       
                }
                player.setPosition(TimeUnit.SECONDS.convert((long) positionSlider.getValue(), TimeUnit.MILLISECONDS));
            }
        }

    }
}  //  @jve:decl-index=0:visual-constraint="10,10"

