import java.util.*;

class User{
    LinkedList<Card> card = new LinkedList<>();
    int cardSum=0;

    void AddCard(Card drawCard){
        this.card.add(drawCard);
        cardSum+=Card.strToNumber(drawCard.number);
    }

    void OpenCard(){
        Iterator<Card> iterator = card.iterator();
        Card now;
        while(iterator.hasNext()){
            now= iterator.next();
            System.out.print(Card.PatternStrToPattern(now.pattern)+" " +now.number +"  " );
        }
        System.out.print("총합: "+cardSum);
        System.out.println();
    }

    void CardClear(){
        cardSum=0;
        while(!card.isEmpty()){
            card.pop();
        }
    }

}

class Player extends User{
    Bet bet = new Bet();
    
    public String toString(){
        return "Player";
    }

    Boolean DrowOption(){
        Scanner sc = new Scanner(System.in);
        UI.DrawUI();
        int drawAnswer = sc.nextInt();
        while(true){
            switch(drawAnswer){
                case 1:
                    return true;
                case 2:
                    return false;
                default:
                    System.out.println("error insert 1 or 2");
            }
            UI.DrawUI();
            drawAnswer = sc.nextInt();
        }
    }
}

class Dealer extends User{

    Boolean IsDrow(){
        if(cardSum>16) return false;
        else return true;
    }

    public String toString(){
        return "Dealer";
    }
    

}