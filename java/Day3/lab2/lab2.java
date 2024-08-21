import java.util.*;

class IpCutter {
    public static void main(String args[]) {
        String Ip = " ";
        Ip = args[0];

       
        int startIndex = 0;
        int dotIndex = Ip.indexOf(".");

        System.out.println("Parsing string using substring() and indexOf()");

        while (dotIndex != -1) {
            System.out.println(Ip.substring(startIndex, dotIndex));
            startIndex = dotIndex + 1;
            dotIndex = Ip.indexOf(".", startIndex);
        }
       
        // Print the last part after the last dot
        System.out.println(Ip.substring(startIndex));
        
        //=======================================================

        System.out.println("Parsing IP using split() ");

        String[] PaesedIp = Ip.split("[.]");

        for (String iterator : PaesedIp)
            System.out.println(iterator);
        
        
        //==========================================================

        System.out.println("Parsing IP using StringTokenizer");
        StringTokenizer str = new StringTokenizer(Ip,".");
        while (str.hasMoreElements()) { 
            System.out.println(str.nextElement()); 
        }
    }
}

