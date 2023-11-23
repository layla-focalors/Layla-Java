package bucket;

// require
// cardable Interface > default 설계도
// Card 클래스
// CreditCard 클래스
// CheckCard 클래스
// MemberShipCard 클래스
// 카드 비밀번호 및 주인 > 공통적인 부분 : card 클래스로 상속  * Extends Card

// CardManager 클래스

interface Cardable {
    int payCard();
    int CheckCardInfo();
}

class Card {
    final String card_number;
    String owner;
    String passwd;
}
class CreditCard {

}
class CheckCard {

}
class MemberShipCard {

}

public class app {
    public static void main(String[] args){

    }
}
