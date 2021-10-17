import java.util.*;

public class BlackJackGame {

    static Scanner sc = new Scanner(System.in);
    static Deck deck;
    static Dealer dealer;
    static Player player;
    static User Loser=null;
    static Record record;

    static void GameInit(){
        deck.SetDeck();
        Loser = null;
        dealer.CardClear();
        player.CardClear();
        dealer.AddCard(deck.DrawCard());
        dealer.AddCard(deck.DrawCard());
        player.AddCard(deck.DrawCard());
        player.AddCard(deck.DrawCard());
    }

    static void BlackJackInit(){
        deck = new Deck();
        dealer = new Dealer();
        player = new Player();
        record = new Record();
    }

    static Boolean isProgressPossible(){
        if(player.bet.money<=0){
            System.out.println("You haven't money get out!!");
            return false;
        }
        return true;
    }


    void SelectMenu(){
        int selectNum=sc.nextInt();
        switch(selectNum){
            case 1:
                PlayGame();
                break;
            case 2:
                UI.RankUI();
                break;
            case 3:
                break;
            }
    }

    void PlayGame(){
        BlackJackInit();
        int re=1;
        while(re==1){
            if(!isProgressPossible()) break;
            GameInit();
            PlayerTrun();
            DealerTurn();
            WhoIsLoser();
            player.bet.betresult(Loser);
            UI.ShowScore(player.toString(),player.bet.money);
            UI.RegameUI();
            re = sc.nextInt();
        }
        if(re==2)record.SaveScoreRecord(player.bet.money);
    }

    void WhoIsLoser(){
        if(Loser==null){
            if(dealer.cardSum>player.cardSum){
                GameOver(player);
            }
            else{
                GameOver(dealer);
            }
        }
        else{
            return;
        }
    }

    void DealerTurn(){
        if(Loser==null){
            System.out.println("\n=======DealerTurn=======");
            while(dealer.IsDrow()){
                dealer.AddCard(deck.DrawCard());
            }
            dealer.OpenCard();
            if(isBust(dealer)){
                GameOver(dealer);
            }
        }
    }

    void PlayerTrun(){
        System.out.println("\n=======PlayerTurn=======");
        player.OpenCard();
        player.bet.betting();
        while(Loser==null&&player.DrowOption()){
            player.AddCard(deck.DrawCard());
            player.OpenCard();
            if(isBust(player)){
                GameOver(player);
                break;
            }
        }

    }
    
    void GameOver(User user){
        System.out.print(user.toString()+" is Lose"+"\n");
        Loser = user;
    }

    Boolean isBust(User user){
        if(user.cardSum>21) return true;
        else return false;
    }

}

