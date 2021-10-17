import java.util.*;

class Bet{
    Scanner sc = new Scanner(System.in);
    static final int basicbet = 1000;
    int betSum=0;
    int money;
    Bet(){
        money=basicbet;
    }

    void betting(){
        int betCost;
        Boolean IsValid=false;
        while(!IsValid){
            IsValid=true;
            System.out.print("How much will you bet?(0 is died): ");
            betCost=sc.nextInt();
            if(betCost==0){
                BlackJackGame.Loser=BlackJackGame.player;
            }
            else if(betCost>money){
                System.out.println("The bet is over");
                IsValid=false;
            }
            else{
                betSum=betCost;
                money-=betCost;
            }
        }
    }

    void betresult(User user){
        if(user!=BlackJackGame.player){
            money+=betSum*2;
        }
        betSum=0;
    }


}

