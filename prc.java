public class prc {
    public static void main(String[] args){
        String str1 = new String("layla");
        String str2 = "layla";
        // 메모리 위치가 달라 결과값아 다르다.
        System.out.printf("%s == %s : %b\n", str1, str2, (str1 == str2));

        String str3 = "Layla";
        String str4 = "Layla";
        System.out.printf("%s == %s : %b", str3, str4, (str3 == str4));
    }
}
