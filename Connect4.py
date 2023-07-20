Row=0
def BoardClear(Board):
    col, row = (6, 7)
    Board =  [[ "*" for x in range(col)] for y in range (row)]
    
    return Board
def printBoard(Board):
    for x in range(0,6):
        print(Board[x])

def Gravity(Board,Col):
    count=0
    temp=Board[0][Col]
    
    for x in range(1,6):
       
        if (Board[x][Col] == '*'):
            count+=1
        else :
            
            break
    Board[0][Col]='*'
    Board[count][Col]=temp
   
    Row=count
            
    
    return Board,count


def CheckVert(Board,row,Col):
    Check=1
    
    for x in range(1,4):
        if(Board[row+x][Col] == Board[row][Col]) and (row+x <=5):
            print(Board[row+x][Col])
            Check+=1
        else:
            break
    return Check
    

def CheckHor(Board,row,Col):
    Check=1 
    for x in range(1,4):
        if  (Col+x <=5) and  (Board[row][Col+x] == Board[row][Col]) :
            print(Board[row+x][Col])
            Check+=1
        else:
            break
    for x in range(1,4):
        if(Board[row][Col-x] == Board[row][Col]) and (Col-x >=0):
            print(Board[row][Col-x])
            Check+=1
        else:
            break
    return Check

def CheckDiag1(Board,row,Col):
    Check=1 
    for x in range(1,4):
        if(Board[row+x][Col+x] == Board[row][Col]) and (Col+x <=5) and (row+x<=5):
           
            Check+=1
        else:
            break
    for x in range(1,4):
        if(Board[row-x][Col-x] == Board[row][Col]) and (Col-x >=0) and (row-x >=0):
            Check+=1
        else:
            break
    return Check

def CheckDiag2(Board,row,Col):
    Check=1 
    for x in range(1,4):
        if(Board[row+x][Col-x] == Board[row][Col]) and (Col-x >=0) and (row+x<=5):
            print(Board[row+x][Col-x])
            Check+=1
        else:
            break
   
    for x in range(1,4):
        if(Board[row-x][Col+x] == Board[row][Col]) and (Col+x >=5) and (row-x >=0):
            print(Board[row-x][Col+x])
            Check+=1
        else:
            break
   
    return Check


GameBoard = [[]]
GameBoard=BoardClear(GameBoard)
Count=1 



while True:
    Count+=1
    print("Choose a Column")
    if Count%2==1:
        Player='R'
    else:
        Player = 'Y'
    Choice= int(input())
    if(GameBoard[0][Choice-1] != '*'):
        print("Choose a different Column, this one is full")
    elif (Choice >=1) & (Choice <=6):
        GameBoard[0][Choice-1]=Player
        GameBoard,Row= Gravity(GameBoard,Choice-1)
       
        VScore=CheckVert(GameBoard,Row,Choice-1)
        print("Vscore:" + str(VScore))
        if(VScore==4):
            print("Congratulations to the "+ Player )
            printBoard(GameBoard)
            break
        
        
        HScore=CheckHor(GameBoard,Row,Choice-1)
        print("Hscore"+ str(HScore) )
        if(HScore==4):
            print("Congratulations to the "+ Player )
            printBoard(GameBoard)
            break
    
        D1Score=CheckDiag1(GameBoard,Row,Choice-1)
        print("D1score"+ str(D1Score) )
        if(D1Score==4):
            print("Congratulations to the "+ Player )
            printBoard(GameBoard)
            break

        D2Score=CheckDiag2(GameBoard,Row,Choice-1)
        print("D2score"+ str(D2Score) )
        if(D2Score==4):
            print("Congratulations to the "+ Player )
            printBoard(GameBoard)
            break

    elif Choice>6 :
        break
    printBoard(GameBoard)



    
