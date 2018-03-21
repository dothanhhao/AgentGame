package SourceCode;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Processing {

	private static final String EVENTS_WUMPUS = "Wumpus";
	private static final String EVENTS_PIT = "Pit";
	private static final String FEATURE_WUMPUS = "Stench";
	private static final String FEATURE_PIT= "Breeze";
	private static final int NUM_WUMPUS = 1;
	private static final int NUM_GOLD = 1;
	private static final int NUM_PIT = 3;
	private static final int SIZE = 4;
	
	public static void main(String[] args) throws IOException{
		
		Random a = new Random();
		ArrayList<String>[][] game2D = createGame();
		
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

			System.out.println("......Moving.........");
			//B1--Save Block visited
			visited.add(locationOfAgent);
			showGame(game2D);
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
					System.out.println();
					showGame(game2D);
					System.out.println("......Moving.........");
					System.out.println();
					System.out.println();
				}
			}
			
			//--------------------------------------------------------------------------------------
			
			//Agent found gold and moving home
			if(game2D[row][col].contains("Gold")){
				game2D[row][col].remove("Gold");
				haveGold = true;
				ArrayList<String> goHome = findWayGoHome(visited,"00",locationOfAgent);
				String lastMove = goHome.remove(goHome.size() - 1);
				for(int i = goHome.size() - 1; i >= 1 ; i--){
					game2D[Integer.parseInt(lastMove.split("")[0])][Integer.parseInt(lastMove.split("")[1])].remove("Agent");
					game2D[Integer.parseInt(goHome.get(i).split("")[0])][Integer.parseInt(goHome.get(i).split("")[1])].add("Agent");
					lastMove = goHome.get(i);
					System.out.println();
					showGame(game2D);
					System.out.println();
					System.out.println("......Moving.........");
					System.out.println();
				}
				
				game2D[Integer.parseInt(lastMove.split("")[0])][Integer.parseInt(lastMove.split("")[1])].remove("Agent");
				game2D[Integer.parseInt(goHome.get(0).split("")[0])][Integer.parseInt(goHome.get(0).split("")[1])].add("Agent");
				lastMove = goHome.get(0);
				System.out.println();
				showGame(game2D);
				System.out.println("......YOU WIN.........");
				break;
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
					System.out.println("Stench is hereeeeeeeeeeeeeeeeeee" );
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
								[Integer.parseInt(saveIndexOfWumpus.get(nextMove).substring(1))].contains(EVENTS_WUMPUS)){
							locationPast = locationOfAgent;
							locationOfAgent = saveIndexOfWumpus.remove(nextMove);
				
							game2D[Integer.parseInt(locationPast.split("")[0])][Integer.parseInt(locationPast.split("")[1])].remove("Agent");
							game2D[Integer.parseInt(locationOfAgent.split("")[0])][Integer.parseInt(locationOfAgent.split("")[1])].add("Agent");
							System.out.println();
							showGame(game2D);
							System.out.println("-------------GAME OVER -------------");
							
							return;
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
							System.out.println("Delete Wumpus " + "M" + locationOfAgent);
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
									System.out.println();
									showGame(game2D);
									System.out.println("......Moving.........");
									System.out.println();
									System.out.println();
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
								System.out.println();
								showGame(game2D);
								System.out.println("-------------GAME OVER -------------");
								
								return;
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
						System.out.println(" 3 HOLE ");
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
			//-----------------------------------------------------------------------------
		
			System.out.println();
			System.out.println();
		}
	}
	
	/*
	 * Using Graph theory to convert visited become way to go home
	 */
	public static ArrayList<String> findWayGoHome(ArrayList<String> visited,String start,String end){
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
	
	public static ArrayList<String>[][] createGame(){
		
		ArrayList<String>[] game1D = new ArrayList[SIZE * SIZE];

		for(int i = 0 ; i < game1D.length;i++){
			game1D[i] = new ArrayList<String>();
		}
		
		game1D = createEvents(game1D);
		
		return setEvents(convertTo2D(game1D));
	}
	
	public static ArrayList<String>[][] createGame1(){
		
		ArrayList<String>[] game1D = new ArrayList[SIZE * SIZE];

		for(int i = 0 ; i < game1D.length;i++){
			game1D[i] = new ArrayList<String>();
		}
		
		game1D[0].add("Agent");
		game1D[12].add(EVENTS_PIT);
		//game1D[6].add(EVENTS_WUMPUS);
		game1D[13].add("Gold");
		game1D[14].add(EVENTS_PIT);
		game1D[15].add(EVENTS_PIT);
		
		return setEvents(convertTo2D(game1D));
	}
	
	
	private static ArrayList<String>[][] convertTo2D(ArrayList<String>[] game){
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
	
	private static ArrayList<String>[] createEvents(ArrayList<String>[] game){
		Random a = new Random();
		
		game[0].add("Agent");
		game = generateWumpus(game,a);
		game = generateGold(game,a);
		game = generatePit(game,a);
		
		return game;
	}
	
	private static ArrayList<String>[] generateWumpus(ArrayList<String>[] game, Random a){
		
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
	
	private static ArrayList<String>[] generateGold(ArrayList<String>[] game, Random a){
		
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
	
	private static ArrayList<String>[] generatePit( ArrayList<String>[] game, Random a){
		
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
	
	private static ArrayList<String>[][] setEvents(ArrayList<String>[][] game){
		
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
	
	private static ArrayList<String> findAround(int row, int col){
		
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
	
	private static void showGame(ArrayList<String>[][] game2D){
		for(int i = 0; i < game2D.length; i++){
			for(int j = 0; j < game2D[0].length; j++){
				System.out.print(game2D[i][j].toString() + " | ");
			}
			System.out.println();
		}
	}
	
}
