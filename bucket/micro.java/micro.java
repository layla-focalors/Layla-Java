/**
 * Cardable
 */
interface Cardable {
    int payCard(String payment_title, int payment); 
    // 카드 결제 메서드
    int payTrans(int fee);
    // 교통카드 결제 메서드
    void printCardInfo();
    // 카드의 정보 출력
    void printCardHistory();
    // 카드 사용 내역 출력 메서드
}

class Card implements Cardable {

}

class CreditCard extends Card {
}
class CheckCard extends Card {
}

public class micro {
    
}
