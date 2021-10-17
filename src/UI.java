class UI {
    static public void StartUI(){
        System.out.println("========================");
        System.out.println("=======Black Jack=======");
        System.out.println("========================");
        System.out.println("========================");
        System.out.println("========================");
        System.out.println("======1. GameStart======");
        System.out.println("======2. Rank     ======");
        System.out.println("======3. exit     ======");
        System.out.println("========================");
        System.out.println("========================");
        System.out.println("========================");
    }

    static public void CardSelectUI(){
        System.out.println("======1. DrawCard ======");
        System.out.println("======2. Stay     ======");
    }

    static public void RankUI(){
        System.out.println("========================");
        System.out.println("=========Record=========");
        Record.GetScoreRecord();
        System.out.println("========================");
        System.out.println("========================");
    }

    static void DrawUI(){
        System.out.println("1. draw     2. stay");
    }

    static void RegameUI(){
        System.out.print("Press 1 to ContinueGame or Press 2 to Record your score or another key to ExitGame");
    }
    
    static void ShowScore(String name,int bet){
        System.out.println("==========Score=========");
        System.out.print(name+": "+bet+"\n");
    }

}
