import java.util.Scanner;	
public class Board {
		final String Black = "B";
		final String White = "W";
		int turnCounter= 0;
		private String player;
		private String[] Board;  
		private String [] Bar;    
		private String [] OFF ;
		Scanner in = new Scanner(System.in); 
		
		public Board(){
			Board = new String [24];  
			Bar =  new String[3];    
			OFF =  new String[3];
		}
		
		public void DisplayBoard(){
			System.out.println();
			displayNumbers(13,25);       //Displays the board numbers
			System.out.println();
			for(int i =12 ; i <Board.length ; i++){
					if(i==18){
						System.out.print("\t"+Bar[0]+"\t\t");
					}
					System.out.print(Board[i]+"\t");
					if(i==23){
						System.out.print(OFF[0]);
					}
			}
			System.out.println();
			for(int i = 11;i>=0;i--){
				if(i==5){
					System.out.print("\t"+Bar[1]+"\t\t");
				}
				System.out.print(Board[i]+"\t");
				if(i==0){
					System.out.print(OFF[1]);
				}
			}
			System.out.println();
			displayNumbers(12,1);	
			System.out.println();
		}
			
	
		public void displayNumbers(int initial,int limit){
			if( limit == 25 ){
				for(int i = initial; i < limit ;i++){
					if(i==19){
							System.out.print("\tBAR\t\t");
					}
					System.out.print(i+"\t");
					if(i==24){
							System.out.print("OFF");
					}
				}
			}
			else{
				for(int i = initial ; i >= limit ; i--){
					if( i < 10 ){
						if( i == 6 ){
							System.out.print("\tBAR\t\t");
						}
						System.out.print("0"+i+"\t");
						if( i == 1 ){
								System.out.print("OFF\t");
						}
					}
					else System.out.print(i+"\t");
				}
			}
		}

		public void initialiseBoard(){
			for( int k = 0; k < Bar.length ; k++ ){
				Bar[k]="BAR";
				OFF[k]="";
			}
			for( int i = 0; i < Board.length ; i++ ){	
				if(i==0){
					Board[i] = "W2";
				}
	        	else if(i==5){
	        		Board[i]="B5";
	        	}
	        	else if(i==7){
	    			Board[i]="B3";
	    		}
	        	else if(i==11){
	    			Board[i]="W5";
	    		}	
	        	else if(i==12){
		        	Board[i]= "B5"; 
		        }
	    		else if(i==16){
	    			Board[i]="W3";
	    		}
	    		else if(i==18){
	        		Board[i]="W5";
	        	}
        		else if(i==23){
	        		Board[i]="B2";
	        	}
        		else Board[i] = "";
			}
			DisplayBoard();
		}
		
		
		public void ResetBoard(){
			initialiseBoard();
			turnCounter = 0 ;
		}
		
		
		public void playerDetermine(int one,int two){  //determines who plays first
		/*	if( turnCounter  ==  0  ){
				if(  one > two ){
				player =  Black;
				}
				else player =  White;
			}*/
			if( turnCounter  ==  0  ){
				player = Black;
			}
			else if(  turnCounter > 1  ){
				if(  player.equals( Black )  ){
					player = White;
				}
				else player = Black;
			}
			
			
			System.out.print("\nPlayer = "+player+"\n");
		}

		public void makeMove(int diceOnevalue,int diceTwovalue ){
			playerDetermine(diceOnevalue,diceTwovalue);
			if( turnCounter  !=  0  ){
				//moveChecker(player,fromPosition,toPosition,diceOnevalue);
				//moveChecker(player,fromPosition,toPosition,diceTwovalue);
				userMove(diceOnevalue);
				userMove(diceTwovalue);
			}
			turnCounter++;
		}
		
		public void userMove(int diceVal){
		 	System.out.println("\nEnter position to move:");
			int fromPosition =  in.nextInt();
			System.out.println("Enter position you want to move to:");
			int toPosition = in.nextInt();
			moveChecker(player,fromPosition,toPosition,diceVal);
		}
		
		public boolean moveChecker(String player, int from, int to,int diceValue){	
			boolean validPosition =  false;
			int move = 0;
			if( player == Black ){
				move = from - to ;
				if( move == diceValue ){
					 validPosition = checkPosition(from,to,player);
				}
			}
			else if ( player == White ){
				move = to - from ;
				if( move  ==  diceValue ){
					validPosition = checkPosition( from , to , player );
				}
			}
			return validPosition;
		}
		
		public boolean checkPosition(int fromPosition,int toPosition,String player){
			if  (  Board[ fromPosition  -  1 ].contains( player ) )    {
				if (  (toPosition-1)==24){
					addTo( fromPosition, toPosition , player );
					return true;
				}
				else if(Board[ toPosition - 1 ].contains( player )  ||   Board[ toPosition - 1 ] == "" ||   Board[ toPosition - 1 ].contains("1")  ){
						addTo( fromPosition, toPosition , player );
						return true;
				}
				else {
					System.out.println("You can't move here.\n"+ player);
					return false ;
				}
			}
			else {
				System.out.println(Board[fromPosition-1]+"You can't  move this checker.\n"+player);
				return false;
			}
		}
		
		public void addTo( int from,  int to, String player ){
			if( player == Black && (to - 1 )!= 24 ){
					int moveBy = from - to;
					remove( from , to , player ) ;
					if( Board[ (from-1)  -  moveBy ] ==  ""  )	{
								Board[ (from-1)  -  moveBy ]  =   player + 1 ;
					}
					else if (  Board[ to - 1 ].contains( "1" )   &&    Board[ to - 1 ].contains( White )   ){
					        if ( !Bar[0].equalsIgnoreCase( "BAR" )   ){
					        	String temp = Bar[0].substring(1)  ;
								int i   =  Integer.parseInt(  temp  ) ;
								i++;
								Bar[0] = White + i;
								Board[ (from-1) -  moveBy ]  =  player+1 ;
					        }
					        else {
					        	Bar[0]  =  White + 1;
					        	Board[ (from-1) - moveBy ]  =  player + 1 ;
					        }
					}
					else {
						String temp = Board[ ( from - 1 ) - moveBy ].substring(1);
						int i = Integer.parseInt(temp);
						i++;
						System.out.print(temp+"\n");
						Board[ ( from - 1 ) - moveBy ] =  player +  i;
					}
			}
			
			else if(  player  ==  White  && (to-1)!=24 ) {
				int moveBy =  to - from ;
				remove( from , to , player );  
				if( Board[ ( from - 1 ) + moveBy ] == ""){
					Board[ ( from - 1 ) + moveBy ] = player + 1 ;
				}
				else if( Board[ to - 1 ].contains("1")    &&    Board[ to - 1 ].contains(Black)   ){
					
					if(  !Bar[0].equalsIgnoreCase("BAR")  ){
						String temp =  Bar[0].substring(1);
						int i = Integer.parseInt(temp);
						i++;
						Bar[0] = Black + i ;
						Board[ ( from - 1 ) +  moveBy] = player + 1 ;
					}
					else {
						Bar[0] = Black + 1 ;
						Board[ ( from - 1 ) + moveBy ] = player + 1 ;
					}
				}
				else {
					String temp = Board[ ( from - 1 ) + moveBy ].substring(1);
					int i = Integer.parseInt(temp);
					i++;
					System.out.print(temp+"\n");
					Board[  ( from - 1)  +  moveBy ] = player +  i ;
				}
			}
			else if((to-1)==24){
				if(player==Black){
					remove(from,to,player);
					if(OFF[1]!=""){
						String temp = OFF[1].substring(1);
						int i = Integer.parseInt(temp);
						if(i>0){
							i++;
						}
						OFF[1] = player + i;
					}
					else OFF[1] = player + 1;
				}
				else if(player==White){
					remove(from,to,player);
					if(OFF[0]!=""){
						String temp = OFF[0].substring(1);
						int i = Integer.parseInt(temp);
						if(i>0){
							i++;
						}
						OFF[0] = player + i;
					}
					else OFF[0] = player + 1;
					
				}
			}
		}
		
		public void remove(int from,int to,String player){
			String temp = Board[ ( from - 1 ) ].substring(1);
			int  i = Integer.parseInt(temp);
			 i--;
			 if(  i == 0 ){
				
				 Board[ ( from - 1 ) ] = "";
			 }
			 else Board[ ( from - 1 ) ] = player + i ;
		}
}
		
	
	

