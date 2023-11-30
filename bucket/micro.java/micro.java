import java.util.Scanner;
import java.util.*;
/**
 * Cardable
 */
interface Cardable {
    public abstract int payCard(String payment_title, int payment); 
    // 카드 결제 메서드
    public abstract int payTrans(int fee);
    // 교통카드 결제 메서드
    public abstract void printCardInfo();
    // 카드의 정보 출력
    public abstract void printCardHistory();
    // 카드 사용 내역 출력 메서드
}

class UseHistoryEntity {
    String use_history_str;
    int use_history_int;
    UseHistoryEntity(String use_history_str, int use_history_int){
        this.use_history_int = use_history_int;
        this.use_history_str = use_history_str;
    }
}

class Card implements Cardable {
    String card_nickname; 
    // 카드 별칭
    private String card_number;
    // 카드 번호
    String Owner;
    // 카드 주인
    String passwd;
    int total_amount_used;
    // 카드에서 사용한 금액의 총합
    int payment_no;
    // 카드를 몇번 결제했는지

    String use_history_str;
    // 결제 이용 지역
    int use_history_int;
    // 결제 금액

    UseHistoryEntity[] history;
    // 카드 이용 내역

    Card(String card_nickname, String card_number, String owner, String passwd){
        this.card_nickname = card_nickname;
        this.card_number = card_number;
        this.Owner = owner;
        this.passwd = passwd;

        this.total_amount_used = 0;
        this.payment_no = 0;
        
        this.history = new UseHistoryEntity[100];
    }

    String GetCardNumber(){
        return this.card_number;
    }


    public int payCard(String payment_title, int payment){return 0;}
    // 카드 결제 메서드
    public int payTrans(int fee) {
        return 0;
    }
    public void printCardInfo(){
    }
    // 카드의 정보 출력
    public void printCardHistory(){
        System.out.println("------------ 카드 정보 출력 내용 ------------");
        for(int i = 0; i < this.payment_no; i++){
            System.out.printf("사용내역(%d번째) : [%s][%d원]\n",
            i + 1,
            this.history[i].use_history_str,
            this.history[i].use_history_int
            );
        }
    }
}

class CreditCard extends Card {
    int limit_amount;
    // 한도 금액
    int annual_fee;
    // 연회비
    int total_trans_fee;
    // 교통카드 비용

    CreditCard(String card_nickname, String card_number, String owner, String passwd, int limit_amount, int annual_fee) {
        super(card_nickname, card_number, owner, passwd);
        this.limit_amount = limit_amount;
        this.annual_fee = annual_fee;
        this.total_trans_fee = 0;
    }
    public int payCard(String payment_title, int payment){
        if(this.total_amount_used + payment > this.limit_amount){
            // 분기 : 1-0-001b-192-card-2A-CP : 한도 초과인 경우
            System.out.println("한도 초과입니다.");
        }else {
            // 분기 : 0-1-000b-129-card-1A : 한도 초과가 아닌 경우
            this.history[this.payment_no] = new UseHistoryEntity(payment_title, payment);
            this.payment_no ++;

            // 전체 금액 갱신
            this.total_amount_used += payment;
            System.out.printf("-> %s(%d원) - %s번 신용카드로 결제\n",
            payment_title,
            payment,
            this.GetCardNumber()
            );
        }
        return 0;
    }
    // 카드 결제 메서드
    public int payTrans(int fee) {
        this.total_trans_fee += fee;
        System.out.printf("-> 교통카드 %d원 사용 ( 누적 : %d원 )\n", fee, this.total_trans_fee);
        return 0;
    }
    public void printCardInfo(){
    }
    // 카드의 정보 출력
}
class CheckCard extends Card {
    int deposit;
    // 계좌 잔액
    int total_trans_fee;
    // 교통카드 비용

    CheckCard(String card_nickname, String card_number, String owner, String passwd, int deposit) {
        super(card_nickname, card_number, owner, passwd);
        this.deposit = deposit;
        this.total_trans_fee = 0;
    }

    public int payCard(String payment_title, int payment){
        if(this.total_amount_used + payment > this.deposit){
            // 분기 : 1-0-001b-192-card-2A-CP : 한도 초과인 경우
            System.out.println("한도 초과입니다.");
        } else {
            // 분기 : 0-1-000b-129-card-1A : 한도 초과가 아닌 경우
            this.history[this.payment_no] = new UseHistoryEntity(payment_title, payment);
            this.payment_no++;

            // 전체 금액 갱신
            this.total_amount_used += payment;
            this.deposit -= payment;
            System.out.printf("-> %s(%d원) - %s번 체크카드로 결제\n",
                payment_title,
                payment,
                this.GetCardNumber()
            );
        }
        return 0;
    }
}

class CardManager {
    Card[] card;

    int card_count;
    CardManager(int limit_count){
        this.card_count = 0;
        this.card = new Card[limit_count];
    }
    void CreateCard(int option){
        // 0신용카드 , 1체크카드
        
    }
    int searchCard(String Card_number){
        return 0;
    }
    void printCardInfo(int card_idx){
        return;
    }
    void buySomething(String item_title, int item_price, String Card_number){
        return;
    }
    void printMenu(){
        System.out.println("1. 카드 생성");
        System.out.println("2. 카드 조회");
        System.out.println("3. 카드 사용");
        System.out.println("4. 교통카드 사용");
        System.out.println("0. 종료");
        System.out.print("입력 : ");
    }
}

public class micro {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        CardManager cm = new CardManager(100);
        int choice = -1;
        while(true){
            cm.printMenu();
            choice = sc.nextInt();
            switch(choice){
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("카드 생성");
                }
                case 2 -> {
                    System.out.println("카드 조회");
                }
                case 3 -> {
                    System.out.println("카드 사용");
                }
                case 4 -> {
                    System.out.println("교통카드 사용");
                }
                default -> {
                    System.out.println("알 수 없는 값입니다.");
                }
            }
        }
    }
}
