class Calculator {
    public static void main(String args[]) {
        int result = 0;
        
        if(args.length !=3)
        {
        
        	System.out.println("Invalid expression ");
        	return;
        }

        switch (args[1].charAt(0)) {
            case '+':
                result = Integer.valueOf(args[0]) + Integer.valueOf(args[2]);
                break;
            case '-':
                result = Integer.valueOf(args[0]) - Integer.valueOf(args[2]);
                break;
            case 'x':
                result = Integer.valueOf(args[0]) * Integer.valueOf(args[2]);
                break;
            case '/':
                if (Integer.valueOf(args[2]) != 0) {
                    result = Integer.valueOf(args[0]) / Integer.valueOf(args[2]);
                } else {
                    System.out.println("Division by zero is not allowed");
                    return;
                }
                break;
            default:
                System.out.println("Invalid operator. Please use +, -, x, or /.");
                return; 
        }

        System.out.println("Result = " + result);
    }
}


//substring and indexof
//split
//string tokenizer
