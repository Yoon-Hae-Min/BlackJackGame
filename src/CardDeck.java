import java.util.*;

/*public class CardDeck{
    public static void main(String[]args){
        Deck d = new Deck();
        d.SetDeck();
        d.printDeck();
    }
}*/

class Deck{
    final int EACH_PATTERN_CARD_NUM=13;
    private LinkedList<Card> Deck = new LinkedList<>();
    
    public Card DrawCard(){
        Card selectcard= SelectCard();
        Deck.remove(selectcard);
        return selectcard;
    }

    Card SelectCard(){
        int deckSize=Deck.size();
        int selectNum=(int)(Math.random()*deckSize);
        Card getCard = Deck.get(selectNum);
        return getCard;
    }

    void SetDeck(){
        for(String pattern : Card.PATTERNS){
            for(int i=1;i<=EACH_PATTERN_CARD_NUM;i++){
                Card card= new Card();
                card.SetPattern(pattern);
                card.SetNumber(NumbertoStr(i));
                Deck.add(card);
            }
        }
    }

    String NumbertoStr(int i){
        String number;
        if(i==1){
            number="A";
        }
        else if(i==11){
            number="J";
        }
        else if(i==12){
            number="Q";
        }
        else if(i==13){
            number="K";
        }
        else{
            number=String.valueOf(i);
        }
        return number;
    }

    void printDeck(){//test용
        for(Card c : Deck){
            System.out.println(c.pattern +" "+c.number);
        }
    }
}

class Card{
    static final String[] PATTERNS = {"spade", "heart", "diamond", "club"};
    String pattern;
    String number;
    
    void SetPattern(String pattern){
        this.pattern=pattern;
    }
    void SetNumber(String number){
        this.number=number;
    }
    static int strToNumber(String str){
        int number;
        if(str.equals("A")){
            number=1;
        }
        else if(str.equals("J")){
            number=11;
        }
        else if(str.equals("Q")){
            number=12;
        }
        else if(str.equals("K")){
            number=13;
        }
        else{
            number=Integer.parseInt(str);
        }
        return number;
    }

    static char PatternStrToPattern(String patt){
        if(patt==PATTERNS[0]){
            return '♠';
        }
        else if(patt==PATTERNS[1]){
            return '♥';
        }
        else if(patt==PATTERNS[2]){
            return '◆';
        }
        else if(patt==PATTERNS[3]){
            return '♣';
        }
        else{
            return 'E';
        }
    }
}
