# Black Jack

[TOC]



## **게임규칙**

나무위키 참고: https://namu.wiki/w/%EB%B8%94%EB%9E%99%EC%9E%AD(%EC%B9%B4%EB%93%9C%EA%B2%8C%EC%9E%84)

유튜브 참고:https://www.youtube.com/watch?v=2EPMwDotXY8

숫자의 합이 21에 가까운수를 만들면 이기고 21을 초과하면 지는 단순한 게임이다 게임구성은 플레이어한명과 딜러한명으로 게임을 진행한다 모든 기능을 구현하면 좋겠으나 제약이 있어 크고 기본적인 규칙들로만 구현을 하였다.



## **게임 진행**

1. 플레이어는 2장의 카드를 받는다
2. 플레이어는 얼마를 베팅할것인지 설정을 한다. (0을 선택하면 베팅을 안한다는 의미이므로 다음판으로 넘어감)
3. 플레이어는 카드를 뽑을 건지 이대로 진행할건지 선택한다. (카드 뽑기를 원치 않을때까지 3번을 반복한다)
4. 딜러가 카드를 2장 받는다 이때 카드의합이 17이하면 딜러는 17이상일때까지 카드를 뽑는다
5. 두명다 카드 뽑기가 끝났으면 카드를 오픈한다 두명중 카드의합이 21에 가까운 사람이 승리한다 단 무승부일때는 플레이어가 승리한 것으로 간주한다.
6. 플레이어가 승리시 베팅금액의 2배를 받고 패배시 베팅 금액을 모두 잃는다



## **객체의 주요 내용**

- Play: 게임이 실행되는 객체이다

- GameManagerSystem: 닌텐토 칩에서 생각나서 만들었다 BlackJack말고도 다른게임들을 추가하고 여기서 어떤 게임을 실행할지 선택해주는 객체이다.

- BlackJackGame: 블랙젝 게임의 진행이 담긴 객체이다 초기화, 게엠진행, 배팅금액처리 를 담당하고있다

- Bet: Player의 베팅 정보를 담고있다. 한판에서의 베팅금액, Player의 남은 베팅액을 관리한다

- Record: bet에 있는 베팅금액을 저장시키고 저장되어있는 기록을 불러주는 객체이다

- CardDeck: 게임에서 쓰일 덱의 객체이다

- User: 게임을할 사람들을 정의하는 객체이다 User객체가 있고 자식 클레스로 Player와 Dealer가 있다.

- UI: 기본적인 BalckJack게임에서 사용할 UI가 담겨져 있다

![schematic](C:\Users\MS\Desktop\heamin\BlackJack\readmepicture\schematic.png)

## **객체 주요 내용 prototype**

BlackJackGame.java

```java
static Deck deck; // 덱
static Dealer dealer; // 딜러
static Player player; // 플레이어
static User Loser=null; // 패배자
static Record record; // 저장
static void GameInit(); // 블랙젝 게임이 시작될때마다 필요한 인스턴스 초기화
static void BlackJackInit();// 최초 게임시작시 필요한 인스턴스 초기화
static Boolean isProgressPossible();// 게임이 진행 될수 베팅액이 있는지 확인
void SelectMenu();// 처음 시작화면에서 무엇을 고를지 선택
void PlayGame();//게임을 순서대로 진행
void WhoIsLoser();// 패배가 누군지 확인
void DealerTurn();// 딜러의 차례
void PlayerTrun();// 플레이어의 차례
void GameOver(User user);// 게임에서 졌다는 문구 출력및 패배 설정
Boolean isBust(User user);// 카드의 합이 21이 초과가 되었는지 확인
```

Bet.java

```java
static final int basicbet = 1000;// 게임시작시 주는 기본금
int betSum=0; // 베팅된 금앱
int money; // 플레이어 보유돈
void betting(); // 베팅금액 설정
void betresult(User user); // 베팅 결과 확인
```

CardDeck.java

```java
class Card// 카드 한장의 객체
    void SetPattern(String pattern); // 카드의 문양을 설정
    void SetNumber(String number); // 카드의 숫자를 설정
	static int strToNumber(String str); // 문자를 숫자로 변경 단 A,J,Q,K에 해당하면 바꿔서 return
    static char PatternStrToPattern(String patt); //spade, heart, diamond, club을 문양으로 변경
    
final int EACH_PATTERN_CARD_NUM=13;// A~K까지 카드의 종류 13장
private LinkedList<Card> Deck = new LinkedList<>(); // 덱
public Card DrawCard(); // 카드를 뽑아서 뽑은 카드를 return
Card SelectCard(); // 덱에서 카드 한장을 선택
void SetDeck(); //덱을 초기화함
String NumbertoStr(int i); // 숫자를 문자화 시킴 단 A,J,Q,K에 해당하면 바꿔서 return

```

User.java

```java
LinkedList<Card> card = new LinkedList<>(); // user가 가지고있는 카드
class User
    void AddCard(Card drawCard); // 카드를 추가
    void OpenCard(); // 가지고있는 카드를 공개
    void CardClear(); //가지고 있는 카드를 초기화 시킴
class Player extends User //playr 객체
	Bet bet = new Bet(); //베팅금액이 있으니 instance를 만듬
	public String toString(); //플레이어를 return
	Boolean DrowOption(); // 카드를 더뽑을지 안뽑을지 설정
class Dealer extends User //dealer 객체
    Boolean IsDrow() // 딜러의 카드 합이 17초과인지 체크
    public String toString()// dealer return

```

Record.java

```java
LinkedList<String> recordData = new LinkedList<>(); // 데이터를 받아와서 처리할때 담고있을 변수
final int ShowRecordNum=10; // 얼만큼의 데이터를 저장하고 보여줄건지
String EnterName();// 데이터 저장을 위해서 이름입력 받음
void SaveScoreRecord(int betCost);// 데이터를 선정된 위치에 저장
void SetRecordLocation(int betCost); // 데이터가 어디에 저장되야하는지 위치를 선정한다
static void GetScoreRecord(); // 저장된 데이터를 가져와서 보여준다
```



## **코드의 흐름**

1. Play객체에서 실행
2. GameManager에서 BlackJackGame을 실행
3. BlackJackGame에서 SelectMenu로 Menu선택
4. PlayGame으로 게임 실행
5. 게임에 참가할 user,record,deck의 instance 생성
6. 게임에 필요한 deck, loser를 세팅 dealer와 player의 카드를 모두제거
7. dealer와 player가 각각 카드를 2장씩 뽑음
8. playertrun에서 카드를 보여주고 베팅값을 설정
9. 베팅값이 0이면 loser가 자동으로 player가 됨
10. loser가 없고 player가 카드를 뽑는다하면 카드를 뽑고 21이 초과되었는지 체크 (반복)
11. 21이 넘었다면 loser가 player가 되고 패배
12. dealertrun에서 dealer가 카드를 뽑아야되는지 체크
13. 카드를 오픈하고 21이 초과되었는지 체크
14. 21이 넘었다면 loser가 dealer가 되고 패배
15. loser가 정해지지 않았다면 whoisloser 실행해서 loser를 찾음
16. player가 이겼다면 2배를 돌려주고 졌다면 베팅액을 삭제
17. player의 남은 잔액을 보여줌
18. 다시 게임할건지 저장할것인지 끝낼것인지 선택



Reference

https://jojoldu.tistory.com/62
