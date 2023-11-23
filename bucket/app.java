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
    public abstract int payCard();
    public abstract int CheckCardInfo();
}

class Card implements Cardable{
    final String card_number;
    String owner;
    String passwd;
    public int payCard(){
        return 0;
    }
    public int CheckCardInfo(){
        return 0;
    }
}
class CreditCard extends Card {
    public int payCard(){
        return 0;
    }
    public int CheckCardInfo(){
        return 0;
    }
}
class CheckCard extends Card {
    public int payCard(){
        return 0;
    }
    public int CheckCardInfo(){
        return 0;
    }
}
class MemberShipCard extends Card {
    public int payCard(){
        return 0;
    }
    public int CheckCardInfo(){
        return 0;
    }
}

class CardManager {
    Card[] mycard;
    CardManager(int limit_card){
        this.mycard = new Card[limit_card];
    }
}

public class app {
    public static void main(String[] args){

    }
}
