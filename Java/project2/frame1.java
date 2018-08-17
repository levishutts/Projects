import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Position;

import java.util.Hashtable;
import java.util.TimerTask;
import java.util.regex.Pattern;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;


public class frame1 extends JFrame  {

    private JPanel contentPane;
    public ButtonGroup buttonGroup_1 = new ButtonGroup();
    private JButton playAllButton;
    private JLabel deleteLabel;

    private JButton stopButton;
    private JLabel loadLabel;
    private JButton pauseButton;
    private JLabel soundLabel;
    public JLabel recordLabel;
    private JLabel saveLabel;
    private JLabel dirLabel;
    private JLabel timeLable;

    private static JLabel timerLabel;
    private JLabel pauseLabel;
    private JSlider xSlider;
    private JSlider ySlider;
    private JSlider volumeSlider;
    public static Timer timer;
    private static Timer timer2;
    public static int timeCount = 1;
    public static int time2Count = 1;
    private static JProgressBar progressBar;
    private static JTree tree;
    private JScrollPane scrollPane;
    private JPanel panelA;
    private JPanel panel_1;
    private JPanel panelC;
    public static int childCount = 0;
    public static String fileName;
    private JTextField searchTextField;
    private JButton searchButton;


    Icon volumeIcon = new ImageIcon(frame1.class.getResource("volume.png"));
    Icon noVolumeIcon = new ImageIcon(frame1.class.getResource("noVolume.png"));
    Icon saveIcon = new ImageIcon(frame1.class.getResource("save.png"));
    Icon recordIcon = new ImageIcon(frame1.class.getResource("play.png"));
    Icon timerIcon = new ImageIcon(frame1.class.getResource("uoregonLogo.png"));
    Icon pauseIcon = new ImageIcon(frame1.class.getResource("stop.png"));
    ImageIcon songIcon = new ImageIcon(frame1.class.getResource("musicalNote.png"));
    ImageIcon songListIcon = new ImageIcon(frame1.class.getResource("songList.png"));
    Icon deleteIcon = new ImageIcon(frame1.class.getResource("delete.png"));
    Icon loadIcon = new ImageIcon(frame1.class.getResource("load.png"));

    public static Color selectedColor = new Color(115,164,209);


    /**
     * Create the frame.
     */
    public frame1() {

        initComponents();
        createEvents();


    }

    ////////////// Timer Function//////////////////////////////////////////////////
    public static String convertToMinHour(int numb){
        int sec = numb % 60;
        int min = (numb / 60)%60;

        String result = String.format("%02d:%02d", min, sec);

        return result;

    }



    public static void timeUp(int nn){

    	timer.stop();
    	if (nn == 0){
        	// Save it
        	fileName = JOptionPane.showInputDialog("Input the file name");
        	addSong(fileName);
        	timeCount = 0;
        	timerLabel.setText(convertToMinHour(timeCount));
        }
        else {
        	timeCount = 0;
        	timerLabel.setText(convertToMinHour(timeCount));
        }

    }
    ///////////////////////////////////////////////////////////////////////////////////


    ///////////////Tree Node, Adding/deleting song Functions/////////////////
    private static DefaultMutableTreeNode getSelectedNode() {

            return (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

      }


    private static void addSong(String fileName){

    	DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
     //   fileName = convertToMinHour(timeCount-1) + "   " +fileName;
        model.insertNodeInto(new DefaultMutableTreeNode(fileName), root, childCount++);

      }


    private void removeSelectedSong() {

        DefaultMutableTreeNode selectedNode = getSelectedNode();
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        try {
        	model.removeNodeFromParent(selectedNode);
        }catch(IllegalArgumentException e){
        	e.getMessage();
        	JOptionPane.showMessageDialog(frame1.this, "You can not delete root folder", "Error",
        	          JOptionPane.ERROR_MESSAGE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////

    //\\\\\\\\\\\\\\\\\\\\\\\\Song Functions\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public static void volumeChange(int fps){
    	try {
        	FloatControl gainControl = (FloatControl) play.clip.getControl(FloatControl.Type.MASTER_GAIN);
    		float volume = (float) 1.0f;
    		if (fps == 0){
    			gainControl.setValue(-80.0f);
    		}else if (fps == 100){
    			gainControl.setValue(6.0f);
    		}else if (fps > 50 && fps < 100){
    			volume = Math.round((fps-50) / 8.2);
    			gainControl.setValue(volume);
    		}else {
    			volume = Math.round((50-fps)/10.0 * (-16));
    			gainControl.setValue(volume);
    		}

    	} catch (NullPointerException e){
    		System.out.println("No Song is Play at this Point");
    	}

    }


    public static double getSongLength(String songName){
    	File file = new File(songName+".wav");
    	System.out.println(file.length());
    	double na = file.length() / 16000 / 2.0;
    	Math.round(na);
    	System.out.println("%%"+na);
    	return na;
    }



    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



    /////////////////////////////////////////////////////////////////////////////
    // Functions for overriding the paintComonent of JTree
    // Mainly use to change the color and length when selecting clip
    /////////////////////////////////////////////////////////////////////////////

    public static class MyTree extends JTree {

        protected void paintComponent(Graphics g) {

             // paint background
             g.setColor(getBackground());
             g.fillRect(0, 0, getWidth(), getHeight());

             // paint selected node's background and border

             int fromRow = getRowForPath( getSelectionPath());
             if (fromRow != -1) {
                  int toRow = fromRow + 1;
                  Rectangle fromBounds = getRowBounds(fromRow);
                  Rectangle toBounds = getRowBounds(toRow - 1);
                  if (fromBounds != null && toBounds != null) {
                       g.setColor(selectedColor);
                       g.fillRect(0, fromBounds.y, getWidth(), toBounds.y - fromBounds.y + toBounds.height);
                       g.setColor(selectedColor);
                       g.drawRect(0, fromBounds.y, getWidth() - 1, toBounds.y - fromBounds.y + toBounds.height);
                  }
             }

             // perform operation of superclass
             setOpaque(false); // trick not to paint background
             super.paintComponent(g);
        }


   }

    public static class MyTreeCellRenderer extends DefaultTreeCellRenderer {
        public MyTreeCellRenderer() {
             setBackgroundNonSelectionColor(null);
        }

        public Color getBackground() {
             return null;
        }
   }


    ////////////////////////////////////////////////////////////////////////////////////



    // \\\\\\\\\\\\\\\\\\\\\ActionListener\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    ActionListener updateTimerLabel = new ActionListener(){
        public void actionPerformed(ActionEvent e){

            timerLabel.setText(convertToMinHour(timeCount++));

            if (timeCount >= 299){
            	int nn = JOptionPane.showConfirmDialog(frame1.this, "Do you want to save the song", "Time is Up!!",JOptionPane.YES_NO_OPTION);
            	timeUp(nn);

            }
        }
    };

   ActionListener updateProBar = new ActionListener(){
	   
        public void actionPerformed(ActionEvent e){
        	String s = getSelectedNode().toString();
        	int length = (int) getSongLength(s);
        //	System.out.println("length" + length);
            progressBar.setMinimum(1);
            progressBar.setMaximum(1000);
            int pro = progressBar.getValue();
            timeLable.setText(convertToMinHour(time2Count++));
            int val = 1000/length;
          //  System.out.println(val);
            if (val+pro >= 1000){
                progressBar.setValue(1000);
                timer2.stop();
                return;
            }

            progressBar.setValue( pro+val);
            
            if (time2Count == length){
            	
            }

        }
    };

    ChangeListener getSliderValue = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			// System.out.println(((JSlider)e.getSource()).getValue());
			JSlider source = (JSlider)e.getSource();
		    if (!source.getValueIsAdjusting()) {
		        int fps = (int)source.getValue();
		        System.out.println(fps);
		    }
		}
    };

    ChangeListener speedChange = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			//Max = 6.0206, Min = -80.0, setValue has to be float number
			//Calculation based on slider 1-100%, 50%=1;
			
			float fps = (int)source.getValue();
			System.out.println("fps is " + fps);
			/*
			if (!source.getValueIsAdjusting()) {
				if (fps == 0){
					soundLabel.setIcon(noVolumeIcon);
				}
				else{
					soundLabel.setIcon(volumeIcon);
				}
				volumeChange(fps);
			}
			*/
			//float f = 
			edit.speed(fps,play.clip, play.format);

		}
    };
    
    ChangeListener volumeChange = new ChangeListener(){
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			//Max = 6.0206, Min = -80.0, setValue has to be float number
			//Calculation based on slider 1-100%, 50%=1;
			
			float fps = (int)source.getValue();
			System.out.println("fps is " + fps);
			/*
			if (!source.getValueIsAdjusting()) {
				if (fps == 0){
					soundLabel.setIcon(noVolumeIcon);
				}
				else{
					soundLabel.setIcon(volumeIcon);
				}
				volumeChange(fps);
			}
			*/
			//float f = 
			edit.volume(fps,play.clip);

		}
    };
    



    // \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



    ////////////////////////////////////////////////////////
    // This method contains all the code for creating and
    // initializing components
    ////////////////////////////////////////////////////////
    public void initComponents(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(frame1.class.getResource("microphone.png")));
        setTitle("Recorder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 905, 469);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setForeground(Color.WHITE);
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        setContentPane(contentPane);

        panelA = new JPanel();
        panelA.setForeground(new Color(0, 100, 0));
        panelA.setBackground(new Color(255, 255, 255));
        panelA.setBorder(new TitledBorder(null, "Let's Start Record~", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        scrollPane = new JScrollPane();

        progressBar = new JProgressBar();
       

        panelC = new JPanel();
        panelC.setFont(panelC.getFont().deriveFont(panelC.getFont().getStyle() | Font.BOLD, 13f));
        panelC.setForeground(new Color(0, 128, 0));
        panelC.setBackground(Color.WHITE);
        panelC.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Edit Here~", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelC.setEnabled(true);


        playAllButton = new JButton("Play ");
        playAllButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        playAllButton.setIcon(new ImageIcon(frame1.class.getResource("play-button.png")));
        playAllButton.setBackground(new Color(255, 255, 255));

        stopButton = new JButton("Stop");
        stopButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        stopButton.setIcon(new ImageIcon(frame1.class.getResource("stop_2.png")));

        pauseButton = new JButton("Pause");
        pauseButton.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        pauseButton.setIcon(new ImageIcon(frame1.class.getResource("pause.png")));

        timeLable = new JLabel();
        timeLable.setText("00:00");

      //  soundLabel.add(volumeSlider);


        /////////// GroupLayout ////////////////////////////////////
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(panelA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(45)
        					.addComponent(playAllButton, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
        					.addGap(30)
        					.addComponent(pauseButton))
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGap(18)
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        							.addGap(6))
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
        								.addComponent(stopButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        								.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE))
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(timeLable)))))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addComponent(panelC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addComponent(panelC, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(gl_contentPane.createSequentialGroup()
        					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_contentPane.createSequentialGroup()
        							.addGap(12)
        							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
        							.addGap(18)
        							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
        								.addGroup(gl_contentPane.createSequentialGroup()
        									.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.RELATED)
        									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
        										.addComponent(playAllButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        										.addComponent(pauseButton)
        										.addComponent(stopButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        									.addPreferredGap(ComponentPlacement.RELATED))
        								.addComponent(timeLable)))
        						.addComponent(panelA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addGap(7))))
        );

        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
		position.put(0, new JLabel("0"));
		position.put(50, new JLabel("50"));
		position.put(100, new JLabel("100"));
		
        Hashtable<Integer, JLabel> speed = new Hashtable<Integer, JLabel>();
		position.put(0, new JLabel("0"));
		position.put(1, new JLabel("1"));
		position.put(2, new JLabel("2"));

		JLabel lblXxx = new JLabel("Speed");
        xSlider = new JSlider();
     //   xSlider.setMajorTickSpacing(25);
      //  xSlider.setMinorTickSpacing(25);
        xSlider.setPaintTicks(true);
        xSlider.setPaintLabels(true);
    //    xSlider.setLabelTable(speed);
	//	xSlider.setMinimum(0);
	//	xSlider.setMaximum(100);
        xSlider.addChangeListener(speedChange); // Add change listener to the slider

        JLabel lblYyy = new JLabel("YYY");
        ySlider = new JSlider();
        ySlider.setMajorTickSpacing(25);
        ySlider.setMinorTickSpacing(25);
        ySlider.setPaintTicks(true);
        ySlider.setPaintLabels(true);
        ySlider.setLabelTable(position);
		ySlider.addChangeListener(getSliderValue);

        soundLabel = new JLabel(volumeIcon);

        		volumeSlider = new JSlider();

	//	volumeSlider.add(soundLabel);
        		volumeSlider.setBorder(null);
        		volumeSlider.setMinimum(0);
        		volumeSlider.setMaximum(100);
        		volumeSlider.addChangeListener(volumeChange);
        GroupLayout gl_panelC = new GroupLayout(panelC);
        gl_panelC.setHorizontalGroup(
        	gl_panelC.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelC.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelC.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelC.createSequentialGroup()
        					.addGroup(gl_panelC.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(lblXxx, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(lblYyy, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(gl_panelC.createParallelGroup(Alignment.TRAILING)
        						.addGroup(gl_panelC.createSequentialGroup()
        							.addComponent(ySlider, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
        							.addGap(6))
        						.addComponent(xSlider, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))
        				.addGroup(gl_panelC.createSequentialGroup()
        					.addGap(9)
        					.addComponent(soundLabel)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(volumeSlider, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        					.addContainerGap())))
        );
        gl_panelC.setVerticalGroup(
        	gl_panelC.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelC.createSequentialGroup()
        			.addGap(25)
        			.addGroup(gl_panelC.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelC.createSequentialGroup()
        					.addGap(8)
        					.addComponent(lblXxx, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
        					.addGap(47)
        					.addComponent(lblYyy, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panelC.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(xSlider, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(ySlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
        			.addGap(51)
        			.addGroup(gl_panelC.createParallelGroup(Alignment.LEADING)
        				.addComponent(soundLabel)
        				.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addGap(58))
        );
        panelC.setLayout(gl_panelC);

        panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "Saved Recordings~", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
        panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_1.setForeground(new Color(0, 100, 0));
        panel_1.setBackground(Color.WHITE);
        scrollPane.setViewportView(panel_1);
      //  scrollPane.setV


        tree = new MyTree();

        tree.setToolTipText("");
        tree.setBackground(Color.WHITE);

        tree.setDropMode(DropMode.ON);
        tree.setShowsRootHandles(true);

        //need to create root here first and it cannot set to invisible.
        tree.setModel(new DefaultTreeModel(
        	new DefaultMutableTreeNode("Saved Recording") {
        		{
        		
        		}
        	}
        ));

        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

        TreeSelectionListener treeSelectionListener = new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent e) {
            	DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            	// tree.treeDidChange();
            	if (node == null){
            		return;
            	}

            }
        };

        tree.addTreeSelectionListener(treeSelectionListener);
        tree.setRowHeight(30);


        MyTreeCellRenderer renderer = new MyTreeCellRenderer();
        renderer.setBorderSelectionColor(selectedColor);
        renderer.setBackgroundSelectionColor(selectedColor);
        renderer.setOpenIcon(songListIcon);
        renderer.setLeafIcon(songIcon);
        renderer.setClosedIcon(songIcon);
        tree.setCellRenderer( renderer);



        deleteLabel = new JLabel(deleteIcon);

        searchTextField = new JTextField();
        searchTextField.setText("");
        searchTextField.setColumns(10);

        searchButton = new JButton("Search");

        loadLabel = new JLabel(loadIcon);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addGap(17)
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel_1.createSequentialGroup()
        					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
        						.addGroup(gl_panel_1.createSequentialGroup()
        							.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
        							.addGap(18)
        							.addComponent(searchButton))
        						.addComponent(tree, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
        					.addContainerGap(19, Short.MAX_VALUE))
        				.addGroup(gl_panel_1.createSequentialGroup()
        					.addGap(8)
        					.addComponent(deleteLabel, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
        					.addGap(44)
        					.addComponent(loadLabel, GroupLayout.PREFERRED_SIZE, 122, Short.MAX_VALUE)
        					.addGap(50))))
        );
        gl_panel_1.setVerticalGroup(
        	gl_panel_1.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel_1.createSequentialGroup()
        			.addGap(9)
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
        				.addComponent(searchTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel_1.createSequentialGroup()
        					.addGap(1)
        					.addComponent(searchButton)))
        			.addGap(6)
        			.addComponent(tree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
        			.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
        				.addComponent(loadLabel)
        				.addComponent(deleteLabel))
        			.addGap(8))
        );
        panel_1.setLayout(gl_panel_1);



        saveLabel = new JLabel(saveIcon);
        saveLabel.setFont(new Font("Verdana", Font.BOLD, 13));
        saveLabel.setForeground(new Color(255, 215, 0));
        saveLabel.setText("Save");
        saveLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        saveLabel.setHorizontalTextPosition(JLabel.CENTER);
        saveLabel.setVerticalTextPosition(JLabel.CENTER);


        recordLabel = new JLabel(recordIcon);
        recordLabel.setForeground(new Color(255, 215, 0));
        recordLabel.setFont(new Font("Verdana", Font.BOLD, 13));
        recordLabel.setText("Start");
        recordLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        recordLabel.setHorizontalTextPosition(JLabel.CENTER);
        recordLabel.setVerticalTextPosition(JLabel.CENTER);


        timerLabel = new JLabel(timerIcon);
        timerLabel.setForeground(new Color(255, 215, 0));
        timerLabel.setText("00:00");
        timerLabel.setHorizontalTextPosition(JLabel.CENTER);
        timerLabel.setVerticalTextPosition(JLabel.CENTER);
        timerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 40));


        timer = new Timer(1000, updateTimerLabel);
        timer2 = new Timer(1000, updateProBar);

        pauseLabel = new JLabel(pauseIcon);
        pauseLabel.setFont(new Font("Verdana", Font.BOLD, 13));
        pauseLabel.setForeground(new Color(255, 215, 0));
        pauseLabel.setText("Stop");
        pauseLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        pauseLabel.setHorizontalTextPosition(JLabel.CENTER);
        pauseLabel.setVerticalTextPosition(JLabel.CENTER);


        dirLabel = new JLabel("Tap the Start to start recording");
        dirLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dirLabel.setFont(new Font("Lucida Grande", Font.BOLD, 12));

        GroupLayout gl_panelA = new GroupLayout(panelA);
        gl_panelA.setHorizontalGroup(
        	gl_panelA.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelA.createSequentialGroup()
        			.addGroup(gl_panelA.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panelA.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(pauseLabel)
        					.addGap(18)
        					.addComponent(recordLabel)
        					.addGap(18)
        					.addComponent(saveLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
        					.addGap(23))
        				.addComponent(timerLabel, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
        			.addGap(0))
        		.addGroup(gl_panelA.createSequentialGroup()
        			.addComponent(dirLabel, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
        			.addContainerGap())
        );
        gl_panelA.setVerticalGroup(
        	gl_panelA.createParallelGroup(Alignment.TRAILING)
        		.addGroup(gl_panelA.createSequentialGroup()
        			.addGap(28)
        			.addComponent(timerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(27)
        			.addComponent(dirLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGap(58)
        			.addGroup(gl_panelA.createParallelGroup(Alignment.BASELINE)
        				.addComponent(recordLabel)
        				.addComponent(pauseLabel)
        				.addComponent(saveLabel))
        			.addGap(8))
        );
        panelA.setLayout(gl_panelA);
        contentPane.setLayout(gl_contentPane);
    }



    ////////////////////////////////////////////////////////
    // This method contains all the code for creating events
    ////////////////////////////////////////////////////////

    public void createEvents(){

        // MouseListener
        recordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0){

               // timer = new Timer(1000, updateTimerLabel);
                if (timer.isRunning()){
                	File file = new File("null.wav");
    				file.delete();
    				record.start();
                	timer.restart();
                	timeCount = 0;
                	System.out.println("restart !!!");
                }
                else{

                	record.start();
                	timer.start();
                	dirLabel.setText("Recording \nTap Start to record again ");
                }

                System.out.println("Mic is clicked");
                System.out.println(timeCount);

            }
        });

        // ActionListener
        pauseLabel.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent arg0){

                timerLabel.setText(convertToMinHour(timeCount++));
                dirLabel.setText("Record Stop");
                if (timer.isRunning()){
                    timer.stop();
                }

                System.out.print("Stop button is clicked");
                System.out.println(timeCount);
                record.finish();
            }
        });


        saveLabel.addMouseListener(new MouseAdapter(){
        	@Override
        	public void mouseClicked(MouseEvent arg0){
        		timer.stop();
                fileName = JOptionPane.showInputDialog(frame1.this,"Input the file name");
                System.out.print(fileName + timeCount);

                //handle empty filename
                while (fileName.isEmpty()){
                	fileName = JOptionPane.showInputDialog("You need to input the file name");
                }

                //handle duplicated filename
            	int startRow = 0;
            	TreePath searchNodepath = tree.getNextMatch(fileName, startRow, Position.Bias.Forward);
            	while (searchNodepath != null){
            		fileName = JOptionPane.showInputDialog("input another file name");
            		searchNodepath = tree.getNextMatch(fileName, startRow, Position.Bias.Forward);
            		System.out.println(searchNodepath);

                }

                //handle saving empty recording
                if (timeCount == 0){
                	JOptionPane.showMessageDialog(frame1.this, "Empty Recording", "Error",
              	          JOptionPane.ERROR_MESSAGE);
                	return;
                }

                //creat the null.wav from saveLoad.java, rename it after user input fileName
                File oldFile = new File("null.wav");
                File newFile = new File(fileName +".wav");
                oldFile.renameTo(newFile);

                addSong(fileName);

                timeCount = 0;
                timerLabel.setText(convertToMinHour(timeCount));
                dirLabel.setText("Tap the Play to start recording");
                tree.expandRow(0);

        	}
        });

        deleteLabel.addMouseListener(new MouseAdapter(){
        	@Override
        	public void mouseClicked(MouseEvent arg0){
        		try{
        			if (play.status == "playing"){
        				play.pause();
        			}

            		int n = JOptionPane.showConfirmDialog(frame1.this, "Do you want to delete the song", "",JOptionPane.YES_NO_OPTION);
            		// n == 1, No; n == 0, YES
            		System.out.print(n);
            		if (n == 0){
            			TreePath[] paths = tree.getSelectionPaths();
            			for (TreePath path : paths) {
            				String songName = path.getLastPathComponent().toString();
            				File file = new File(songName);
            				file.delete();
                            System.out.println("You've deleted: " + path.getLastPathComponent());
                            removeSelectedSong();
                            childCount--;
                        }

            		}else{
            			//do nothing

            		}
        		}catch(Exception e1){
            		JOptionPane.showMessageDialog(frame1.this, "No Song to Delete", "Error",
              	          JOptionPane.ERROR_MESSAGE);
        			System.out.println("No song to Delete");
        		}

        	}
        });


        playAllButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		progressBar.setValue(0);
        		timeLable.setText("00:00");
        		time2Count = 1;
        		 TreePath[] paths = tree.getSelectionPaths();
        		 for (TreePath path : paths) {
        			 String songName = path.getLastPathComponent().toString();
        			 play.play(songName+".wav");
        			 play.startPlaying();
        			 timer2.start();
                     System.out.println("You've selected: " + path.getLastPathComponent());
                 }
        	}
        });

        searchButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		String searchName = searchTextField.getText();
        		int startRow = 0;
            	TreePath searchNodepath = tree.getNextMatch(searchName, startRow, Position.Bias.Forward);
            	if (searchNodepath == null){
            		JOptionPane.showMessageDialog(frame1.this, "File do not exsit", "Error",
                	          JOptionPane.ERROR_MESSAGE);
            		searchTextField.setText("");
                  	return;
            	}else{
            		tree.setSelectionPath(searchNodepath);
            		searchTextField.setText("");
            	}
        	}
        });

        stopButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        	//	play.clip.stop();
        		/*
       		 TreePath[] paths = tree.getSelectionPaths();
       		 for (TreePath path : paths) {  // for clip : list[clip]  clip.
       			 System.out.println(path);
       			 play.clip.close();
                }
                */
        		play.stopAll();
        	}
        });
        
        

        loadLabel.addMouseListener(new MouseAdapter(){
        	@Override
        	public void mouseClicked(MouseEvent arg0){
        		String fullName = null;
    			try {
					fullName = saveLoad.load();
	        		String[] songName = fullName.split(Pattern.quote("."));
	        		String name = songName[0]; // taking off the .wav
	        		addSong(name);
	        		tree.expandRow(0);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("Exception: import cancel");
				}

        	}
        });
        
        pauseButton.addActionListener(new ActionListener(){
        	@Override
        	public void actionPerformed(ActionEvent e){
        		
        	}
        });


    }
}
