import java.util.Scanner;


interface Cardable {
    public abstract int payCard(String payment_title, int payment); // 카드 결제 메서드
    public abstract int payTrans(int fee); // 교통카드 결제 메서드
    public abstract void printCardInfo(); // 카드의 정보를 출력하는 메서드
    public abstract void printCardHistory(); // 카드 사용 내역 출력 메서드
}
class UseHistoryEntity {
    String use_history_str; // 어디서 결제
    int use_history_int; // 얼마 결제했는지
    UseHistoryEntity(String use_history_str, int use_history_int){
        this.use_history_str = use_history_str;
        this.use_history_int = use_history_int;
    }
}
class Card implements Cardable {
    String card_nickname; // 카드 별칭
    private String card_number; // 카드 번호 - 정보은닉 및 캡슐화
    String passwd; // 카드 비밀번호
    String owner;// 카드 주인
    int total_amount_used; // 카드에서 사용한 금액들의 총 합계
    int payment_no; // 카드를 총 몇 번 결제 했는지.


    // 카드 이용 내역
    UseHistoryEntity[] history;


    //Card 클래스 초기화
    Card(String card_nickname, String card_number, String owner, String passwd) {
        this.card_nickname = card_nickname;
        this.card_number = card_number;
        this.owner = owner;
        this.passwd = passwd;


        this.total_amount_used = 0;
        this.payment_no = 0;


        this.history = new UseHistoryEntity[100];
    }
    String getCardNumber() { return this.card_number; }
    public int payCard(String payment_title, int payment) {return 0;}
    public int payTrans(int fee) {return 0;}
    public void printCardInfo() { }
    public void printCardHistory() { // 카드 종류와 상관없이 동일한 기능 -> 오버라이딩 X
        System.out.println("카드 정보 출력(내역) -----------------------------");
        for(int i=0;i<this.payment_no; i++) {
            System.out.printf("사용내역(%d번째) : [%s][%d원]\n",
            i+1,
            this.history[i].use_history_str,
            this.history[i].use_history_int);
        }
    }
}
class CreditCard extends Card {
    int limit_amount; // 한도 금액
    int annual_fee; // 연회비
    int total_trans_fee; // 총 교통카드 이용 금액


    CreditCard(String card_nickname, String card_number, String owner
    , String passwd, int limit_amount, int annual_fee) {
        super(card_nickname, card_number, owner, passwd);
        this.limit_amount = limit_amount;
        this.annual_fee = annual_fee;
        this.total_trans_fee = 0;
    }


    public int payCard(String payment_title, int payment) {
        if(this.total_amount_used + payment > this.limit_amount) {
            System.out.println("-> 한도 초과 입니다!");
        } else { // 한도 초과가 아닌 경우
            // 히스토리 내역 저장
            this.history[this.payment_no++] = new UseHistoryEntity(payment_title, payment);
            // 전체 금액 갱신
            this.total_amount_used += payment;
            System.out.printf("-> %s(%d원) - %s번 신용카드로 결제\n"
            , payment_title, payment, this.getCardNumber());
        }
        return 0;
    }
    public int payTrans(int fee) {
        this.total_trans_fee += fee;
        System.out.printf("-> 교통카드 %d원 사용! (누적:%d원)\n",fee,total_trans_fee);
        return 0;
    }
    public void printCardInfo() {
        System.out.println("신용카드 정보 출력 +++++++++++++++++++++++++");
        System.out.println("카드 소유주 : " + this.owner);
        System.out.println("카드 별칭 : " + this.card_nickname);
        System.out.println("카드 번호 : " + this.getCardNumber());
        System.out.println("카드 비밀번호 : " + this.passwd);
        System.out.println("카드 결제 금액 한도 : " + this.limit_amount);
        System.out.printf("카드 연회비/월연회비(A) : %d / %d\n", this.annual_fee, this.annual_fee/12 );
        System.out.println("카드 결제 대금(B) : " + this.total_amount_used);
        System.out.println("카드 교통 요금(C) : " + this.total_trans_fee);
        System.out.println("카드 총 결제 대금(A+B+C) : " + (this.annual_fee/12+this.total_amount_used+this.total_trans_fee));
    }
}
class CheckCard extends Card {
    int deposit; // 입금된 금액(잔액)
    int total_trans_fee; // 총 교통카드 이용 금액


    CheckCard(String card_nickname, String card_number, String owner
    , String passwd, int deposit) {
        super(card_nickname, card_number, owner, passwd);
        this.deposit = deposit;
        this.total_trans_fee = 0;
    }


    public int payCard(String payment_title, int payment) {
        if(payment > this.deposit) {
            System.out.println("-> 한도 초과 입니다!");
        } else { // 한도 초과가 아닌 경우
            // 히스토리 내역 저장
            this.history[this.payment_no++] = new UseHistoryEntity(payment_title, payment);
            // 전체 금액 갱신
            this.total_amount_used += payment;
            this.deposit -= payment; // 돈 복사 방지
            System.out.printf("-> %s(%d원) - %s번 체크카드로 결제\n"
            , payment_title, payment, this.getCardNumber());
        }
        return 0;
    }
    public int payTrans(int fee) {
        this.total_trans_fee += fee;
        System.out.printf("-> 교통카드 %d원 사용! (누적:%d원)\n",fee,total_trans_fee);
        return 0;
    }
    public void printCardInfo() {
        System.out.println("체크카드 정보 출력 +++++++++++++++++++++++++");
        System.out.println("카드 소유주 : " + this.owner);
        System.out.println("카드 별칭 : " + this.card_nickname);
        System.out.println("카드 번호 : " + this.getCardNumber());
        System.out.println("카드 비밀번호 : " + this.passwd);
        System.out.println("카드 잔액(예금액) : " + this.deposit);
        System.out.println("카드 교통 요금(A) : " + this.total_trans_fee);
        System.out.println("카드 총 결제 대금(A) : " + this.total_trans_fee);
    }
}
class CardManager {
    Card[] card;
    int card_count;
    CardManager(int limit_count) {
        this.card_count = 0;
        this.card = new Card[limit_count];
    }
    void createCard(int option) { // 0:신용카드 / 1:체크카드
        Scanner sc = new Scanner(System.in);
        String nickname, card_number, owner, passwd;
        int limit_amount, annual_fee, deposit;

        System.out.print("카드 이름 : "); nickname = sc.next();
        System.out.print("카드 번호 : "); card_number = sc.next();
        System.out.print("카드 주인 : "); owner = sc.next();
        System.out.print("카드 암호 : "); passwd = sc.next();
        
        if(option == 0){
            System.out.println("카드 한도 : "); limit_amount = sc.nextInt();
            System.out.println("카드 연회비 : "); annual_fee = sc.nextInt();
            this.card[this.card_count++] = new CreditCard(nickname, card_number, owner, passwd, limit_amount, annual_fee); 
            System.out.println("->카드 생성이 완료되었습니다.");
        } else if (option == 1){
            System.out.println("카드 잔약 : "); deposit = sc.nextInt();
            this.card[this.card_count++] = new CheckCard(nickname, card_number, owner, passwd, deposit); 
            System.out.println("->카드 생성이 완료되었습니다.");
        }
    }
    int searchCard(String card_number) {
        return 0;
    }
    void printCardInfo(int card_idx){
        return;
    }
    void buySomething(String item_title, int item_price, String card_number) {
        return;
    }
    void printMenu() {
        System.out.println("1. 카드 생성");
        System.out.println("2. 카드 조회");
        System.out.println("3. 카드 사용");
        System.out.println("4. 교통카드 사용");
        System.out.println("0. 종료");
        System.out.print("입력 : ");
    }
}
public class micro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CardManager cm = new CardManager(100);
        int choice = -1;


        while(true) {
            cm.printMenu();
            choice = sc.nextInt();


            switch(choice) {
                case 0:
                    return;
                case 1:
                    System.out.println("카드생성!");
                    break;
                case 2:
                    System.out.println("카드조회!");
                    break;
                case 3:
                    System.out.println("카드사용!");
                    break;
                case 4:
                    System.out.println("교통카드사용!");
                    break;
                default:
                    System.out.println("없는 메뉴입니다.");
                    break;
            }
        }
    }
}





