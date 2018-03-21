package Interface;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class GuiForGame {
	
	private static final String FILE_PATH_PICTURE = "Your File_Path";
	private static final ImageIcon AGENT = new ImageIcon( FILE_PATH_PICTURE + "\\" + "agent.png");
	private static final ImageIcon AGENT_BRENCH = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Agent_Brench.png");
	private static final ImageIcon AGENT_GOLD= new ImageIcon( FILE_PATH_PICTURE + "\\" +"Agent_Gold.png");
	private static final ImageIcon AGENT_WIND = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Agent_Win.png");
	private static final ImageIcon AGENT_WIND_BRENCH = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Agent_Wind_Brench.png");
	private static final ImageIcon AGENT_GOLD_WIND_BRENCH = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Agent_Gold_Brench_Wind.png");
	private static final ImageIcon BRENCH_WIND = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Brench_Wind.png");
	private static final ImageIcon GOLD = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Gold.png");
	private static final ImageIcon GOLD_BRENCH = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Gold_Brench.png");
	private static final ImageIcon GOLD_WIND = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Gold_Wind.png");
	private static final ImageIcon GOLD_MONSTER = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Gold_Monster.png");
	private static final ImageIcon GOLD_BRENCH_WIND = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Gold_Brench_Wind.png");
	private static final ImageIcon HOLE = new ImageIcon( FILE_PATH_PICTURE + "\\" +"hh.png");
	private static final ImageIcon MONSTER = new ImageIcon( FILE_PATH_PICTURE + "\\" +"monst.png");
	private static final ImageIcon MONSTER_WIND = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Monster_Wind.png");
	private static final ImageIcon MONSTER_GOLD_WIND = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Monster_Gold_Wind.png");
	private static final ImageIcon SMELL = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Smell.png");
	private static final ImageIcon WIND = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Wind.png");
	private static final ImageIcon AGENT_HOLE = new ImageIcon( FILE_PATH_PICTURE + "\\" +"AGENT_HOLE.png");
	private static final ImageIcon AGENT_MONSTER = new ImageIcon( FILE_PATH_PICTURE + "\\" +"AGENT_MONSTER.png");
	private static final ImageIcon AGENT_Gold_Brench = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Agent_Gold_Brench.png");
	private static final ImageIcon AGENT_Gold_Wind = new ImageIcon( FILE_PATH_PICTURE + "\\" +"Agent_Gold_Wind.png");
	
	private static final String EVENTS_WUMPUS = "Wumpus";
	private static final String EVENTS_PIT = "Pit";
	private static final String FEATURE_WUMPUS = "Stench";
	private static final String FEATURE_PIT= "Breeze";
	private static final int NUM_WUMPUS = 1;
	private static final int NUM_GOLD = 1;
	private static final int NUM_PIT = 3;
	private static final int SIZE = 4;

	private JFrame frame;
	private JLabel block_1;
	private JLabel block_2;
	private JLabel block_3;
	private JLabel block_4;
	private JLabel block_5;
	private JLabel block_6;
	private JLabel block_7;
	private JLabel block_8;
	private JLabel block_9;
	private JLabel block_10;
	private JLabel block_11;
	private JLabel block_12;
	private JLabel block_13;
	private JLabel block_14;
	private JLabel block_15;
	private JLabel block_16;
	private ArrayList<JLabel> saveAllBlock;
	private ArrayList<String>[][] game2D;
	private ArrayList<String>[][] tmpGame2D;
	private ArrayList<ArrayList<String>[][]> allMap;
	private int countStep;
	private boolean isReset;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiForGame window = new GuiForGame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiForGame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		game2D = new ArrayList[SIZE][SIZE];
		allMap = new ArrayList<ArrayList<String>[][]>();
		countStep = 0;
		isReset = false;
		
		frame = new JFrame("Welcom to Agent Game");
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		frame.setBounds(100, 100, 900, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblState = new JLabel("YOU WIN",SwingConstants.CENTER);
		lblState.setForeground(Color.RED);
		lblState.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblState.setBackground(Color.GRAY);
		lblState.setBounds(10, 333, 196, 297);
		frame.getContentPane().add(lblState);
		lblState.setOpaque(true);
		lblState.setBorder(BorderFactory.createLineBorder(Color.black));
		lblState.hide();
		
		block_16 = new JLabel("",SwingConstants.CENTER);
		block_16.setIcon(null);
		block_16.setBackground(Color.WHITE);
		block_16.setBounds(698, 475, 164, 155);
		frame.getContentPane().add(block_16);
		block_16.setOpaque(true);
		block_16.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_10 = new JLabel("",SwingConstants.CENTER);
		block_10.setIcon(null);
		block_10.setBackground(Color.WHITE);
		block_10.setBounds(375, 321, 161, 155);
		frame.getContentPane().add(block_10);
		block_10.setOpaque(true);
		block_10.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_11 = new JLabel("",SwingConstants.CENTER);
		block_11.setIcon(null);
		block_11.setBackground(Color.WHITE);
		block_11.setBounds(535, 321, 164, 155);
		frame.getContentPane().add(block_11);
		block_11.setOpaque(true);
		block_11.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_6 = new JLabel("",SwingConstants.CENTER);
		block_6.setIcon(null);
		block_6.setBackground(Color.WHITE);
		block_6.setBounds(375, 166, 161, 155);
		frame.getContentPane().add(block_6);
		block_6.setOpaque(true);
		block_6.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_12 = new JLabel("",SwingConstants.CENTER);
		block_12.setIcon(null);
		block_12.setBackground(Color.WHITE);
		block_12.setBounds(698, 321, 164, 155);
		frame.getContentPane().add(block_12);
		block_12.setOpaque(true);
		block_12.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_2 = new JLabel("",SwingConstants.CENTER);
		block_2.setIcon(null);
		block_2.setBackground(Color.WHITE);
		block_2.setBounds(375, 11, 161, 155);
		frame.getContentPane().add(block_2);
		block_2.setOpaque(true);
		block_2.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_3 = new JLabel("",SwingConstants.CENTER);
		block_3.setIcon(null);
		block_3.setBackground(Color.WHITE);
		block_3.setBounds(535, 11, 164, 155);
		frame.getContentPane().add(block_3);
		block_3.setOpaque(true);
		block_3.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_4 = new JLabel("",SwingConstants.CENTER);
		block_4.setBackground(Color.WHITE);
		block_4.setBounds(698, 11, 164, 155);
		frame.getContentPane().add(block_4);
		block_4.setOpaque(true);
		block_4.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_7 = new JLabel("",SwingConstants.CENTER);
		block_7.setBackground(Color.WHITE);
		block_7.setBounds(535, 166, 164, 155);
		frame.getContentPane().add(block_7);
		block_7.setOpaque(true);
		block_7.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_8 = new JLabel("",SwingConstants.CENTER);
		block_8.setIcon(null);
		block_8.setBackground(Color.WHITE);
		block_8.setBounds(698, 166, 164, 155);
		frame.getContentPane().add(block_8);
		block_8.setOpaque(true);
		block_8.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_15 = new JLabel("",SwingConstants.CENTER);
		block_15.setIcon(null);
		block_15.setBackground(Color.WHITE);
		block_15.setBounds(535, 475, 164, 155);
		frame.getContentPane().add(block_15);
		block_15.setOpaque(true);
		block_15.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_14 = new JLabel("",SwingConstants.CENTER);
		block_14.setIcon(null);
		block_14.setBackground(Color.WHITE);
		block_14.setBounds(375, 475, 161, 155);
		frame.getContentPane().add(block_14);
		block_14.setOpaque(true);
		block_14.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_9 = new JLabel("",SwingConstants.CENTER);
		block_9.setIcon(null);
		block_9.setBackground(Color.WHITE);
		block_9.setBounds(216, 321, 161, 155);
		frame.getContentPane().add(block_9);
		block_9.setOpaque(true);
		block_9.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_13 = new JLabel("",SwingConstants.CENTER);
		block_13.setIcon(null);
		block_13.setBackground(Color.WHITE);
		block_13.setBounds(216, 475, 161, 155);
		frame.getContentPane().add(block_13);
		block_13.setOpaque(true);
		block_13.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_5 = new JLabel("",SwingConstants.CENTER);
		block_5.setIcon(null);
		block_5.setBackground(Color.WHITE);
		block_5.setBounds(216, 166, 161, 155);
		frame.getContentPane().add(block_5);
		block_5.setOpaque(true);
		block_5.setBorder(BorderFactory.createLineBorder(Color.black));
		
		block_1 = new JLabel("",SwingConstants.CENTER);
		block_1.setIcon(null);
		block_1.setBackground(Color.WHITE);
		block_1.setBounds(216, 11, 161, 155);
		frame.getContentPane().add(block_1);
		block_1.setOpaque(true);
		block_1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		saveAllBlock = new ArrayList<JLabel>();
		saveAllBlock.add(block_1);
		saveAllBlock.add(block_2);
		saveAllBlock.add(block_3);
		saveAllBlock.add(block_4);
		saveAllBlock.add(block_5);
		saveAllBlock.add(block_6);
		saveAllBlock.add(block_7);
		saveAllBlock.add(block_8);
		saveAllBlock.add(block_9);
		saveAllBlock.add(block_10);
		saveAllBlock.add(block_11);
		saveAllBlock.add(block_12);
		saveAllBlock.add(block_13);
		saveAllBlock.add(block_14);
		saveAllBlock.add(block_15);
		saveAllBlock.add(block_16);
		
		JLabel setting = new JLabel("Game Setting",SwingConstants.CENTER);
		setting.setFont(new Font("VNI-HLThuphap", Font.BOLD | Font.ITALIC, 20));
		setting.setBackground(Color.WHITE);
		setting.setForeground(Color.RED);
		setting.setBounds(10, 11, 196, 25);
		frame.getContentPane().add(setting);
		setting.setBorder(BorderFactory.createLineBorder(Color.black));
		setting.setOpaque(true);

		JButton createMap = new JButton("Create Map");
		createMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < saveAllBlock.size();i++) {
					saveAllBlock.get(i).setIcon(null);
				}
				game2D = createGame();
				tmpGame2D = new ArrayList[SIZE][SIZE];
				for(int i = 0; i < SIZE; i++) {
					for(int j = 0; j < SIZE; j++) {
						tmpGame2D[i][j] = new ArrayList<String>(game2D[i][j]);
					}
				}
				showGame(game2D);
				lblState.hide();
			}
		});
		
		createMap.setFont(new Font("Tahoma", Font.BOLD, 11));
		createMap.setForeground(Color.BLACK);
		createMap.setBounds(49, 74, 114, 25);
		createMap.setOpaque(true);
		frame.getContentPane().add(createMap);
		
		JButton start = new JButton("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!isReset) {
						lblState.setText(processingGame());

					}
					else if(isReset) {
						for(int i = 0; i < SIZE; i++) {
							for(int j = 0; j < SIZE; j++) {
								game2D[i][j] = new ArrayList<String>(tmpGame2D[i][j]);
							}
						}
						lblState.setText(processingGame());

						isReset = false;
					}
					
					JButton stepByStep = new JButton("Step by step");
					stepByStep.addActionListener(new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							
							showGame(allMap.get(countStep));
							countStep++;
							if(countStep == allMap.size()) {
								lblState.show();
								return;
							}
							
						}
					});
					stepByStep.setBounds(49, 158, 114, 31);
					frame.getContentPane().add(stepByStep);
					stepByStep.setFont(new Font("Tahoma", Font.BOLD, 11));
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		start.setFont(new Font("Tahoma", Font.BOLD, 11));
		start.setBounds(49, 132, 114, 31);
		frame.getContentPane().add(start);
		
		JButton reset= new JButton("RESET");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGame(tmpGame2D);
				isReset = true;
				countStep = 0;
				lblState.hide();
				
				allMap.clear();
			}
		});
		reset.setFont(new Font("Tahoma", Font.BOLD, 11));
		reset.setBounds(49, 216, 114, 31);
		frame.getContentPane().add(reset);
		
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		exit.setFont(new Font("Tahoma", Font.BOLD, 11));
		exit.setBounds(49, 276, 114, 31);
		frame.getContentPane().add(exit);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(226, 11, 644, 619);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.GRAY);
		lblNewLabel_1.setBounds(10, 11, 196, 310);
		lblNewLabel_1.setOpaque(true);
		frame.getContentPane().add(lblNewLabel_1);
		lblNewLabel_1.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(0, 0, 884, 641);
		lblNewLabel.setOpaque(true);
		frame.getContentPane().add(lblNewLabel);
		
	}
	
	public String processingGame() throws IOException, InterruptedException{
		
		Random a = new Random();
	
		ArrayList<String> visited = new ArrayList<String>(); //Save all block that Agent was visited;
		ArrayList<String> saveEvents = new ArrayList<String>(); // Save Event per block that Agent was visited;
		ArrayList<String> mainFlow  = new ArrayList<String>(); // Save location of Hole or Monster that Agent fond;
		ArrayList<String> tmpFlow  = new ArrayList<String>(); // Save location of Hole or Monster that Agent guess;
		ArrayList<String> guessEvents  = new ArrayList<String>(); // Save Events guess;
		
		
		boolean haveGold = false; //Check Agent that have gold??
		boolean isKnowWumpus = false; // Monster was found??
		boolean isWumpusDie = false; //check that Monster is killed??/
		String indexWumpus = ""; // Save index of Monster when Agent found it
		String locationOfAgent = "00"; // Agent start at this block;
		String locationPast = ""; //Save block that Agent moved
	
		int countmainWumpus = 0;// Count number of Monster that we know exactly
		
		ArrayList<String> canMove = new ArrayList<String>();
		
		while(!haveGold){
			
			visited.add(locationOfAgent);

			//***************************
			
			String[] splitLocation = locationOfAgent.split("");
			int row = Integer.parseInt(splitLocation[0]);
			int col = Integer.parseInt(splitLocation[1]);
			
			//B2-Save all events in block
			ArrayList<String> saveTmpEvents = new ArrayList<String>();
			for(int i = 0 ; i < game2D[row][col].size(); i++){
				if(game2D[row][col].get(i).equals(FEATURE_PIT)){
					String events = "B" + (row) + (col);
					if(!saveEvents.contains(events)){
						saveEvents.add(events);
						saveTmpEvents.add(events);
					}
				}
				else if(game2D[row][col].get(i).equals(FEATURE_WUMPUS)){
					String events = "S" + (row) + (col);
					if(!saveEvents.contains(events)){
						saveEvents.add(events);
						saveTmpEvents.add(events);
					}
						
				}
			}
			//-----------------------------------------------------------
			
			
			//B3,4,5---Save tmpFlow, Check events and visited -> delete event -> create guess
			for(int i = 0; i < saveTmpEvents.size(); i++){
				String[] splitEvents = saveTmpEvents.get(i).split("");
				ArrayList<String> aroundEvents = findAround(Integer.parseInt(splitEvents[1]),
															Integer.parseInt(splitEvents[2]));
				String checkHandM = "";
				if(splitEvents[0].equals("B"))
					checkHandM = "H";
				else
					checkHandM = "M";
				for(int j = 0 ; j < aroundEvents.size(); j++){
					if(!visited.contains(aroundEvents.get(j)) && !tmpFlow.contains(checkHandM + aroundEvents.get(j))){
						
						if(checkHandM.equals("H")){
							tmpFlow.add(checkHandM + aroundEvents.get(j));
							ArrayList<String> subAround = findAround(Integer.parseInt(aroundEvents.get(j).split("")[0]),
									Integer.parseInt(aroundEvents.get(j).split("")[1]));
							for(int indexSub = 0 ; indexSub < subAround.size(); indexSub++){
								if(!guessEvents.contains("B" + subAround.get(indexSub))){
									guessEvents.add("B" + subAround.get(indexSub));
								}
							}
						}
						else if(checkHandM.equals("M")  && countmainWumpus == 0){
							tmpFlow.add(checkHandM + aroundEvents.get(j));
							ArrayList<String> subAround = findAround(Integer.parseInt(aroundEvents.get(j).split("")[0]),
									Integer.parseInt(aroundEvents.get(j).split("")[1]));
							for(int indexSub = 0 ; indexSub < subAround.size(); indexSub++){
								if(checkHandM.equals("M") && !guessEvents.contains("S" + subAround.get(indexSub))){
									guessEvents.add("S" + subAround.get(indexSub));
								}
							}
						}
					}
					
				}
			}
			//--------------------------------------------------------------------------------------
			
			//B6--Compare between guess and visited, check events and delete
			for(int i = 0 ; i < guessEvents.size(); i++){
				
				String index = guessEvents.get(i).substring(1);
				if(visited.contains(index)){
					
					String name = guessEvents.get(i).substring(0,1);
					if(name.equals("B"))
						name = FEATURE_PIT;
					else if(name.equals("S"))
						name = FEATURE_WUMPUS;
			
					int tmpRow = Integer.parseInt(index.split("")[0]);
					int tmpCol = Integer.parseInt(index.split("")[1]);
					
					if(!game2D[tmpRow][tmpCol].contains(name)){
						guessEvents.remove(i);
						i--;
					}
				}
				
			}
			//--------------------------------------------------------------------------------------
			
			//B7--Compare tmpFlow and guess, after that, reset the value in guess
			ArrayList<String> tmpToCheck = new ArrayList<String>();
			for(int i = 0 ; i < tmpFlow.size(); i++){
				String name = tmpFlow.get(i).substring(0,1);
				String index = tmpFlow.get(i).substring(1);
				
				int tmpRow = Integer.parseInt(index.substring(0,1));
				int tmpCol = Integer.parseInt(index.substring(1));
				
				if(name.equals("H"))
					name = "B";
				else if(name.equals("M"))
					name = "S";
				
				ArrayList<String> tmpAround = findAround(tmpRow,tmpCol);
				for(int j = 0 ; j < tmpAround.size(); j++){
					tmpAround.set(j, name + tmpAround.get(j));
				}
				
				boolean check = true;
				for(int j = 0 ; j < tmpAround.size(); j++){
					if(!guessEvents.contains(tmpAround.get(j))){
						check = false;
						break;
					}
				}
				
				if(check == false){
					tmpFlow.remove(i);
					i--;
				}
				else{
					for(int j = 0; j < tmpAround.size(); j++){
						tmpToCheck.add(tmpAround.get(j));
					}
				}
	
			}
			
			for(int i = 0; i < guessEvents.size();i++){
				
				if(!tmpToCheck.contains(guessEvents.get(i))){
					guessEvents.remove(i);
					i--;
				}
			}
			//--------------------------------------------------------------------------------------
			
			//Check index of Monster and kill it
			int countWumpus = 0;
			ArrayList<String> saveWumpus = new ArrayList<String>();
			for(int i = 0 ; i < tmpFlow.size(); i++){
				if(tmpFlow.get(i).substring(0,1).equals("M")){
					saveWumpus.add(tmpFlow.get(i));
					indexWumpus = tmpFlow.get(i).substring(1);
					countWumpus++;
				}
			}
			
			if(countWumpus == 1){
				for(int i = 0 ; i < tmpFlow.size(); i++){
					if(tmpFlow.get(i).substring(0,1).equals("H") && 
							tmpFlow.get(i).substring(1).equals(indexWumpus)){
						tmpFlow.remove(i);
						break;
					}
				}
				isKnowWumpus = true;
			}
			
			ArrayList<String> processDoulicate = new ArrayList<String>();
			for(int i = 0 ; i < visited.size(); i++){
				if(!processDoulicate.contains(visited.get(i))){
					processDoulicate.add(visited.get(i));
				}
			}
			
			int countStench = 0;
			for(int i = 0 ; i < processDoulicate.size() ; i++){
				if(game2D[Integer.parseInt(processDoulicate.get(i).substring(0,1))]
						[Integer.parseInt(processDoulicate.get(i).substring(1))].contains(FEATURE_WUMPUS)){
					countStench++;
				}
			}
			
			if(countStench >= 2){
				int[] countQuantity= new int[saveWumpus.size()];
				for(int i = 0 ; i < saveWumpus.size(); i++){
					ArrayList<String> aroundWumpus = findAround(Integer.parseInt(saveWumpus.get(i).substring(1,2)),
							Integer.parseInt(saveWumpus.get(i).substring(2)));
					for(int j = 0 ; j < aroundWumpus.size(); j++){
						if(processDoulicate.contains(aroundWumpus.get(j)) 
						&& game2D[Integer.parseInt(aroundWumpus.get(j).substring(0,1))]
						[Integer.parseInt(aroundWumpus.get(j).substring(1))].contains(FEATURE_WUMPUS)){
							countQuantity[i]++;
						}
					}
				}
				
				int max = countQuantity[0];
				int indexMax = 0;
				for(int i = 1; i < countQuantity.length; i++){
					if(max < countQuantity[i]){
						max = countQuantity[i];
						indexMax = i;
					}
				}
				
				String mainWumpus = saveWumpus.get(indexMax);
				for(int i = 0 ; i < tmpFlow.size(); i++){
					if(tmpFlow.get(i).substring(0,1).equals("M") && !tmpFlow.get(i).equals(mainWumpus)){
						tmpFlow.remove(i);
						i--;
					}
					if(i >= 0 && tmpFlow.get(i).substring(0,1).equals("H") && 
							tmpFlow.get(i).substring(1).equals(mainWumpus.substring(1))){
						tmpFlow.remove(i);
						i--;
					}
				}
				
				countmainWumpus = 1;
				isKnowWumpus = true;
				indexWumpus = mainWumpus.substring(1);
				
			}
			
			if(countWumpus != 1 && isKnowWumpus == false){
				indexWumpus = "";
				isKnowWumpus = false;
			}
			
			else if(isKnowWumpus && !isWumpusDie){
				countmainWumpus = 1;
			
				int tmpRowWumpus = Integer.parseInt(indexWumpus.substring(0,1));
				int tmpColWumpus = Integer.parseInt(indexWumpus.substring(1));
				int mainRow =  Integer.parseInt(locationOfAgent.substring(0,1));
				int mainCol =  Integer.parseInt(locationOfAgent.substring(1));
			
				if(mainRow == tmpRowWumpus || mainCol == tmpColWumpus){
					
					ArrayList<String> deleteAround = findAround(tmpRowWumpus,tmpColWumpus);
					canMove.add(indexWumpus);
				
					game2D[tmpRowWumpus][tmpColWumpus].remove(EVENTS_WUMPUS);
					for(int i = 0 ; i < deleteAround.size(); i++){
						game2D[Integer.parseInt(deleteAround.get(i).substring(0,1))]
								[Integer.parseInt(deleteAround.get(i).substring(1))].remove(FEATURE_WUMPUS);
						deleteAround.set(i, "S" + deleteAround.get(i));
						if(tmpFlow.contains(deleteAround.get(i))){
							tmpFlow.remove(deleteAround.get(i));
						}
					}
					
					isWumpusDie = true;
					
					addMap(game2D);
					
				}
			}
			
			//--------------------------------------------------------------------------------------
			
			//Agent found gold and moving home
			if(game2D[row][col].contains("Gold")){
				
				haveGold = true;
				ArrayList<String> goHome = findWayGoHome(visited,"00",locationOfAgent);
				String lastMove = goHome.remove(goHome.size() - 1);
				for(int i = goHome.size() - 1; i >= 1 ; i--){
					game2D[Integer.parseInt(lastMove.split("")[0])][Integer.parseInt(lastMove.split("")[1])].remove("Agent");
					game2D[Integer.parseInt(goHome.get(i).split("")[0])][Integer.parseInt(goHome.get(i).split("")[1])].add("Agent");
					game2D[row][col].remove("Gold");
					lastMove = goHome.get(i);
					addMap(game2D);
				}
				
				game2D[Integer.parseInt(lastMove.split("")[0])][Integer.parseInt(lastMove.split("")[1])].remove("Agent");
				game2D[Integer.parseInt(goHome.get(0).split("")[0])][Integer.parseInt(goHome.get(0).split("")[1])].add("Agent");
				lastMove = goHome.get(0);
				addMap(game2D);
				
	
				return "YOU WIN";
			}
			//---------------------------------------------------------------------------------------
			
			//Love the way you like :))
			ArrayList<String> findWay = findAround(row,col);
			
			for(int i = 0; i < tmpFlow.size(); i++){
				if(canMove.contains(tmpFlow.get(i).substring(1))){
					tmpFlow.remove(i);
					i--;
				}
			}
			
			for(int i = 0; i < tmpFlow.size(); i++){
				String indexFlow = tmpFlow.get(i).substring(1);
				if(findWay.contains(indexFlow)){
					findWay.remove(indexFlow);
				}
			}
			
			for(int i = 0 ; i < findWay.size(); i++){
				if(visited.contains(findWay.get(i))){
					findWay.remove(i);
					i--;
				}
			}
			
			if( findWay.size() == 0 && canMove.isEmpty()){
				
				
				//You have no choice and must random way to go ( events Monster )
				if(game2D[Integer.parseInt(locationOfAgent.substring(0,1))]
						[Integer.parseInt(locationOfAgent.substring(1))].contains(FEATURE_WUMPUS)){
					int tmpRow = Integer.parseInt(locationOfAgent.substring(0,1));
					int tmpCol = Integer.parseInt(locationOfAgent.substring(1));
					
					ArrayList<String> lookAroundAgent = findAround(tmpRow,tmpCol);
					ArrayList<String> saveIndexOfWumpus = new ArrayList<String>();
					
					int count = 0;
					for(int i = 0 ; i < lookAroundAgent.size(); i++){
						if( tmpFlow.contains("M"+ lookAroundAgent.get(i))){
							saveIndexOfWumpus.add(lookAroundAgent.get(i));
							count++;
						}
					}
					
					if(count >= 2){
						
						int nextMove = a.nextInt(saveIndexOfWumpus.size());
						
						if(game2D[Integer.parseInt(saveIndexOfWumpus.get(nextMove).substring(0,1))]
								[Integer.parseInt(saveIndexOfWumpus.get(nextMove).substring(1))].contains(EVENTS_WUMPUS) 
								|| game2D[Integer.parseInt(saveIndexOfWumpus.get(nextMove).substring(0,1))]
										[Integer.parseInt(saveIndexOfWumpus.get(nextMove).substring(1))].contains(EVENTS_PIT)){
							locationPast = locationOfAgent;
							locationOfAgent = saveIndexOfWumpus.remove(nextMove);
				
							game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
							game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");
							
							addMap(game2D);
							
							return "GAME OVER";
						}
								
						//Your random way is right and keep playing game
						else{
							locationPast = locationOfAgent;
							locationOfAgent = saveIndexOfWumpus.remove(nextMove);
							if(canMove.contains(locationOfAgent)){
								canMove.remove(locationOfAgent);
							}
							game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
							game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");
							tmpFlow.remove("M" + locationOfAgent);
					
							isKnowWumpus = true;
						}
					}
					
				}
				
				else{
				//Find Hole that you know exactly location
					for(int i = 0; i < guessEvents.size(); i++){
						if( guessEvents.get(i).substring(0,1).equals("B") && 
								visited.contains(guessEvents.get(i).substring(1))){
							
							int count = 0;
							int tmpRow = Integer.parseInt(guessEvents.get(i).substring(1,2));
							int tmpCol = Integer.parseInt(guessEvents.get(i).substring(2));
							String tmpSave = "";
							ArrayList<String> lookAround = findAround(tmpRow,tmpCol);
							for(int j = 0 ; j < lookAround.size(); j++){
								if(tmpFlow.contains("H" + lookAround.get(j))){
									tmpSave = "H" + lookAround.get(j);
									count++;
								}
							}
							if(count == 1 && !mainFlow.contains(tmpSave)){
								mainFlow.add(tmpSave);
							}
						}
					}
					//----------------------------------------------------------
					
					//Processing if can't find enough main Hole
					if(mainFlow.size() < 3){
								
						String tail = "";
						
						//Find index of temperate Hole and move to it
						if(mainFlow.size() > 0 && mainFlow.size() < 3){
							for(int sub = 0 ; sub < tmpFlow.size(); sub++){
								boolean isDone = false;
								if(!mainFlow.contains(tmpFlow.get(sub))){
									ArrayList<String> around = findAround(Integer.parseInt(tmpFlow.get(sub).substring(1,2)),
																Integer.parseInt(tmpFlow.get(sub).substring(2)));
									for(String tmpString : around){
										if(visited.contains(tmpString)){
											tail = tmpString;
											isDone = true;
											break;
										}
									}
								}
								if(isDone)	break;
							}
							
							if(!tail.isEmpty()){
								ArrayList<String> around = findWayGoHome(visited,tail,locationOfAgent);
								
								for(int i = around.size() - 2; i >= 0 ; i--){
									
									locationPast = locationOfAgent;
									locationOfAgent = around.get(i);
									
									game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
									game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");
									
									addMap(game2D);
									
								}
							}
						}
						//---------------------------------------------------------------------------------
						
						//Random way to move
						if(game2D[Integer.parseInt(locationOfAgent.split("")[0])]
								[Integer.parseInt(locationOfAgent.split("")[1])].contains(FEATURE_PIT)){
							
							if(tail.isEmpty())
								tail = locationOfAgent;
							
							ArrayList<String> randomWay =  findAround(Integer.parseInt(tail.substring(0,1)),
																		Integer.parseInt(tail.substring(1)));
							for(int sub = 0 ; sub < randomWay.size(); sub++){
								if(visited.contains(randomWay.get(sub))){
									randomWay.remove(sub);
									sub--;
								}
							}
							int move = a.nextInt(randomWay.size());
							
							//If next way is hole, you will die and end game 
							if(game2D[Integer.parseInt(randomWay.get(move).substring(0,1))][Integer.parseInt(randomWay.get(move).substring(1))].contains("Pit")){
								locationPast = locationOfAgent;
								locationOfAgent = randomWay.remove(move);
								game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
								
								game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");
							
								addMap(game2D);
								
								return "GAME OVER";
							}
									
							//Your random way is right and keep playing game
							else{
								locationPast = locationOfAgent;
								locationOfAgent = randomWay.remove(move);
								if(canMove.contains(locationOfAgent)){
									canMove.remove(locationOfAgent);
								}
								game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
								game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");
								tmpFlow.remove("H" + locationOfAgent);
							}
						}
						//-------------------------------------------------------------------	
					}
					
					//Processing if you know enough main Hole : Delete all events and guess aren't right
					else{
						
						for(int i = 0 ; i < tmpFlow.size(); i++){
							if(tmpFlow.get(i).substring(0,1).equals("H") && !mainFlow.contains(tmpFlow.get(i))){
								canMove.add(tmpFlow.get(i).substring(1));
								tmpFlow.remove(i);
								i--;
							}
						}
						for(int i = 0; i < tmpFlow.size(); i++) {
							if(!mainFlow.contains(tmpFlow.get(i)) && !tmpFlow.get(i).substring(0,1).equals("M")
									&& !canMove.contains(tmpFlow.get(i).substring(1))) {
								canMove.add(tmpFlow.get(i).substring(1));
							}
						}
						ArrayList<String> reSetupEvents = new ArrayList<String>();
						for(int i = 0; i < mainFlow.size(); i++){
							String mainHole = mainFlow.get(i);
							ArrayList<String> aroundMainFlow = findAround(Integer.parseInt(mainHole.substring(1,2)),
												Integer.parseInt(mainHole.substring(2)));
							for(int j = 0; j < aroundMainFlow.size(); j++){
								if(!reSetupEvents.contains(aroundMainFlow.get(j))){
									reSetupEvents.add("B"+aroundMainFlow.get(j));
								}
							}
						}
						
						for(int i = 0; i < guessEvents.size(); i++){
							if(!reSetupEvents.contains(guessEvents.get(i))){
								guessEvents.remove(i);
								i--;
							}
						}
					}
					//---------------------------------------------------------------------------------------
				}
			}
			
			else if(findWay.size() == 0 && !canMove.isEmpty()){
				

				ArrayList<String> continueFindWay = findAround(Integer.parseInt(locationOfAgent.substring(0,1)),
						Integer.parseInt(locationOfAgent.substring(1)));
				
				for(int i = 0; i < tmpFlow.size(); i++){
					String indexFlow = tmpFlow.get(i).substring(1);
					if(continueFindWay.contains(indexFlow)){
						continueFindWay.remove(indexFlow);
					}
				}
				continueFindWay.remove(locationPast);
				
				if(continueFindWay.isEmpty()){
					String tmp = locationPast;
					locationPast = locationOfAgent;
					locationOfAgent = tmp;
					
					game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
					game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");

				}
				
				else{
					int move = a.nextInt(continueFindWay.size());
					locationPast = locationOfAgent;
					locationOfAgent = continueFindWay.remove(move);
					if(canMove.contains(locationOfAgent)){
						canMove.remove(locationOfAgent);
					}
					game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
					game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");
				}

			}
			
			else if( findWay.size() != 0 ){
				int move = a.nextInt(findWay.size());
				locationPast = locationOfAgent;
				locationOfAgent = findWay.remove(move);
				if(canMove.contains(locationOfAgent)){
					canMove.remove(locationOfAgent);
				}
				game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
				game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");

				for(int subindex = 0 ; subindex < findWay.size(); subindex++){
					if(!canMove.contains(findWay.get(subindex)) && !visited.contains(subindex))
						canMove.add(findWay.get(subindex));
				}
			}
			
			addMap(game2D);
			//-----------------------------------------------------------------------------
		}
		return "";
	}
	
	public  ArrayList<String> findWayGoHome(ArrayList<String> visited,String start,String end){
		ArrayList<String> goHome = new ArrayList<String>();
		int index = 0;
		for(int i = 0; i < visited.size(); i++){
			if(visited.get(i).equals(start)){
				index = i;
				break;
			}
		}
		
		int indexEnd = 0;
		for(int i = 0; i < visited.size(); i++){
			if(visited.get(i).equals(end)){
				indexEnd = i;
				break;
			}
		}
		
		while(index <= indexEnd){
			goHome.add(visited.get(index));
			
			for(int i = index + 1; i < indexEnd; i++){
				if(visited.get(i).equals(goHome.get(goHome.size()- 1))){
					index = i;
				}
				
			}
			index++;
		}
		return goHome;
	}
	
	public ArrayList<String>[][] createGame(){
		
		ArrayList<String>[] game1D = new ArrayList[SIZE * SIZE];

		for(int i = 0 ; i < game1D.length;i++){
			game1D[i] = new ArrayList<String>();
		}
		
		game1D = createEvents(game1D);
		
		return setEvents(convertTo2D(game1D));
	}
	
	private ArrayList<String>[][] convertTo2D(ArrayList<String>[] game){
		ArrayList<String>[][] game2D = new ArrayList[SIZE][SIZE];
		
		int index1D = 0;
		
		for(int i = 0 ; i < game2D.length; i++){
			for(int j = 0; j < game2D[0].length; j++){
				game2D[i][j] = new ArrayList<String>(game[index1D]);
				index1D++;
			}
		}
		
		return game2D;
	}
	
	private  ArrayList<String>[] createEvents(ArrayList<String>[] game){
		Random a = new Random();
		
		game[0].add("Agent");
		game = generateWumpus(game,a);
		game = generateGold(game,a);
		game = generatePit(game,a);
		
		return game;
	}
	
	private  ArrayList<String>[] generateWumpus(ArrayList<String>[] game, Random a){
		
		boolean check = false;
		
		do{
			int index = a.nextInt(16);
			if(index != 0 && index!= 1 && index != 4){
				game[index].add(EVENTS_WUMPUS);
				check = true;
			}
		}while(check == false);
		return game;
	}
	
	private  ArrayList<String>[] generateGold(ArrayList<String>[] game, Random a){
		
		boolean check = false;
		do{
			int index = a.nextInt(16);
			if(index != 0 && index!= 1 && index != 4){
				game[index].add("Gold");
				check = true;
			}
		}while(!check);
		return game;
	}
	
	private  ArrayList<String>[] generatePit( ArrayList<String>[] game, Random a){
		
		for(int i = 0 ; i < NUM_PIT; i++){
			
			boolean check = false;
			do{
				int index = a.nextInt(16);
				if(index != 0 && index!= 1 && index != 4 && !game[index].contains(EVENTS_WUMPUS) && !game[index].contains("Gold")
						&& !game[index].contains(EVENTS_PIT)){
						game[index].add(EVENTS_PIT);
						check = true;
				}
			}while(!check);
		}
		return game;
	}
	
	private  ArrayList<String>[][] setEvents(ArrayList<String>[][] game){
		
		for(int i = 0; i < SIZE; i++){
			for(int j = 0; j < SIZE; j++){
				
					ArrayList<String> save = new ArrayList<String>();
					save = findAround(i,j);
					
					if(game[i][j].contains(EVENTS_WUMPUS)){
						for(int index = 0 ; index < save.size(); index++){
							String[] split = save.get(index).split("");
							if(!game[Integer.parseInt(split[0])][Integer.parseInt(split[1])].contains(EVENTS_PIT))
								game[Integer.parseInt(split[0])]
									[Integer.parseInt(split[1])].add(FEATURE_WUMPUS);
						}
					}
					else if(game[i][j].contains(EVENTS_PIT)){
						for(int index = 0 ; index < save.size(); index++){
							String[] split = save.get(index).split("");
							if(!game[Integer.parseInt(split[0])][Integer.parseInt(split[1])].contains(EVENTS_PIT)
									&& !game[Integer.parseInt(split[0])][Integer.parseInt(split[1])].contains(FEATURE_PIT))
							game[Integer.parseInt(split[0])]
								[Integer.parseInt(split[1])].add(FEATURE_PIT);
						}
					}
				
			}
		}
		
		return game;
	}
	
	private  ArrayList<String> findAround(int row, int col){
		
		ArrayList<String> save = new ArrayList<String>();
		
		if((col + 1) < SIZE)
			save.add(row + "" + (col + 1));
		if((col - 1)  >= 0)
			save.add(row + "" + (col - 1));
		if((row - 1)  >= 0)
			save.add((row - 1)  + "" + col);
		if((row + 1)  < SIZE)
			save.add((row + 1)  + "" + col);
		
		return save;
	}
	
	private  void showGame(ArrayList<String>[][] game2D){
		for(int i = 0; i < game2D.length; i++){
			for(int j = 0; j < game2D[0].length; j++){
				System.out.print(game2D[i][j].toString() + " | ");
			}
			System.out.println();
		}
	
		for(int i = 0; i < saveAllBlock.size();i++) {
			saveAllBlock.get(i).setIcon(null);
		}
		
		 int indexBlock = 0;
		 for(int i = 0; i < game2D.length; i++) {
			 for(int j = 0; j < game2D[0].length; j++) {
				 
				 if(setImageBlock(game2D[i][j]) != null) {
					 this.saveAllBlock.get(indexBlock).setIcon(setImageBlock(game2D[i][j]));
				 }
				 indexBlock++;
			 }
		 }
	}
	
	private ImageIcon setImageBlock(ArrayList<String> events) {
		
		ImageIcon result = null;
		
		if(events.size() == 1) {
			switch(events.get(0)) {
				case "Agent" :
					result = AGENT;
					break;
				case FEATURE_WUMPUS:
					result = SMELL;
					break;
				case FEATURE_PIT:
					result = WIND;
					break;
				case "Gold" : 
					result =GOLD;
					break;
				case EVENTS_PIT : 
					result = HOLE;
					break;
				case EVENTS_WUMPUS : 
					result = MONSTER;
					break;
			}
		}
		
		else if(events.size() == 2) {
			if(events.contains("Agent") && events.contains(FEATURE_PIT))
				result = AGENT_WIND;
			else if(events.contains("Agent") && events.contains(FEATURE_WUMPUS))
				result = AGENT_BRENCH;
			else if(events.contains("Agent") && events.contains("Gold"))
				result = AGENT_GOLD;
			else if(events.contains(FEATURE_PIT) && events.contains(FEATURE_WUMPUS))
				result = BRENCH_WIND;
			else if(events.contains("Gold") && events.contains(FEATURE_WUMPUS))
				result = GOLD_BRENCH;
			else if(events.contains("Gold") && events.contains(EVENTS_WUMPUS))
				result = GOLD_MONSTER;
			else if(events.contains("Gold") && events.contains(FEATURE_PIT))
				result = GOLD_WIND;
			else if(events.contains(EVENTS_WUMPUS) && events.contains(FEATURE_PIT))
				result = MONSTER_WIND;
			else if(events.contains(EVENTS_WUMPUS) && events.contains("Agent"))
				result = AGENT_MONSTER;
			else if(events.contains("Agent") && events.contains(EVENTS_PIT))
				result = AGENT_HOLE;
		}
		
		else if(events.size() == 3) {
			if(events.contains("Agent") && events.contains(FEATURE_PIT)&& events.contains(FEATURE_WUMPUS))
				result = AGENT_WIND_BRENCH;
			else if(events.contains("Gold") && events.contains(FEATURE_PIT)&& events.contains(FEATURE_WUMPUS))
				result = GOLD_BRENCH_WIND;
			else if(events.contains("Gold") && events.contains(FEATURE_PIT)&& events.contains(EVENTS_WUMPUS))
				result = MONSTER_GOLD_WIND;
			else if(events.contains("Gold") && events.contains(FEATURE_PIT)&& events.contains("Agent"))
				result = AGENT_Gold_Wind;
			else if(events.contains("Gold") && events.contains(FEATURE_WUMPUS)&& events.contains("Agent"))
				result = AGENT_Gold_Brench;
		}
		
		else if ( events.size() == 4) {
			result = AGENT_GOLD_WIND_BRENCH;
		}

		return result;
	}
	
	private void addMap(ArrayList<String>[][] game2D) {
		
		ArrayList<String>[][] tmp = new ArrayList[SIZE][SIZE];
		
		for(int i = 0 ; i < game2D.length; i++) {
			for(int j = 0; j < game2D[0].length; j++) {
				tmp[i][j] = new ArrayList<String>(game2D[i][j]);
			}
		}
		
		this.allMap.add(tmp);
	}
}
