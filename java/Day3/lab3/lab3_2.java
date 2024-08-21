class lab3_2
{
    public static void main(String args[]) {
       
       
        String str1 = "*";
        String str2 = "                    ";
        String str3 = "*";
        String str;

        for(int i=0; i < 10 ;i++)
        {
            str = str1 + str2 + str3;
            System.out.println(str);
            
            str1 = str1.concat("*");
            str2 = str2.substring(0,str2.length() - 2);
            str3 = str3.concat(" *");
            
        }

        

        }
    }
