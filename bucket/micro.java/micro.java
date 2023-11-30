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

class Card implements Cardable {
    public int payCard(String payment_title, int payment){
        return 0;
    }
    // 카드 결제 메서드
    public int payTrans(int fee) {
        return 0;
    }
    public void printCardInfo(){
    }
    // 카드의 정보 출력
    public void printCardHistory(){
    }
}

class CreditCard extends Card {
}
class CheckCard extends Card {
}

class CardManager {

}

public class micro {
    public static void main(String[] args){

    }
}
