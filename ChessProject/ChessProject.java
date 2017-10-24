import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
  String turn = "White"; //for taking turns

	JPanel panels;
	JLabel pieces;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
	    panels.add(pieces);
    }


	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(((tmp1.contains("Black")))){
			oponent = true;
		}
		else{
			oponent = false;
		}
    if((tmp1.contains("BlackKing"))){  //checks if black king is taken white wins
      JOptionPane.showMessageDialog(null, "White Wins!");
      System.exit(0);
    }

		return oponent;
	}

  private Boolean checkBlackOponent( int newX, int newY ){
    Boolean oponent;
    Component c1 = chessBoard.findComponentAt(newX, newY);
    JLabel awaitingPiece = (JLabel)c1;
    String tmp1 = awaitingPiece.getIcon().toString();
    if(((tmp1.contains("White")))){

      oponent = true;
    }
    else{
      oponent = false;
    }
    if((tmp1.contains("WhiteKing"))){ //checks  if white king is taken black wins
      JOptionPane.showMessageDialog(null, "Black Wins!");
      System.exit(0);
    }
    return oponent;
  }


  private String getPiece(int x, int y){ // similar to the piece present method but instead of returning a boolean in is returing the name of the piece on the tile we want to check
  Component c = chessBoard.findComponentAt(x, y);
  if((c instanceof JLabel)){
    JLabel awaitingPiece = (JLabel)c;
    String tmp1 = awaitingPiece.getIcon().toString();
    return tmp1;
  }
  else{
    return "";
  }
}
private Boolean CloseKing(int x, int y){
  if(((piecePresent(x, y + 75)) && getPiece(x, y + 75).contains("King")) || //Checking the tile ahead while moving down the board
  ((piecePresent(x, y - 75)) && getPiece(x, y - 75).contains("King")) || //Checking the tile ahead while moving up the board
  ((piecePresent(x + 75, y)) && getPiece(x + 75, y).contains("King")) || //Checking the tile ahead while moving to the right
  ((piecePresent(x - 75, y)) && getPiece(x - 75, y).contains("King")) || //Checking the tile ahead while moving to the left
  ((piecePresent(x + 75, y + 75)) && getPiece(x + 75, y + 75).contains("King")) || //Checking the southwest path ahead
  ((piecePresent(x + 75, y - 75)) && getPiece(x + 75, y - 75).contains("King")) || // Checking the northwest path ahead
  ((piecePresent(x - 75, y + 75)) && getPiece(x - 75, y + 75).contains("King")) || // Checking the souteast path ahead
  ((piecePresent(x - 75, y - 75)) && getPiece(x - 75, y - 75).contains("King"))){ // checking the northeast path ahead
    return true; //if true we know the piece ahead of the next tile in each direction is a King
  }
  else{
    return false;
  }
}


	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		Boolean success =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;
    Boolean progression = false;





		/*
			The only piece that has been enabled to move is a White Pawn...but we should really have this is a separate
			method somewhere...how would this work.

			So a Pawn is able to move two squares forward one its first go but only one square after that.
			The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
			a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
			square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
			If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
			demonstration purposes the Pawn here turns into a Queen.
		*/
    int landingX = (e.getX()/75);
    int landingY = (e.getY()/75);
    int xMovement = Math.abs((e.getX()/75)-startX);
    int yMovement = Math.abs((e.getY()/75)-startY);
    System.out.println("----------------------------");
    System.out.println("The piece that is being moved is :"+ pieceName);
    System.out.println("The Starting coordinates ar :"+"("+startX+","+startY+")");
    System.out.println("The xmovement is :"+xMovement);
    System.out.println("The ymovement is :"+yMovement);
    System.out.println("The landing coordinates are :"+"("+landingX+","+landingY+")");
    System.out.println("----------------------------");
//prints out the movements on cmd

if (pieceName.contains(turn)){
//this varies the turn of white and Black

//Queen code begins
    if (pieceName.contains("Queen")) {
      		int newY = e.getY() / 75;//new e.get variable value Y
      		int newX = e.getX() / 75;//new e.get variable value X
      		boolean inTheWay = false;//if in the way do not move
      		int distance = Math.abs(startX - newX);//distance of starting position to where it landed
      		if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
//if its less than 0 or greater than 7 dont move
            validMove = false;
      		} else
      		{
      			validMove = true;
      		if (Math.abs(startX - landingX) == Math.abs(startY - landingY)) {
      			if ((startX - landingX < 0) && (startY - landingY < 0)) {
      				for (int i = 0; i < distance; i++) {
      					if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
      						inTheWay = true;
      					}
      				}
      			} else if ((startX - landingX < 0) && (startY - landingY > 0)) {
      				for (int i = 0; i < distance; i++) {
      					if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
      						inTheWay = true;
      					}
      				}
      			} else if ((startX - landingX > 0) && (startY - landingY > 0)) {
      				for (int i = 0; i < distance; i++) {
      					if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
      						inTheWay = true;
      					}
      				}
      			} else if ((startX - landingX > 0) && (startY - landingY < 0)) {
      				for (int i = 0; i < distance; i++) {
      					if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
      						inTheWay = true;
      					}
      				}
      			}
      				if (inTheWay) {
      					validMove = false;
      				} else {
      					if (piecePresent(e.getX(), (e.getY()))) {
      						if (pieceName.contains("White")) {
      							if (checkWhiteOponent(e.getX(), e.getY())) {
      								validMove = true;
      							} else {
      								validMove = false;
      							}
      						} else {
      							if (checkBlackOponent(e.getX(), e.getY())) {
      								validMove = true;
      							} else {
      								validMove = false;
      							}
      						}
      					} else {
      						validMove = true;
      					}
      				}
      			}
      			else if (((Math.abs(startX - landingX) !=0)&&(Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) ==0) && (Math.abs(landingY - startY) !=0))) {
      				if (Math.abs(startX - landingX) != 0) {

      					if (startX - landingX > 0) {

      						for (int i = 0; i < xMovement; i++) {
      							if (piecePresent(initialX - (i * 75), e.getY())) {
      								inTheWay = true;
      								break;
      							} else {
      								inTheWay = false;
      							}
      						}
      					} else {
      						for (int i = 0; i < xMovement; i++) {
      							if (piecePresent(initialX + (i * 75), e.getY())) {
      								inTheWay = true;
      								break;
      							} else {
      								inTheWay = false;
      							}
      						}
      					}
      				} else {

      					if (startY - newY > 0) {

      						for (int i = 0; i < yMovement; i++) {
      							if (piecePresent(e.getX(), initialY - (i * 75))) {
      								inTheWay = true;
      								break;
      							} else {
      								inTheWay = false;
      							}
      						}
      					} else {
      						for (int i = 0; i < yMovement; i++) {
      							if (piecePresent(e.getX(), initialY + (i * 75))) {
      								inTheWay = true;
      								break;
      							} else {
      								inTheWay = false;
      							}
      						}
      					}
      				}
      				if (inTheWay) {
      					validMove = false;
      				} else {
      					if (piecePresent(e.getX(), (e.getY()))) {
      						if (pieceName.contains("White")) {
      							if (checkWhiteOponent(e.getX(), e.getY())) {
      								validMove = true;
      							} else {
      								validMove = false;
      							}
      						} else {
      							if (checkBlackOponent(e.getX(), e.getY())){

      								validMove = true;
      							} else {
      								validMove = false;
      							}
      						}
      					} else {
      						validMove = true;
      					}
      				}
      				} else {
      				validMove = false;
      				}
      		}
    		}

//Queen ends
        if(pieceName.equals("BlackKing")){
                if(((landingX < 0) || (landingX > 7))||((landingX<0) || (landingY > 7))){
                  validMove = false;
                }

                else{

                  if((xMovement<=1) && (yMovement<=1)){//if movement is <=1 in any direction then ok
                    if(!CloseKing(e.getX(), e.getY())){
                    if(!piecePresent(e.getX(),e.getY())){//once no piece is on that square

                      validMove = true;

                    }
                    else{//if piece on square
                          if(checkBlackOponent(e.getX(), e.getY())){//check if its oponent piece

                           validMove = true;

                          }
                          else{
                            validMove = false;//no move
                          }
                        }
                    }
                  }

                  else{
                    validMove = false;
                  }
                }
              }
          //Black King
          if(pieceName.equals("WhiteKing")){
                if(((landingX < 0) || (landingX > 7))||((landingX<0) || (landingY > 7))){
                  validMove = false;
                }

                else{

                  if((xMovement<=1) && (yMovement<=1)){//if movement is <=1 in any direction then ok
                    if(!CloseKing(e.getX(), e.getY())){
                    if(!piecePresent(e.getX(),e.getY())){//once no piece is on that square

                      validMove = true;
                    }
                    else{//if piece on square
                          if(checkWhiteOponent(e.getX(), e.getY())){//check if its oponet piece

                           validMove = true;

                          }
                          else{
                            validMove = false;//no move
                          }
                        }
                    }
                  }

                  else{
                    validMove = false;
                  }
                }
              }
          //Black King

    if(pieceName.contains("Rook")){
         Boolean inTheWay = false;
         if(((landingX < 0) || (landingX > 7) || (landingY < 0) || (landingY > 7))){
           validMove = false;
         }
         else{
           if((((Math.abs(startX-landingX)!=0)&&(Math.abs(startY-landingY) == 0))||(Math.abs(startX-   //this checks that the piece is being put onto the chess board...if its not being put back onto the board its not valid
           landingX)==0)&&(Math.abs(landingY-startY)!=0)))
           {
             if(Math.abs(startX-landingX)!=0){
               int rMovement = Math.abs(startX-landingX);
               if(startX-landingX > 0){
                 for(int i=0;i < xMovement;i++){  //this is for the rook moving left to right or right to left. it also checks that there is no pieces in the way
                   if(piecePresent(initialX-(i*75), e.getY())){
                     inTheWay = true;
                     break;
                   }
                   else{
                     inTheWay = false;
                   }
                 }
               }
               else{
                 for(int i=0;i < xMovement;i++){  //this is for the rook moving left to right or right to left. it also checks that there is no pieces in the way
                   if(piecePresent(initialX+(i*75), e.getY())){
                     inTheWay = true;
                     break;
                   }
                   else{
                     inTheWay = false;
                   }
                 }
               }
             }
             else{
               int rMovement = Math.abs(startY-landingY);
               if(startY-landingY > 0){
                 for(int i=0;i < yMovement;i++){  //this is for up and down movement and checks that no pieces in the way...
                   if(piecePresent(e.getX(),initialY-(i*75))){
                     inTheWay = true;
                     break;
                   }
                   else{
                     inTheWay = false;
                   }
                 }
               }
               else{
                 for(int i=0;i < yMovement;i++){  //this is for up and down movement and checks that no pieces in the way...
                   if(piecePresent(e.getX(),initialY+(i*75))){
                     inTheWay = true;
                     break;
                   }
                   else{
                     inTheWay = false;
                   }
                 }
               }
             }

             if(inTheWay){
               validMove = false;
             }
             else{
               if(piecePresent(e.getX(), (e.getY()))){  //the rook can take an oponent and it is a valid move....
                 if(pieceName.contains("White")){
                   if(checkWhiteOponent(e.getX(), e.getY())){
                     validMove = true;
                   }
                   else{
                     validMove = false;
                   }
                 }
                 else{
                   if(checkBlackOponent(e.getX(), e.getY())){ //the rook can take an oponent and it is a valid move....
                     validMove = true;
                   }
                   else{
                     validMove = false;
                   }
                 }
               }
               else{
                 validMove = true;
               }
             }
         }
         else{
           validMove = false;
         }
       }
     }



if(pieceName.contains("Bishup")){
  Boolean inTheWay = false;
  int distance = Math.abs(startX-landingX);
  if(((landingX < 0) || (landingX > 7)) || ((landingY < 0)||(landingY > 7))){
    validMove = false;
  }
  else{
    validMove = true;
    if(Math.abs(startX-landingX)==Math.abs(startY-landingY)){
      if((startX-landingX < 0)&&(startY-landingY < 0)){
        for(int i=0; i < distance; i++){
          if(piecePresent((initialX+(i*75)),(initialY+(i*75)))){
            inTheWay = true;
          }
        }
      }
      else if((startX-landingX < 0)&&(startY-landingY > 0)){
          for(int i=0; i < distance; i++){
            if(piecePresent((initialX+(i*75)),(initialY-(i*75)))){
              inTheWay = true;
      }
    }
  }
      else if((startX-landingX > 0)&&(startY-landingY > 0)){
          for(int i=0; i < distance; i++){
            if(piecePresent((initialX-(i*75)),(initialY-(i*75)))){
              inTheWay = true;
      }
    }
  }
  else if((startX-landingX > 0)&&(startY-landingY < 0)){
      for(int i=0; i < distance; i++){
        if(piecePresent((initialX-(i*75)),(initialY+(i*75)))){
          inTheWay = true;
        }
      }
    }
    if(inTheWay){
      validMove = false;
    }
    else{
      if(piecePresent(e.getX(), (e.getY()))) {
        if(pieceName.contains("White")){
          if (checkWhiteOponent(e.getX(), e.getY())){
            validMove = true;
          }
          else{
            validMove = false;
          }
        }
        else{
          if(checkBlackOponent(e.getX(), e.getY())){
            validMove = true;
          }
          else{
            validMove = false;
          }
        }
      }
      else{
        validMove = true;
      }
    }
  }
  else{
    validMove = false;
  }
}
  }

   if(pieceName.contains("Knight")){
            if(((landingX < 0)||(landingX > 7))||((landingY < 0 )|| landingY > 7)){
              validMove = false;
            }
            else{
              if(((landingX == startX+1) && (landingY == startY+2))||((landingX == startX-1) && (landingY == startY+2))||((landingX == startX+2) && (landingY ==
              startY+1))||((landingX == startX-2) && (landingY ==
               startY+1))||((landingX == startX+1) && (landingY == startY-2))||((landingX == startX-1) && (landingY == startY-2))||((landingX == startX+2) && (landingY == startY-1))||((landingX == startX-2) && (landingY == startY-1))){
                 if(piecePresent(e.getX(), (e.getY()))){
                   if(pieceName.contains("White")){
                     if(checkWhiteOponent(e.getX(),e.getY())){
                       validMove = true;
                     }
                     else{
                       validMove = false;
                     }
                   }
                   else{
                     if(checkBlackOponent(e.getX(), e.getY())){
                       validMove = true;
                     }

                   else{
                     validMove=false;
                   }
                 }

               }
               else{
                 validMove = true;
               }

            }
            else{
              validMove = false;
            }

          }
        }


    else if(pieceName.equals("BlackPawn")){
      if((startY == 6) && (startX == landingX)&&(((startY-landingY)==1) || (startY-landingY)== 2)){
        if(!piecePresent(e.getX(), e.getY())){
          validMove = true;
        }
        else{
          validMove = false;
        }
      }



else if((Math.abs(startX-landingX)==1)&&(((startY-landingY)== 1))){
  if(piecePresent(e.getX(), e.getY())){
    if(checkBlackOponent(e.getX(), e.getY())){
      validMove = true;
      if(landingY == 0){
        progression = true;


}
}

        else{
          validMove = false;
        }
      }
      else{
        validMove = false;      }
    }
  else if ((startY != 6)&&((startX == landingX)&&(((startY-landingY)==1)))){
    if(!piecePresent(e.getX(), e.getY())){
      validMove = true;
      if(landingY ==0){
        progression = true;
      }


}

    else{

      validMove = false;
    }
}
else{
  validMove = false;
}
}



	if(pieceName.equals("WhitePawn")){
			if(startY == 1)
			{
				if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2))
				{
					if((((e.getY()/75)-startY)==2)){
						if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()-75)))){
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
					else{
						if((!piecePresent(e.getX(), (e.getY()))))
						{
							validMove = true;
						}
						else{
							validMove = false;
						}
					}
				}
      }
				else if((yMovement == 1)&&(startY < landingY) && (xMovement == 1)){
          if(piecePresent(e.getX(), e.getY())){
            if(checkWhiteOponent(e.getX(), e.getY())){
              validMove = true;
            }
          }
			}
			else{
				int newY = e.getY()/75;
				int newX = e.getX()/75;
				if((startX-1 >=0)||(startX +1 <=7))
				{
					if((!piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
					{
						if(checkWhiteOponent(e.getX(), e.getY())){
							validMove = true;
							if(startY == 6){
								success = true;
							}
						}
						else{
							validMove = false;
						}
					}
					else{
						if(!piecePresent(e.getX(), (e.getY()))){
							if((startX == (e.getX()/75))&&((e.getY()/75)-startY)==1){
								if(startY == 6){
									success = true;
								}
								validMove = true;
							}
							else{
								validMove = false;
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else{
					validMove = false;
				}
			}
		}
  }
		if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}
		else{
			if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png" ) );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
        if (success = true){
          progression = false;
        }
			}
else if(progression){
  int location = 0 + (e.getX()/75);
  if (c instanceof JLabel){
          Container parent = c.getParent();
          parent.remove(0);
    pieces = new JLabel( new ImageIcon("BlackQueen.png" ) );
    parent = (JPanel)chessBoard.getComponent(location);
      parent.add(pieces);
  }
  else{
    Container parent = (Container)c;
          pieces = new JLabel( new ImageIcon("BlackQueen.png") );
    parent = (JPanel)chessBoard.getComponent(location);
      parent.add(pieces);
  }
  if (progression = true){
    success = false;
  }
}

			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
	    		chessPiece.setVisible(true);
			}
		}
    if(validMove){
      if(turn.equals("White")){


      turn = "Black";
    }
    else{
      turn = "White";
    }
  }


    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
